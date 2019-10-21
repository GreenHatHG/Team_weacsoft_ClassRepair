//package team.weacsoft.classrepair.controller;
//
//import cn.hutool.core.util.IdUtil;
//import cn.hutool.json.JSONObject;
//import cn.hutool.json.JSONUtil;
//import net.bytebuddy.asm.Advice;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.web.bind.annotation.*;
//import team.weacsoft.classrepair.bean.OrderItem;
//import team.weacsoft.classrepair.contests.EventEnum;
//import team.weacsoft.classrepair.entity.Result;
//import team.weacsoft.classrepair.entity.ResultFactory;
//import team.weacsoft.classrepair.repository.OrderItemRepository;
//import team.weacsoft.classrepair.service.OperationLogService;
//import team.weacsoft.classrepair.util.WxUtils;
//
//import javax.servlet.http.HttpServletRequest;
//import java.net.URLDecoder;
//import java.nio.charset.StandardCharsets;
//import java.util.*;
//
///**
// * @author GreenHatHG
// **/
//
//@RestController
//@RequestMapping(value="${api}")
//public class OrderItemController {
//
//    @Autowired
//    private OrderItemRepository orderItemRepository;
//    @Autowired
//    private OperationLogService operationLogService;
//
//    /**
//     * 提交表单
//     * 可以插入相同记录的orderitem
//     * @param payload
//     * @return
//     */
//    @PostMapping("/orderitem")
//    public Result order(@RequestBody Map<String, Object> payload){
//        JSONObject jsonObject = JSONUtil.parseObj(payload);
//        OrderItem orderItem = new OrderItem();
//
//        /**
//         * 向auth.code2session发送请求
//         */
//        JSONObject code2sessionResp = WxUtils.wxAuth(new Code2SessionBody(jsonObject.getStr("code")));
//
//        try{
//            orderItem = JSONUtil.toBean(JSONUtil.toJsonStr(payload), OrderItem.class);
//            orderItem.setOrderId(IdUtil.objectId());
//            orderItemRepository.save(orderItem);
//        }catch (Exception e){
//            e.printStackTrace();
//            operationLogService.addLog(orderItem.getOpenId(), "提交订单失败", EventEnum.ADDORDERITEM.event);
//            return ResultFactory.buildFailResult("传递的参数不符合要求");
//        }
//        operationLogService.addLog(orderItem.getOpenId(), "提交订单成功", "-");
//        return ResultFactory.buildSuccessResult("成功");
//    }
//
//    /**
//     * 查询全部或者按单条件查询
//     */
//    @GetMapping("/orderitems")
//    public Result getOrderItem(HttpServletRequest request){
//        OrderItem orderItem = null;
//        List<OrderItem> orderItems = null;
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
//        Page<OrderItem> pages = orderItemRepository.findAll(pageable);
//        return ResultFactory.buildSuccessResult(pages);
//    }
//
//    @PutMapping("/orderitem")
//    public Result updateOrderItem(@RequestBody OrderItem orderItem){
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
//}