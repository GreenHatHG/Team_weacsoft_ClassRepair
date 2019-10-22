package team.weacsoft.classrepair.contests;

/**
 * 微信api接口枚举
 * @author GreenHatHG
 **/
public enum WxAPIEnum {
    /**
     * 登录凭证校验
     */
    CODE2SEESION("https://api.weixin.qq.com/sns/jscode2session"),

    /**
     * 订阅消息
     */
    SUBSCRIBE_MESSAGE("https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token=ACCESS_TOKEN");
    public String api;
    WxAPIEnum(String api){
        this.api = api;
    }
}
