package team.weacsoft.classrepair.contests;

/**
 * @author GreenHatHG
 **/
public enum EventEnum {

    Login("用户请求登录"),
    Login_SUCCESS("用户成功登录"),
    Login_FAILED("用户登录失败"),
    ORDERITEM("用户下单"),
    ORDERITEM_SUCCESS("下单成功"),
    ORDERITEM_FAILED("下单失败");

    public String event;
    EventEnum(String event){
        this.event = event;
    }
}
