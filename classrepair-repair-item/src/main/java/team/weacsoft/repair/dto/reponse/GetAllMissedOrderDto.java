package team.weacsoft.repair.dto.reponse;

import lombok.Data;

import java.io.Serializable;

/**
 * @author GreenHatHG
 * @since 2020-02-03
 */

@Data
public class GetAllMissedOrderDto implements Serializable {
    private Long createTime;
    private Long updateTime;
    private String classroom;
    private String problem;
    private String repairItemId;
    private Integer orderer;
    private String name;
    private String title;
}
