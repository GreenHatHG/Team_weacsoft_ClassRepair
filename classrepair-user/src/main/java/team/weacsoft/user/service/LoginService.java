package team.weacsoft.user.service;

import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import me.chanjar.weixin.common.error.WxErrorException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import team.weacsoft.common.exception.BadRequestException;
import team.weacsoft.common.exception.EntityNotFoundException;
import team.weacsoft.common.exception.UnauthorizedException;
import team.weacsoft.common.utils.Argon2Util;
import team.weacsoft.common.utils.JsonUtil;
import team.weacsoft.common.utils.JwtUtil;
import team.weacsoft.common.wx.WxMaConfiguration;
import team.weacsoft.user.domain.Admin;
import team.weacsoft.user.domain.UserInfoDo;
import team.weacsoft.user.domain.dto.WebLoginDto;
import team.weacsoft.user.domain.dto.WxLoginDto;

/**
 * @author GreenHatHG
 */

@Component
public class LoginService {

    private UserInfoSelectService userInfoService;
    private JwtUtil jwtUtil;
    private Admin admin;

    @Autowired
    public LoginService(UserInfoSelectService userInfoService, JwtUtil jwtUtil, Admin admin) {
        this.userInfoService = userInfoService;
        this.jwtUtil = jwtUtil;
        this.admin = admin;
    }

    @Value("${classrepair.web.login}")
    private String webLoginPwd;

    public JSONObject wxLogin(WxLoginDto wxLoginDto) {
        WxMaJscode2SessionResult session = null;
        try{
            //请求auth.code2Session
            session = WxMaConfiguration.getWxMaService().getUserService().getSessionInfo(wxLoginDto.getCode());
        }catch (WxErrorException  e){
            throw new BadRequestException(444, JSON.toJSONString(session));
        }

        UserInfoDo userInfo = userInfoService.findByOpenid(session.getOpenid());
        //数据库中还没有该人
        if(userInfo == null){
            userInfo = UserInfoDo.builder()
                    .sessionKey(session.getSessionKey())
                    .name(wxLoginDto.getName())
                    .role(wxLoginDto.getRole())
                    .avatar(wxLoginDto.getAvatar())
                    .password(Argon2Util.hash(wxLoginDto.getPassword()))
                    .phone(wxLoginDto.getPhone())
                    .nickname(wxLoginDto.getNickname())
                    .identityId(wxLoginDto.getIdentityId()).build();
            userInfo.setOpenid(session.getOpenid());
            userInfo = userInfoService.save(userInfo);
        }
        return userInfoDoToResp(userInfo);
    }

    /**
     * 密码不为空&&有学号工号&&是维护人员才允许登录网页
     */
    public JSONObject webLogin(WebLoginDto dto){
        if(StringUtils.equals(String.valueOf(dto.getAccount()), admin.getRootIdentityId())){
            if(Argon2Util.verify(userInfoService.findById(admin.getRootId()).getPassword(),
                    dto.getPwd())){
                MDC.put("userTableId", admin.getRootId());
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("token", jwtUtil.responseJwt(admin.getRootId()));
                return jsonObject;
            }else {
                throw new UnauthorizedException("密码不正确");
            }
        }
        UserInfoDo userInfo = userInfoService.findByIdentityId(dto.getAccount());
        if(userInfo == null){
            throw new EntityNotFoundException("UserInfo", "IdentityId", String.valueOf(dto.getAccount()));
        }
        if(StringUtils.equals(webLoginPwd, dto.getPwd())){
            return userInfoDoToResp(userInfo);
        }else{
            throw new UnauthorizedException("密码不正确");
        }
    }

    /**
     * 构建返回前端的数据
     */
    private JSONObject userInfoDoToResp(UserInfoDo userInfo){
        JSONObject resp = JSONObject.parseObject(JsonUtil.entityExclude(userInfo,
                "password", "createTime", "updateTime").toJSONString());
        resp.put("token", jwtUtil.responseJwt(userInfo.getId()));
        MDC.put("userTableId", userInfo.getId());
        return resp;
    }
}
