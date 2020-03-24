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

    public static String getStateById(int state){//通过int state获取指定的描述
        String a;
        switch(state){
            case 1:
                a=RepairItemStateEnum.PENDING.getDescription();
                break;
            case 2:
                a=RepairItemStateEnum.CHECKED.getDescription();
                break;
            case 3:
                a=RepairItemStateEnum.PROCESSING.getDescription();
                break;
            case 4:
                a=RepairItemStateEnum.PROCESSED.getDescription();
                break;
            case 5:
                a=RepairItemStateEnum.CANCELLED.getDescription();
                break;
            default:
                a="错误状态";
        }
        return a;
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
