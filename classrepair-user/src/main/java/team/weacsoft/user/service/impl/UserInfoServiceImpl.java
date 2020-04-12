package team.weacsoft.user.service.impl;

import cn.binarywang.wx.miniapp.api.WxMaUserService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaPhoneNumberInfo;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.ImmutableMap;
import me.chanjar.weixin.common.error.WxErrorException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.weacsoft.common.exception.BadRequestException;
import team.weacsoft.common.exception.EntityNotFoundException;
import team.weacsoft.common.exception.UnauthorizedException;
import team.weacsoft.common.persistence.PageRequest;
import team.weacsoft.common.utils.Argon2Util;
import team.weacsoft.common.utils.JsonUtil;
import team.weacsoft.common.utils.JwtUtil;
import team.weacsoft.common.utils.PageUtil;
import team.weacsoft.common.wx.WxMaConfiguration;
import team.weacsoft.user.dto.common.UpdateRoleDto;
import team.weacsoft.user.dto.reponse.BaseResp;
import team.weacsoft.user.dto.reponse.GetUserInfoByTokenResp;
import team.weacsoft.user.dto.request.GetPhoneDto;
import team.weacsoft.user.dto.request.UpdateUserInfoDto;
import team.weacsoft.user.entity.UserInfo;
import team.weacsoft.user.mapper.UserInfoMapper;
import team.weacsoft.user.service.IUserInfoService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author GreenHatHG
 * @since 2020-01-25
 */

@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {

    @Override
    public GetUserInfoByTokenResp getUserInfoByToken(HttpServletRequest request){
        UserInfo userInfo = getById(JwtUtil.getIdFromRequest(request));
        String identity = null;
        //TODO 這個role并沒有全部修改
        //1-普通人员，4-维护人员,5-课室团队负责人6-课室管理员（A2的阿姨） 7-老师，9-超级管理员(int)
        switch (userInfo.getRole()){
            case 1:
                identity = "普通人员"; break;
            case 4:
                identity = "维护人员"; break;
            case 5:
                identity = "课室团队负责人"; break;
            case 6:
                identity = "课室管理员"; break;
            case 7:
                identity = "老师"; break;
            case 9:
                identity = "超级管理员"; break;
            default:
                break;
        }

        GetUserInfoByTokenResp resp = new GetUserInfoByTokenResp();
        BeanUtils.copyProperties(userInfo, resp);
        resp.setIdentity(identity);
        return resp;
    }

    @Transactional
    @Override
    public UpdateRoleDto updateRoleById(String id, Integer role) {
        UserInfo userInfo = getById(id);
        if(userInfo == null){
            throw new EntityNotFoundException("user", "id", id);
        }
        if(userInfo.getRole() == 5){
            throw new BadRequestException(40010, "不能修改该用户权限");
        }
        userInfo.setRole(role);
        updateById(userInfo);
        return (UpdateRoleDto) JsonUtil.getCopyDto(userInfo, new UpdateRoleDto());
    }

    @Override
    public Page<UserInfo>  getUserInfoByField(String field, String value, PageRequest pageRequest, HttpServletRequest request) {
        //避免泄露超级管理员账户密码
        if(StringUtils.equals(field, "role") && StringUtils.equals(value, "5")
                && getById(JwtUtil.getIdFromRequest(request)).getRole() != 5){
            throw new UnauthorizedException("权限不足，不能查询该字段");
        }
        Page<UserInfo> page = page(PageUtil.getPage(pageRequest),
                new QueryWrapper<UserInfo>().eq(field, value));
        if(page == null){
            throw new EntityNotFoundException("user", field, value);
        }
        return page;
    }

    @Override
    public BaseResp updateMyUserInfo(HttpServletRequest request, UpdateUserInfoDto dto) {
        return updateUserInfo(null, request, dto);
    }

    @Override
    public BaseResp updateOtherUserInfo(Integer id, UpdateUserInfoDto dto) {
        return updateUserInfo(id, null, dto);
    }

    @Transactional
    public BaseResp updateUserInfo(Integer id, HttpServletRequest request, UpdateUserInfoDto dto) {
        if(dto.getPassword() != null){
            dto.setPassword(Argon2Util.hash(dto.getPassword()));
        }
        UserInfo userInfo;
        if(id != null){
            userInfo = getById(id);
            if(userInfo == null){
                throw new EntityNotFoundException("user_info", "id", id.toString());
            }
        }else{
            userInfo = getById(JwtUtil.getIdFromRequest(request));
        }
        BeanUtil.copyProperties(dto, userInfo, CopyOptions.create().setIgnoreNullValue(true));
        updateById(userInfo);
        //重新查询检查数据库中数据是否正确
        userInfo = getById(userInfo.getId());
        return (BaseResp) JsonUtil.getCopyDto(userInfo, new BaseResp());
    }

    @Override
    public Page<UserInfo> getUserList(PageRequest pageRequest) {
        return page(PageUtil.getPage(pageRequest),
                new QueryWrapper<UserInfo>().eq("delete_time", 0L)
                        .eq("state", 1).ne("role", 5));
    }

    @Override
    public List<UserInfo> getPrincipals(){
        return (List<UserInfo>) list(Wrappers.<UserInfo>lambdaQuery()
                .eq(UserInfo::getRole, 5).or()
                .eq(UserInfo::getRole, 6).or()
                .eq(UserInfo::getRole, 7));
    }

    @Override
    public Map<String, String> getPhone(GetPhoneDto dto, HttpServletRequest request) throws WxErrorException {
        WxMaUserService wxMaUserService = WxMaConfiguration.getWxMaService().getUserService();
        WxMaJscode2SessionResult session = wxMaUserService.getSessionInfo(dto.getCode());

        WxMaPhoneNumberInfo phoneNoInfo = wxMaUserService.getPhoneNoInfo(session.getSessionKey(),
                dto.getEncryptedData(), dto.getIv());

        System.out.println(phoneNoInfo.toString());
        UserInfo userInfo = getById(JwtUtil.getIdFromRequest(request));
        userInfo.setPhone(phoneNoInfo.getPhoneNumber());
        updateById(userInfo);
        return ImmutableMap.<String, String>builder().put("phone", userInfo.getPhone()).build();
    }
}
