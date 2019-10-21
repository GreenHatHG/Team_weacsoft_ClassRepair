package team.weacsoft.classrepair.contests;

/**
 * @author GreenHatHG
 **/
public enum EventEnum {

    Login("用户请求登录"),
    Login_SUCCESS("用户成功登录"),
    Login_Failed("用户登录失败"),
    ADDORDERITEM("保存订单到数据库失败，可能是传递的参数不符合要求");

    public String event;
    EventEnum(String event){
        this.event = event;
    }
}
