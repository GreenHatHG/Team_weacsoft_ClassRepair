package team.weacsoft.classrepair.controller;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.weacsoft.classrepair.bean.OrderItem;
import team.weacsoft.classrepair.bean.UserInfo;
import team.weacsoft.classrepair.contests.EventEnum;
import team.weacsoft.classrepair.entity.Result;
import team.weacsoft.classrepair.entity.ResultFactory;
import team.weacsoft.classrepair.entity.wx.Code2SessionBody;
import team.weacsoft.classrepair.repository.OperationLogRepository;
import team.weacsoft.classrepair.repository.OrderItemRepository;
import team.weacsoft.classrepair.service.OperationLogService;
import team.weacsoft.classrepair.util.WxUtils;

import java.util.Map;

/**
 * @author GreenHatHG
 **/

@RestController
@RequestMapping(value="/api/v1")
public class OrderItemController {

    private JSONObject jsonObject = null;
    private OrderItem orderItem = null;

    private OrderItemRepository orderItemRepository;
    private OperationLogService operationLogService;

    @Autowired
    public void setOrderItemRepository(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    public void setOperationLogService(OperationLogService operationLogService) {
        this.operationLogService = operationLogService;
    }

    @PostMapping("/orderitem")
    public Result order(@RequestBody Map<String, Object> payload){
        jsonObject = JSONUtil.parseObj(payload);
        orderItem = new OrderItem();

        JSONObject code2sessionResp = WxUtils.wxAuth(new Code2SessionBody(jsonObject.getStr("code")));

        try{
            orderItem = JSONUtil.toBean(JSONUtil.toJsonStr(payload), OrderItem.class);
            orderItemRepository.save(orderItem);
        }catch (Exception e){
            e.printStackTrace();
            operationLogService.addLog(code2sessionResp.getInt("openid"), "提交订单失败", EventEnum.ADDORDERITEM.event);
            return ResultFactory.buildFailResult("传递的参数不符合要求");
        }
        operationLogService.addLog(code2sessionResp.getInt("openid"), "提交订单成功", null);
        return ResultFactory.buildSuccessResult("成功");
    }

}
