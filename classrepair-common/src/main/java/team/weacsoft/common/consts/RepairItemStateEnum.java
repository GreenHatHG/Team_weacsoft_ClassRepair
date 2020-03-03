package team.weacsoft.common.consts;

/**
 * @author GreenHatHG
 * @since 2020-02-28
 */

public enum  RepairItemStateEnum {

    /**
     * 待处理
     */
    PENDING(1),
    /**已查看
     *
     */
    CHECKED(2),
    /**
     * 处理中
     */
    PROCESSING(3),
    /**
     * 已处理
     */
    PROCESSED(4),
    /**
     * 已取消
     */
    CANCELLED(5);

    private int state;

    RepairItemStateEnum(int state) {
        this.state = state;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
