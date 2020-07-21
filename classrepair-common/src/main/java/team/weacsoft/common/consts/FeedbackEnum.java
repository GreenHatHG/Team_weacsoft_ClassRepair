package team.weacsoft.common.consts;

/**
 * @Description
 * @ClassName FeedbackEnum
 * @Author 魔法はまだ解けない
 * @date 2020.07.19 22:52
 */
public enum FeedbackEnum {

    PENDING(0, "待处理"),
    CHECKED(1, "已查看");

    private int state;
    private String description;
    FeedbackEnum(int state, String description){
        this.state=state;
        this.description=description;
    }

    public String getDescription() {
        return description;
    }

    public int getState() {
        return state;
    }
}
