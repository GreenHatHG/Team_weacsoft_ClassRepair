package team.weacsoft.repair.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author GreenHatHG
 * @since 2020-02-03
 */

@Getter
@Setter
@ToString
public class CommonRepairItemDto implements Serializable {
    private Long createTime;

    private Long updateTime;

    private String classroom;

    private String problem;

    private String repairItemId;

    private Integer state;

    private Integer orderer;

    private String ordererName = "";

    private String ordererPhone = "";

    private Integer receiver;

    private String receiverName = "";

    private String receiverPhone = "";

    private String title;
}
