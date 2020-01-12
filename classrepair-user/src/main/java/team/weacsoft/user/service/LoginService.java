package team.weacsoft.user.service;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import team.weacsoft.common.exception.EntityNotFoundException;
import team.weacsoft.common.exception.UnauthorizedException;
import team.weacsoft.common.utils.JsonUtil;
import team.weacsoft.common.utils.JwtUtil;
import team.weacsoft.common.wx.WxUtils;
import team.weacsoft.user.domain.Admin;
import team.weacsoft.user.domain.UserInfoDo;
import team.weacsoft.user.domain.dto.WebLoginDto;
import team.weacsoft.user.domain.dto.WxLoginDto;
import team.weacsoft.common.utils.Argon2Util;

/**
 * @author GreenHatHG
 */

@Component
public class LoginService {

    @Autowired
    private UserInfoSelectService userInfoService;

    @Autowired
    private WxUtils wxUtils;

    @Autowired
    private JwtUtil jwtUtil;

    @Value("${classrepair.web.login}")
    private String webLoginPwd;

    public JSONObject wxLogin(WxLoginDto userInfoDto){
        //请求auth.code2Session
        JSONObject code2sessionResp = wxUtils.code2Session(userInfoDto.getCode());

        UserInfoDo userInfo = userInfoService.findByOpenid(code2sessionResp.getString("openid"));
        //数据库中还没有该人
        if(userInfo == null){
            userInfo = UserInfoDo.builder()
                    .sessionKey(code2sessionResp.getString("session_key"))
                    .name(userInfoDto.getName())
                    .role(userInfoDto.getRole())
                    .avatar(userInfoDto.getAvatar())
                    .password(Argon2Util.hash(userInfoDto.getPassword()))
                    .phone(userInfoDto.getPhone())
                    .nickname(userInfoDto.getNickname())
                    .identityId(userInfoDto.getIdentityId()).build();
            userInfo.setOpenid(code2sessionResp.getString("openid"));
            userInfo = userInfoService.save(userInfo);
        }
        return userInfoDoToResp(userInfo);
    }

    /**
     * 密码不为空&&有学号工号&&是维护人员才允许登录网页
     * @param dto
     * @return
     */
    public JSONObject webLogin(WebLoginDto dto){
        if(StringUtils.equals(String.valueOf(dto.getAccount()), Admin.getRootId())){
            if(Argon2Util.verify(userInfoService.findByIdentityId(Long.valueOf(Admin.getRootId())).getPassword(),
                    dto.getPwd())){
                MDC.put("userTableId", Admin.getRootId());
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("token", jwtUtil.getJWT(Admin.getRootId()));
                return jsonObject;
            }else {
                throw new UnauthorizedException("密码不正确");
            }
        }
        UserInfoDo userInfo = userInfoService.findByIdentityId(dto.getAccount());
        if(userInfo == null){
            throw new EntityNotFoundException(UserInfoDo.class, "IdentityId", String.valueOf(dto.getAccount()));
        }
        if(StringUtils.equals(webLoginPwd, dto.getPwd())){
            return userInfoDoToResp(userInfo);
        }else{
            throw new UnauthorizedException("密码不正确");
        }
    }

    /**
     * 构建返回前端的数据
     * @param userInfo
     * @return
     */
    private JSONObject userInfoDoToResp(UserInfoDo userInfo){
        JSONObject resp = JSONObject.parseObject(JsonUtil.entityExclude(userInfo,
                "password", "createTime", "updateTime").toJSONString());
        resp.put("token", jwtUtil.getJWT(userInfo.getId()));
        MDC.put("userTableId", userInfo.getId());
        return resp;
    }
}
