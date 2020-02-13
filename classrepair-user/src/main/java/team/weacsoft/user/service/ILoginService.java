package team.weacsoft.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import me.chanjar.weixin.common.error.WxErrorException;
import team.weacsoft.user.dto.reponse.WebLoginResp;
import team.weacsoft.user.dto.reponse.WxLoginResp;
import team.weacsoft.user.dto.request.WebLoginDto;
import team.weacsoft.user.dto.request.WxLoginDto;
import team.weacsoft.user.entity.UserInfo;

/**
 * @author GreenHatHG
 * @since 2020-01-25
 */
public interface ILoginService extends IService<UserInfo> {

    /**
     * 微信登录接口
     */
    WxLoginResp wxLogin(WxLoginDto wxLoginDto) throws WxErrorException;

    /**
     * 网页登录接口
     */
    WebLoginResp webLogin(WebLoginDto webLoginDto);
}
