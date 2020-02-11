package team.weacsoft.user.service.impl;

import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.chanjar.weixin.common.error.WxErrorException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.weacsoft.common.exception.BadRequestException;
import team.weacsoft.common.exception.UnauthorizedException;
import team.weacsoft.common.persistence.Admin;
import team.weacsoft.common.utils.Argon2Util;
import team.weacsoft.common.utils.JsonUtil;
import team.weacsoft.common.utils.JwtUtil;
import team.weacsoft.common.wx.WxMaConfiguration;
import team.weacsoft.user.dto.reponse.WebLoginResp;
import team.weacsoft.user.dto.reponse.WxLoginResp;
import team.weacsoft.user.dto.request.WebLoginDto;
import team.weacsoft.user.dto.request.WxLoginDto;
import team.weacsoft.user.entity.UserInfo;
import team.weacsoft.user.mapper.UserInfoMapper;
import team.weacsoft.user.service.ILoginService;

/**
 * @author GreenHatHG
 * @since 2020-01-25
 */
@Service
public class LoginServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements ILoginService {

    private Admin admin;

    @Autowired
    public LoginServiceImpl(Admin admin) {
        this.admin = admin;
    }

    @Override
    public WxLoginResp wxLogin(WxLoginDto wxLoginDto) {
        WxMaJscode2SessionResult session;
        try{
            //请求auth.code2Session
            session = WxMaConfiguration.getWxMaService().getUserService().getSessionInfo(wxLoginDto.getCode());
        }catch (WxErrorException e){
            throw new BadRequestException(40000, e.getMessage());
        }

        UserInfo userInfo = getOne(new QueryWrapper<UserInfo>().eq("openid", session.getOpenid()));
        //数据库中还没有该人
        if(userInfo == null){
            userInfo = UserInfo.builder().build();
            BeanUtils.copyProperties(wxLoginDto, userInfo);
            userInfo.setPassword(Argon2Util.hash(userInfo.getPassword()));
            userInfo.setSessionKey(session.getSessionKey());
            userInfo.setOpenid(session.getOpenid());
            save(userInfo);
        }
        MDC.put("userTableId", String.valueOf(userInfo.getId()));
        WxLoginResp wxLoginResp = (WxLoginResp) JsonUtil.getCopyDto(userInfo, new WxLoginResp());
        wxLoginResp.setToken(JwtUtil.responseJwt(String.valueOf(userInfo.getId()), userInfo.getPassword()));
        return wxLoginResp;
    }

    @Override
    public WebLoginResp webLogin(WebLoginDto dto) {
        UserInfo userInfo;
        //超级管理员
        if(StringUtils.equals(String.valueOf(dto.getAccount()), admin.getRootIdentityId())) {
            userInfo = this.getById(admin.getRootId());
        }else {
            userInfo = getOne(new QueryWrapper<UserInfo>().eq("identity_id", dto.getAccount()));
        }
        if(userInfo == null || !Argon2Util.verify(userInfo.getPassword(), dto.getPwd())){
            throw new UnauthorizedException("账号或者密码不正确");
        }
        if(userInfo.getRole() < 2){
            throw new UnauthorizedException("权限不足");
        }
        MDC.put("userTableId", String.valueOf(userInfo.getId()));
        return WebLoginResp.builder().token(JwtUtil.responseJwt(String.valueOf(userInfo.getId()), userInfo.getPassword())).build();
    }

}
