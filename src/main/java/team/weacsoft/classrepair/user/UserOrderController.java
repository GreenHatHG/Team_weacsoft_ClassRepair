package team.weacsoft.classrepair.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import team.weacsoft.classrepair.commons.dto.Result;
import team.weacsoft.classrepair.commons.dto.ResultFactory;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author GreenHatHG
 * @menu 订单管理
 */

@RestController
@Validated
@Slf4j
@RequestMapping(value="/repair_item")
public class UserOrderController {

    /**
     * 获得他人所有未处理订单
     * @param id
     * @return
     */
    @GetMapping("/other/missed_orders")
    public Result getAllOtherMissedOrders(
            @RequestParam @NotBlank @Size(max = 100) String id){
        return ResultFactory.buildSuccessResult(null);
    }

    /**
     * 获得他人所有已处理订单
     * @param id
     * @return
     */
    @GetMapping("/other/processed_orders")
    public Result getAllOtherProcessedOrders(
            @RequestParam @NotBlank @Size(max = 100) String id){
        return ResultFactory.buildSuccessResult(null);
    }
}
