package team.weacsoft.classrepair.controller;

import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import team.weacsoft.classrepair.bean.RepairItem;
import team.weacsoft.classrepair.bean.UserInfo;
import team.weacsoft.classrepair.commons.dto.Result;
import team.weacsoft.classrepair.commons.dto.ResultFactory;
import team.weacsoft.classrepair.commons.util.WxRequests;
import team.weacsoft.classrepair.contests.EventEnum;
import team.weacsoft.classrepair.service.OperationLogService;
import team.weacsoft.classrepair.service.RepairItemService;
import team.weacsoft.classrepair.service.UserInfoService;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Map;

/**
 * @author GreenHatHG
 **/

@RestController
@RequestMapping(value="${api}/repair_item")
@Validated
public class RepairItemController {

    @Autowired
    private RepairItemService repairItemService;

    @Autowired
    private OperationLogService operationLogService;

    @Autowired
    private WxRequests wxRequests;

    @Autowired
    private UserInfoService userInfoService;

    /**
     * 报修
     * @param code
     * @param classroom
     * @param equipment_type
     * @param problem
     * @param oder_user_phone
     * @return
     */
    @PostMapping("/")
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
        repairItemService.save(repairItem, userInfo.getId());

        operationLogService.addLog(userInfo.getId(), EventEnum.REPAIR.event
                    , EventEnum.REPAIR_SUCCESS.event);

        Map<String, String> resp = ImmutableMap.<String, String> builder()
                .put("repairItemId", repairItem.getRepairItemId())
                .put("orderUserId", repairItem.getOrderUserId())
                .put("classroom", repairItem.getClassroom())
                .put("type", repairItem.getEquipmentType())
                .put("content", repairItem.getProblem())
                .put("oderUserPhone", repairItem.getOderUserPhone()).build();

        return ResultFactory.buildSuccessResult(resp);
    }

    /**
     * 接单
     * @param repair_item_id
     * @param code
     * @return
     */
    @PostMapping("/actions/order")
    public Result order(@RequestParam @NotBlank @Size(max = 100) String repair_item_id,
                        @RequestParam @NotEmpty String code){
        RepairItem repairItem = repairItemService.findByRepairItemId(repair_item_id);
        UserInfo userInfo = userInfoService.findByOpenIdAndCheck(
                wxRequests.code2Session(code).getStr("openid"), code);
        repairItem.setReceiverUserId(userInfo.getId());
        repairItem.setState(2);
        repairItemService.update(repairItem);
        operationLogService.addLog(userInfo.getId(), EventEnum.ORDER.event,
                EventEnum.ORDER_SUCCESS.event);
        return ResultFactory.buildSuccessResult("接单成功");
    }

    /**
     * 取消用户报修订单
     * @param repair_item_id
     * @param code
     * @return
     */
    @PutMapping("/actions/cancel_repair")
    public Result cancelRepair(@RequestParam @NotBlank @Size(max = 100) String repair_item_id,
                         @RequestParam @NotBlank @Size(max = 100) String code){
        RepairItem repairItem = repairItemService.findByRepairItemId(repair_item_id);
        UserInfo userInfo = userInfoService.findByOpenIdAndCheck(
                wxRequests.code2Session(code).getStr("openid"), code);
        repairItem.setState(4);
//        repairItem.setDeleteTime(new Timestamp(System.currentTimeMillis()));
        repairItemService.update(repairItem);
        return ResultFactory.buildSuccessResult("取消成功");
    }

//    @PutMapping("/actions/cancel_order")
//    public Result cancelOrder(@RequestParam @NotBlank @Size(max = 100) String repair_item_id,
//                               @RequestParam @NotBlank @Size(max = 100) String code) {
//        RepairItem repairItem = repairItemService.findByRepairItemId(repair_item_id);
//    }



