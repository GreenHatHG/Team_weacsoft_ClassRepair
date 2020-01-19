package team.weacsoft.common.wx;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import cn.binarywang.wx.miniapp.config.impl.WxMaDefaultConfigImpl;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author GreenHatHG
 */
@Component
@ConfigurationProperties(prefix = "classrepair.wx")
public class WxMaConfiguration {
    private String appid;
    private String appsecret;

    private static WxMaService wxMaService = null;

    @Bean
    public Object services(){
        WxMaDefaultConfigImpl config = new WxMaDefaultConfigImpl ();
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