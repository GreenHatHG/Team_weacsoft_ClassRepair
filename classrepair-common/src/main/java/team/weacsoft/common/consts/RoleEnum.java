package team.weacsoft.common.consts;

/**
 * 1-普通人员, 4-维护人员, 5-课室团队负责人, 6-课室管理员 , 7-老师, 9-超级管理员
 * @author GreenHatHG
 * @since 2020-05-13
 */
public enum RoleEnum {
    ORDINARY(1, "普通人员"),
    MAINTENANCE(4, "维护人员"),
    LEADER(5, "课室团队负责人"),
    CLASSADMIN(6, "课室管理员"),
    TEACHER(7, "老师"),
    ADMIN(9, "超级管理员");
    
    private int role;
    private String description;

    public static String getDescription(int state){
        RoleEnum[] enums = RoleEnum.values();
        for(RoleEnum roleEnum : enums){
            if(state == roleEnum.getRole()){
                return roleEnum.getDescription();
            }
        }
        return "";
    }

    RoleEnum(int role, String description) {
        this.role = role;
        this.description = description;
    }

    public int getRole() {
        return role;
    }

    public String getDescription() {
        return description;
    }
}
