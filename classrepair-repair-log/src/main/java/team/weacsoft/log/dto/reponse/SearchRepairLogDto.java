package team.weacsoft.log.dto.reponse;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import team.weacsoft.repair.dto.request.CommonRepairItemDto;

/**
 * @author GreenHatHG
 * @since 2020-02-14
 */

@Getter
@Setter
@ToString
public class SearchRepairLogDto extends CommonRepairItemDto {
    private String logContent;
}