//    /**
//     * 查询全部或者按单条件查询
//     */
//    @GetMapping("/orderitems")
//    public Result getOrderItem(HttpServletRequest request){
//        RepairItem orderItem = null;
//        List<RepairItem> orderItems = null;
//
//        //解析传递过来的参数，只能解析第一个参数
//        Iterator entries = request.getParameterMap().entrySet().iterator();
//
//        //查询全部数据，没有分页
//        if(!entries.hasNext()){
//            return ResultFactory.buildSuccessResult(orderItemRepository.findAll());
//        }
//
//        Map.Entry entry = (Map.Entry) entries.next();
//        String param = (String) entry.getKey();
//        String value = ((String[]) entry.getValue())[0];
//
//        //注意需要解码value，因为可能有中文字符
//        try{
//            value = URLDecoder.decode(value, StandardCharsets.UTF_8);
//        }catch (Exception e){
//            return ResultFactory.buildFailResult("解析参数失败，请检查参数值");
//        }
//
//        switch (param){
//            case "id":
//                orderItem = orderItemRepository.findById(value).get();
//                break;
//            case "userid":
//                orderItems = orderItemRepository.findByOpenId(value);
//                break;
//            case "orderid":
//                orderItem = orderItemRepository.findByOrderId(value);
//                break;
//            case "classroom":
//                orderItems = orderItemRepository.findByClassroom(value);
//                break;
//            case "type":
//                orderItems = orderItemRepository.findByType(value);
//                break;
//            case "phone":
//                orderItems = orderItemRepository.findByPhone(value);
//                break;
//            case "status":
//                orderItems = orderItemRepository.findByStatus(Integer.parseInt(value));
//                break;
//            default:
//                return ResultFactory.buildFailResult("未找到相关信息");
//        }
//
//        if(orderItem != null){
//            return ResultFactory.buildSuccessResult(orderItem);
//        }else if(orderItems != null){
//            return ResultFactory.buildSuccessResult(orderItems);
//        }
//        return ResultFactory.buildFailResult("未找到相关信息");
//    }
//
//    /**
//     * 分页查询所有的orderitem
//     * @param page 第几页
//     * @param size 每一页数据数量
//     * @return
//     */
//    @GetMapping("/orderitems/pages")
//    public Result getOrderitemsWithPages(int page, int size){
//        Pageable pageable = PageRequest.of(page - 1, size);
//        Page<RepairItem> pages = orderItemRepository.findAll(pageable);
//        return ResultFactory.buildSuccessResult(pages);
//    }
//
//    @PutMapping("/orderitem")
//    public Result updateOrderItem(@RequestBody RepairItem orderItem){
//        try{
//            orderItemRepository.save(orderItem);
//        }catch (Exception e){
//            e.printStackTrace();
//            return ResultFactory.buildFailResult("更新失败");
//        }
//        return ResultFactory.buildSuccessResult("更新成功");
//    }
//
//    @PutMapping("/orderitem/repairid")
//    public Result updateRepairId(int repairid, String orderid){
//        try{
//            if(orderItemRepository.findByOrderId(orderid) == null){
//                return ResultFactory.buildFailResult("未找到相关记录，修改失败");
//            }
//            orderItemRepository.updateRepairIdByOrderId(repairid, orderid);
//        }catch (Exception e){
//            e.printStackTrace();
//            return ResultFactory.buildFailResult("修改失败");
//        }
////        operationLogService.addLog(repairid, "接单成功", "单号：" + orderid);
//        return ResultFactory.buildSuccessResult("修改成功");
//    }
//
//    //todo 添加删除记录
//    @DeleteMapping("/orderitems")
//    public Result deleteOrderItemByOrderId(String orderid){
//        try{
//            if(orderid == null){
//                orderItemRepository.deleteAll();
//            }
//            orderItemRepository.deleteByOrderId(orderid);
//        }catch (Exception e){
//            e.printStackTrace();
//            return ResultFactory.buildFailResult("删除失败");
//        }
//        return ResultFactory.buildSuccessResult("删除成功");
//    }
}