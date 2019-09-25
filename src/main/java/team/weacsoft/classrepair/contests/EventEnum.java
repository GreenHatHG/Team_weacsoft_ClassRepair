package team.weacsoft.classrepair.contests;

/**
 * @author GreenHatHG
 **/
public enum EventEnum {

    Login("用户登录"),

    ADDORDERITEM("保存订单到数据库失败，可能是传递的参数不符合要求");

    public String event;
    EventEnum(String event){
        this.event = event;
    }
}
