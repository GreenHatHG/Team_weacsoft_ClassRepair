package team.weacsoft.repair.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Description
 * @ClassName ExcelRepariItemDto
 * @Author 魔法はまだ解けない
 * @date 2020.02.24 18:30
 */
@Getter
@Setter
@ToString
public class ExcelRepariItemDto  {
    private Long createTime;

    private Long updateTime;

    private String classroom;

    private String problem;

    private String repairItemId;

    private Integer state;

    //private Integer orderer;

    private String ordererName = "";

    private String ordererPhone = "";

    //private Integer receiver;

    private String receiverName = "";

    private String receiverPhone = "";

    private String title;

    private Long deleteTime;
}
