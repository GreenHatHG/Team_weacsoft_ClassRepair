package team.weacsoft.repair.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
/**
 * 订单搜索查询类
 */
public class OrderSearchEntity {

    //订单号
    private String repairItemId;
    //本请用户
    private Integer userId;
    //下单人名字
    private String ordererName;
    //接单人学号
    private Integer receiverIdentityId;
    //接单人姓名
    private String receiverName;
    //订单状态
    private Integer searchState;
    //搜索依据
    @NotBlank
    private Integer range;

}
