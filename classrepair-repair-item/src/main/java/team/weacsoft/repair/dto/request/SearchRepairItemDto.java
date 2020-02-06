package team.weacsoft.repair.dto.request;

import lombok.Data;

import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @author GreenHatHG
 * @since 2020-02-06
 */

@Data
public class SearchRepairItemDto implements Serializable {

    @Size(max = 100)
    private String repairItemId;

    @Size(max = 100)
    private String ordererName;

    private Long identityId;

}
