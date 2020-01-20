package team.weacsoft.common.wx;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import cn.binarywang.wx.miniapp.config.impl.WxMaDefaultConfigImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author GreenHatHG
 */
@Component
public class WxMaConfiguration {
    @Value("${classrepair.wx.appid}")
    private String appid;
    @Value("${classrepair.wx.appsecret}")
    private String appsecret;

    private static WxMaService wxMaService = null;

    @Bean
    public Object services(){
        WxMaDefaultConfigImpl config = new WxMaDefaultConfigImpl();
        config.setAppid(appid);
        config.setSecret(appsecret);

        wxMaService = new WxMaServiceImpl();
        wxMaService.setWxMaConfig(config);

        return Boolean.TRUE;
    }

    public static WxMaService getWxMaService(){
        return wxMaService;
    }
}