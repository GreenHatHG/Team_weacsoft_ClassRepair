package team.weacsoft.classrepair.controller;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.weacsoft.classrepair.bean.OrderItem;
import team.weacsoft.classrepair.bean.UserInfo;
import team.weacsoft.classrepair.contests.EventEnum;
import team.weacsoft.classrepair.entity.Result;
import team.weacsoft.classrepair.entity.ResultFactory;
import team.weacsoft.classrepair.repository.OrderItemRepository;
import team.weacsoft.classrepair.repository.UserInfoRepository;
import team.weacsoft.classrepair.service.OperationLogService;
import team.weacsoft.classrepair.util.WxRequests;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author GreenHatHG
 **/

@RestController
@RequestMapping(value="${api}")
public class OrderItemController {

    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private OperationLogService operationLogService;
    @Autowired
    private WxRequests wxRequests;
    @Autowired
    private UserInfoRepository userInfoRepository;

    /**
     * 提交表单
     * @param payload
     * @return
     */
    @PostMapping("/orderitems")
    public ResponseEntity<Result> order(@RequestBody Map<String, Object> payload){
        //todo 去除重复代码，目前没有好办法
        JSONObject jsonObject = JSONUtil.parseObj(payload);

        //获取必要值并且判断空
        String jsCode = jsonObject.getStr("code");
        String classroom = jsonObject.getStr("classroom");
        String type = jsonObject.getStr("type");
        String content = jsonObject.getStr("content");

        if(jsCode == null){
            return ResultFactory.buildPropertyErroResult("code参数为空");
        }
        if(classroom == null){
            return ResultFactory.buildPropertyErroResult("classroom参数为空");
        }
        if(type == null){
            return ResultFactory.buildPropertyErroResult("type参数为空");
        }
        if(content == null){
            return ResultFactory.buildPropertyErroResult("content参数为空");
        }

        //请求微信接口
        JSONObject code2sessionResp = wxRequests.code2Session(jsCode);
        if(code2sessionResp.getInt("errcode") != null){
            operationLogService.addLog(""
                    , EventEnum.ORDERITEM.event, EventEnum.ORDERITEM_FAILED.event+"-->通过wx.login接口获得openid失败");
            return ResultFactory.buildNotAcceptableResult("通过wx.login接口获得openid失败", code2sessionResp);
        }

        //对orderItem赋值
        OrderItem orderItem = new OrderItem();

        UserInfo userInfo = userInfoRepository.findByOpenid(code2sessionResp.getStr("openid"));
        if(userInfo == null){
            return ResultFactory.buildNotAcceptableResult("数据库找不到此人，请检查code值的正确性", jsonObject);
        }

        //生成订单id
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        orderItem.setOrderItemId(sdf.format(date) + System.currentTimeMillis() / 100);
        orderItem.setOrderId(userInfo.getId());
        orderItem.setClassroom(classroom);
        orderItem.setType(type);
        orderItem.setContent(content);
        if(jsonObject.getStr("phone") != null){
            orderItem.setPhone(jsonObject.getStr("phone"));
        }else{
            orderItem.setPhone(userInfo.getPhone());
        }

        try{
            orderItemRepository.save(orderItem);
        }catch (Exception e){
            e.printStackTrace();
            operationLogService.addLog(userInfo.getId(), EventEnum.ORDERITEM.event
                    , EventEnum.ORDERITEM_FAILED.event+"->保存到数据库出错");
            return ResultFactory.buildFailResult("保存订单到数据库失败" + "-->" + e.getMessage());
        }
        operationLogService.addLog(userInfo.getId(), EventEnum.ORDERITEM.event
                    , EventEnum.ORDERITEM_SUCCESS.event);

        Map<String, String> resp = new HashMap<>(6);
        resp.put("orderItemId", orderItem.getOrderItemId());
        resp.put("orderId", orderItem.getOrderId());
        resp.put("classroom", orderItem.getClassroom());
        resp.put("type", orderItem.getType());
        resp.put("content", orderItem.getContent());
        resp.put("phone", orderItem.getPhone());
        return ResultFactory.buildSuccessResult(resp);
    }

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
}