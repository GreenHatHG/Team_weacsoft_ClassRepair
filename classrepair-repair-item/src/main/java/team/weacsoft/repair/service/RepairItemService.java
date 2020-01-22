package team.weacsoft.repair.service;

import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import team.weacsoft.common.exception.BadRequestException;
import team.weacsoft.common.exception.EntityNotFoundException;
import team.weacsoft.common.exception.UnauthorizedException;
import team.weacsoft.common.utils.JwtUtil;
import team.weacsoft.common.wx.SendDYTemplateMessage;
import team.weacsoft.repair.domain.RepairItemDo;
import team.weacsoft.repair.domain.dto.OrderItemDto;
import team.weacsoft.repair.repository.RepairItemRepository;
import team.weacsoft.user.domain.UserInfoDo;
import team.weacsoft.user.service.UserInfoSelectService;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * @author GreenHatHG
 */

@Component
@Slf4j
public class RepairItemService {

    private RepairItemRepository repairItemRepository;
    private JwtUtil jwtUtil;
    private UserInfoSelectService userInfoService;
    private SendDYTemplateMessage sendDYTemplateMessage;

    @Autowired
    public RepairItemService(RepairItemRepository repairItemRepository, JwtUtil jwtUtil, UserInfoSelectService userInfoService, SendDYTemplateMessage sendDYTemplateMessage) {
        this.repairItemRepository = repairItemRepository;
        this.jwtUtil = jwtUtil;
        this.userInfoService = userInfoService;
        this.sendDYTemplateMessage = sendDYTemplateMessage;
    }

    public RepairItemDo findByRepairItemId(String repairItemId){
        RepairItemDo repairItem = repairItemRepository.findByRepairItemId(repairItemId);
        if(repairItem == null){
            throw new EntityNotFoundException("RepairItem", "repairItemId", repairItemId);
        }
        return repairItem;
    }

    public List<RepairItemDo> findAll(){
        return repairItemRepository.findAll();
    }

    /**
     * 生成订单id
     */
    public String getRepairItemId(){
        //DateUtil.today():当前日期字符串，格式：yyyyMMdd
        return DateUtil.format(new Date(), "yyyyMMdd") + System.currentTimeMillis() / 100;
    }

    /**
     * 用户报修
     */
    public RepairItemDo addOrderItem(OrderItemDto orderItemDto, HttpServletRequest request){
        String id = jwtUtil.getIdFromHttpServletRequest(request);
        UserInfoDo userInfo = userInfoService.findById(id);
        RepairItemDo repairItem = RepairItemDo.builder()
                .repairItemId(getRepairItemId())
                .orderUserId(userInfo.getId())
                .classroom(orderItemDto.getClassroom())
                .equipmentType(orderItemDto.getEquipmentType())
                .problem(orderItemDto.getProblem())
                .oderUserPhone(orderItemDto.getOderUserPhone() == null ? userInfo.getPhone() : orderItemDto.getOderUserPhone())
                .build();
        repairItem = repairItemRepository.save(repairItem);
        sendMessage(repairItem, userInfo.getOpenid(), "已下单", "无");
        return repairItem;
    }

    /**
     * 维修人员接单
     */
    public RepairItemDo order(String repairItemId, HttpServletRequest request){
        RepairItemDo repairItem = findByRepairItemId(repairItemId);
        UserInfoDo receiver = userInfoService.findById(jwtUtil.getIdFromHttpServletRequest(request));
        if(repairItem.getState() != 1){
            throw new BadRequestException(465, "接单失败，该订单未处于未接状态，订单状态：" + repairItem.getState());
        }
        repairItem.setReceiverUserId(receiver.getId());
        repairItem.setState(2);
        repairItem = repairItemRepository.save(repairItem);
        sendMessage(repairItem, receiver.getOpenid(), "已接单",  "接单人" + receiver.getName());
        sendMessage(repairItem, userInfoService.findById(repairItem.getOrderUserId()).getOpenid(), "已接单",  "接单人" + receiver.getName());
        return repairItem;
    }

    /**
     * 取消报修
     */
    public RepairItemDo cancelRepair(String repairItemId, HttpServletRequest request){
        UserInfoDo canceler = userInfoService.findById(jwtUtil.getIdFromHttpServletRequest(request));
        RepairItemDo repairItem = findByRepairItemId(repairItemId);
        if(canceler.getRole() == 1 && !StringUtils.equals(canceler.getId(), repairItem.getOrderUserId())){
            throw new UnauthorizedException("普通人员只能取消自己下的单");
        }
        if(repairItem.getState() != 1 || repairItem.getState() != 2){
            throw new BadRequestException(465, "取消保修失败，该订单未处于未处理或者处理中状态，订单状态：" + repairItem.getState());
        }
        repairItem.setState(4);
        repairItem = repairItemRepository.save(repairItem);
        sendMessage(repairItem, canceler.getOpenid(), "已取消", "取消者："+canceler.getName());
        sendMessage(repairItem, userInfoService.findById(repairItem.getReceiverUserId()).getOpenid(), "已取消", "取消者："+canceler.getName());
        return repairItem;
    }

    /**
     * 维护人员取消接单
     */
    public RepairItemDo cancelReceive(String repairItemId, HttpServletRequest request){
        RepairItemDo repairItem = findByRepairItemId(repairItemId);
        if(repairItem.getState() != 2){
            throw new BadRequestException(465, "该订单未处于处理中状态，目前状态:"+repairItem.getState());
        }
        repairItem.setState(1);
        repairItem.setReceiverUserId("");
        repairItem = repairItemRepository.save(repairItem);
        UserInfoDo canceler = userInfoService.findById(jwtUtil.getIdFromHttpServletRequest(request));
        sendMessage(repairItem, canceler.getOpenid(), "已取消", "取消者：" + canceler.getName());
        sendMessage(repairItem, userInfoService.findById(repairItem.getOrderUserId()).getOpenid(), "已取消", "取消者：" + canceler.getName());
        return repairItem;
    }

    /**
     * 完成报修
     */
    public RepairItemDo completeOrder(String repairItemId, HttpServletRequest request){
        RepairItemDo repairItem = findByRepairItemId(repairItemId);
        if(repairItem.getState() != 2){
            throw new BadRequestException(465, "该订单未处于处理中状态，目前状态:"+repairItem.getState());
        }
        repairItem.setState(3);
        repairItem = repairItemRepository.save(repairItem);
        UserInfoDo completer = userInfoService.findById(jwtUtil.getIdFromHttpServletRequest(request));
        sendMessage(repairItem, completer.getOpenid(), "已完成", "完成者：" + completer.getName());
        sendMessage(repairItem, userInfoService.findById(repairItem.getOrderUserId()).getOpenid(), "已完成", "完成者：" + completer.getName());
        return repairItem;
    }

    /**
     * 发送模板消息
     * @param state 订单状态
     * @param remark 备注
     */
    private void sendMessage(RepairItemDo repairItem, String openId, String state, String remark) {
        if (StringUtils.isNotBlank(openId)) {
            sendDYTemplateMessage.buildMapAndSend(openId, repairItem.getRepairItemId(),
                    new StringBuilder().append("地点：").append(repairItem.getClassroom()).append("\\n")
                            .append("问题：").append(repairItem.getEquipmentType()).append("-").append(repairItem.getProblem()),
                    state, repairItem.getCreateTime(), remark);
        }
    }

}
