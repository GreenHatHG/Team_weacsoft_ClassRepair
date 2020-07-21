package team.weacsoft.repair.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 推送消息实体类
 * @author GreenHatHG
 * @since 2020-03-01
 */

@Getter
@Setter
@ToString
public class PushInfo extends PeriodStatistics{
    private int state;
    private String problem;
    private String ordererName;
    private String ordererPhone;
    private String repairItemId;
}
