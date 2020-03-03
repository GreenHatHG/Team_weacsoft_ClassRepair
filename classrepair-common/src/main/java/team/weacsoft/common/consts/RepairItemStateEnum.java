package team.weacsoft.common.consts;

/**
 * @author GreenHatHG
 * @since 2020-02-28
 */

public enum  RepairItemStateEnum {

    PENDING(1, "待处理"),
    CHECKED(2, "已查看"),
    PROCESSING(3, "处理中"),
    PROCESSED(4, "已处理"),
    CANCELLED(5, "已取消");

    private int state;
    private String description;

    RepairItemStateEnum(int state, String description) {
        this.state = state;
        this.description = description;
    }

    public int getState() {
        return state;
    }

    public String getDescription() {
        return description;
    }

    public static String getDescription(int state){
        RepairItemStateEnum[] enums = RepairItemStateEnum.values();
        for(RepairItemStateEnum repairItemStateEnum : enums){
            if(state == repairItemStateEnum.getState()){
                return repairItemStateEnum.getDescription();
            }
        }
        return "";
    }
}
