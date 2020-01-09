package team.weacsoft.repair.domain.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author GreenHatHG
 */

@Getter
@Setter
public class OrderItemDto {

    /**
     * 课室号
     */
    @NotBlank
    @Size(max = 100)
    String classroom;

    /**
     * 故障设备类型
     */
    @NotBlank
    @Size(max = 100)
    String equipmentType;

    /**
     * 故障详情描述
     */
    @NotBlank
    @Size(max = 100)
    String problem;

    /**
     * 用户电话
     */
    @Size(max = 100)
    String oderUserPhone = "";
}
