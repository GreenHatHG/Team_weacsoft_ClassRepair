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
public class ExcelRepariItemDto extends CommonRepairItemDto{
    private Long deleteTime;
}
