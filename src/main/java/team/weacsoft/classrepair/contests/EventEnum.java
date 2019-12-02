package team.weacsoft.classrepair.contests;

/**
 * @author GreenHatHG
 **/
public enum EventEnum {

    Login("用户请求登录"),
    Login_SUCCESS("用户成功登录"),
    Login_FAILED("用户登录失败"),

    REPAIR("用户报修"),
    REPAIR_SUCCESS("报修成功"),
    REPAIR_FAILED("报修失败"),
    CANCEL_REPIAR("用户取消报修"),
    CANCEL_REPAIR_SUCCESS("取消报修失败"),
    ORDER("接单"),
    ORDER_SUCCESS("接单成功"),
    ORDER_FAILED("接单失败");

    public String event;
    EventEnum(String event){
        this.event = event;
    }
}
