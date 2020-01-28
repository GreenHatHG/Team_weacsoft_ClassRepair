package team.weacsoft.user.service.impl;

import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import team.weacsoft.common.exception.UnauthorizedException;
import team.weacsoft.common.persistence.Admin;
import team.weacsoft.common.utils.Argon2Util;
import team.weacsoft.common.utils.JsonUtil;
import team.weacsoft.common.utils.JwtUtil;
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

    @Value("${classrepair.web.login}")
    private String webLoginPwd;

    @Override
    public WxLoginResp wxLogin(WxLoginDto wxLoginDto) {
        WxMaJscode2SessionResult session = null;
//        try{
//            //请求auth.code2Session
//            session = WxMaConfiguration.getWxMaService().getUserService().getSessionInfo(wxLoginDto.getCode());
//        }catch (WxErrorException e){
//            throw new BadRequestException(40000, e.getMessage());
//        }
        session = new WxMaJscode2SessionResult();
        session.setOpenid(RandomUtil.randomString(20));
        session.setSessionKey(RandomUtil.randomString(22));

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
        wxLoginResp.setToken(JwtUtil.responseJwt(String.valueOf(userInfo.getId())));
        return wxLoginResp;
    }

    @Override
    public WebLoginResp webLogin(WebLoginDto dto) {
        UserInfo userInfo;
        //超级管理员
        if(StringUtils.equals(String.valueOf(dto.getAccount()), admin.getRootIdentityId())) {
            userInfo = this.getById(admin.getRootId());
            if(userInfo == null || !Argon2Util.verify(userInfo.getPassword(), dto.getPwd())){
                throw new UnauthorizedException("账号或者密码不正确");
            }
        }else {
            userInfo = getOne(new QueryWrapper<UserInfo>().eq("identity_id", dto.getAccount()));
            if(userInfo == null || !StringUtils.equals(webLoginPwd, dto.getPwd())){
                throw new UnauthorizedException("账号或者密码不正确");
            }
        }
        MDC.put("userTableId", String.valueOf(userInfo.getId()));
        return WebLoginResp.builder().token(JwtUtil.responseJwt(String.valueOf(userInfo.getId()))).build();
    }

}
