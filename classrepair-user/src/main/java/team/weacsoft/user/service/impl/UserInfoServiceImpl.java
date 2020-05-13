package team.weacsoft.user.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.weacsoft.common.consts.RoleEnum;
import team.weacsoft.common.exception.BadRequestException;
import team.weacsoft.common.exception.EntityNotFoundException;
import team.weacsoft.common.persistence.PageRequest;
import team.weacsoft.common.utils.Argon2Util;
import team.weacsoft.common.utils.JsonUtil;
import team.weacsoft.common.utils.JwtUtil;
import team.weacsoft.common.utils.PageUtil;
import team.weacsoft.user.dto.common.UpdateRoleDto;
import team.weacsoft.user.dto.reponse.BaseResp;
import team.weacsoft.user.dto.reponse.GetUserInfoByTokenResp;
import team.weacsoft.user.dto.request.FieldDtoEnum;
import team.weacsoft.user.dto.request.UpdateUserInfoDto;
import team.weacsoft.user.entity.UserInfo;
import team.weacsoft.user.mapper.UserInfoMapper;
import team.weacsoft.user.service.IUserInfoService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
        if(StringUtils.equals(field, FieldDtoEnum.role.toString())
                && StringUtils.equals(value, String.valueOf(RoleEnum.ADMIN.getRole()))
                && getById(JwtUtil.getIdFromRequest(request)).getRole() != RoleEnum.ADMIN.getRole()){
            throw new AccessDeniedException("权限不足，不能查询该字段");
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
}
