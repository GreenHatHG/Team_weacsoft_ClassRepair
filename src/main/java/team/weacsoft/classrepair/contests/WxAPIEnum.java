package team.weacsoft.classrepair.contests;

/**
 * 微信api接口枚举
 * @author GreenHatHG
 **/
public enum WxAPIEnum {
    /**
     * 登录凭证校验
     */
    CODE2SEESION("https://api.weixin.qq.com/sns/jscode2session?appid=" +
            "APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code");


    public String api;
    WxAPIEnum(String api){
        this.api = api;
    }
}
