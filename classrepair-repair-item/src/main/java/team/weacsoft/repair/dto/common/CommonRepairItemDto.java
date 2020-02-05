package team.weacsoft.repair.dto.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @author GreenHatHG
 * @since 2020-02-03
 */

@Data
public class CommonRepairItemDto implements Serializable {
    private Long createTime;
    private Long updateTime;
    private String classroom;
    private String problem;
    private String repairItemId;
    private Integer orderer;
    private String ordererName = "";
    private Integer receiver;
    private String receiverName = "";
    private String title;
}
