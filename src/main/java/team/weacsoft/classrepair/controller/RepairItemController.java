package team.weacsoft.classrepair.controller;

import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import team.weacsoft.classrepair.bean.RepairItem;
import team.weacsoft.classrepair.bean.UserInfo;
import team.weacsoft.classrepair.commons.dto.Result;
import team.weacsoft.classrepair.commons.dto.ResultFactory;
import team.weacsoft.classrepair.commons.exception.CustomException;
import team.weacsoft.classrepair.commons.log.Log;
import team.weacsoft.classrepair.commons.util.WxRequests;
import team.weacsoft.classrepair.service.RepairItemService;
import team.weacsoft.classrepair.service.UserInfoService;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Map;

/**
 * @author GreenHatHG
 * @menu 订单管理
 **/

@RestController
@RequestMapping(value="/repair_item")
@Validated
public class RepairItemController {

    @Autowired
    private RepairItemService repairItemService;

    @Autowired
    private WxRequests wxRequests;

    @Autowired
    private UserInfoService userInfoService;

    /**
     * 用户报修接口
     * @param code 临时登录凭证
     * @param classroom 课室号
     * @param equipment_type 故障设备类型
     * @param problem 故障详情描述
     * @param oder_user_phone 用户电话
     * @return
     */
    @Log(module = "订单管理", operation = "用户增加报修单")
    @PostMapping("")
    public Result addOrderItem(@RequestParam @NotBlank @Size(max = 100) String code,
                        @RequestParam @NotBlank @Size(max = 100) String classroom,
                        @RequestParam @NotBlank @Size(max = 100) String equipment_type,
                        @RequestParam @NotBlank String problem,
                        @RequestParam(required = false) String oder_user_phone){

        UserInfo userInfo = userInfoService.findByOpenIdAndCheck(
                wxRequests.code2Session(code).getStr("openid"), code);

        RepairItem repairItem = RepairItem.builder()
                .repairItemId(repairItemService.getRepairItemId())
                .orderUserId(userInfo.getId())
                .classroom(classroom)
                .equipmentType(equipment_type)
                .problem(problem)
                .oderUserPhone(oder_user_phone == null ? userInfo.getPhone() : oder_user_phone)
                .build();
        repairItemService.save(repairItem);
        // 再次查询，确保插入的数据正确
        repairItem = repairItemService.findByRepairItemId(repairItem.getRepairItemId());

        return ResultFactory.buildSuccessResult(getUpdateRepairItemResp(repairItem));
    }

    /**
     * 维修人员接单
     * @param repair_item_id 订单号
     * @param code 临时登录凭证
     * @return
     */
    @Log(module = "订单管理", operation = "维修人员接单")
    @PostMapping("/actions/order")
    public Result order(@RequestParam @NotBlank @Size(max = 100) String repair_item_id,
                        @RequestParam @NotEmpty String code){
        RepairItem repairItem = repairItemService.findByRepairItemId(repair_item_id);
        UserInfo userInfo = userInfoService.findByOpenIdAndCheck(
                wxRequests.code2Session(code).getStr("openid"), code);
        if(repairItem.getState() == 2){
            throw new CustomException(465, "该单已接", getUpdateRepairItemResp(repairItem));
        }
        repairItem.setReceiverUserId(userInfo.getId());
        repairItem.setState(2);
        repairItemService.update(repairItem);
        // 再次查询，确保插入的数据正确
        repairItem = repairItemService.findByRepairItemId(repairItem.getRepairItemId());
        return ResultFactory.buildSuccessResult(getUpdateRepairItemResp(repairItem));
    }

    /**
     * 维修人员取消报修
     * @param repair_item_id 订单号
     * @param code 临时登录凭证
     * @return
     */
    @Log(module = "订单管理", operation = "维修人员取消报修")
    @PutMapping("/actions/cancel_repair")
    public Result cancelRepair(@RequestParam @NotBlank @Size(max = 100) String repair_item_id,
                         @RequestParam @NotBlank @Size(max = 100) String code){
        return updateRepairItem(repair_item_id, code, 1);
    }

    /**
     * 用户取消报修
     * @param repair_item_id 订单号
     * @param code 临时登录凭证
     * @return
     */
    @Log(module = "订单管理", operation = "用户取消报修")
    @PutMapping("/actions/cancel_order")
    public Result cancelOrder(@RequestParam @NotBlank @Size(max = 100) String repair_item_id,
                               @RequestParam @NotBlank @Size(max = 100) String code) {
        return updateRepairItem(repair_item_id, code, 4);
    }

    /**
     * 完成报修
     * @param repair_item_id 订单号
     * @param code 临时登录凭证
     * @return
     */
    @Log(module = "订单管理", operation = "完成报修")
    @PutMapping("/actions/complete")
    public Result completeOrder(@RequestParam @NotBlank @Size(max = 100) String repair_item_id,
                              @RequestParam @NotBlank @Size(max = 100) String code) {
        return updateRepairItem(repair_item_id, code, 3);
    }

    /**
     * 返回更新后的数据
     * @param repairItem
     * @return
     */
    private Map<String, String> getUpdateRepairItemResp(RepairItem repairItem){
        return ImmutableMap.<String, String> builder()
                .put("repairItemId", repairItem.getRepairItemId())
                .put("orderUserId", repairItem.getOrderUserId())
                .put("classroom", repairItem.getClassroom())
                .put("type", repairItem.getEquipmentType())
                .put("content", repairItem.getProblem())
                .put("orderUserPhone", repairItem.getOderUserPhone())
                .put("state", String.valueOf(repairItem.getState())).build();
    }

    /**
     * 对订单的统一操作，i用来确定订单状态
     * @param repair_item_id
     * @param code
     * @param i
     * @return
     */
    private Result updateRepairItem(String repair_item_id, String code, int i) {
        userInfoService.findByOpenIdAndCheck(
                wxRequests.code2Session(code).getStr("openid"), code);
        RepairItem repairItem = repairItemService.findByRepairItemId(repair_item_id);
        repairItem.setState(i);
        repairItem.setDeleteTime(System.currentTimeMillis());
        repairItemService.update(repairItem);
        // 再次查询，确保插入的数据正确
        repairItem = repairItemService.findByRepairItemId(repairItem.getRepairItemId());
        return ResultFactory.buildSuccessResult(getUpdateRepairItemResp(repairItem));
    }

}