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
public class OrderSearchEntity {

    //订单号
    String repairItemId;
    //本请用户
    String userId;
    //下单人名字
    String ordererName;
    //接单人学号
    Integer receiverIdentityId;
    //接单人姓名
    String receiverName;
    //订单状态
    Integer searchState;
    //搜索依据
    @NotBlank
    Integer range;



}
