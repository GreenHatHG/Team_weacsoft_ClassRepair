package team.weacsoft.log.dto.reponse;

import lombok.Data;

/**
 * @author GreenHatHG
 * @since 2020-02-15
 */

@Data
public class FindRepairLogDto {
    private Long createTime;

    private Long updateTime;

    private String logContent;

    private String repairItemId;
}
