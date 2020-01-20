package team.weacsoft.repair.service;

import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import team.weacsoft.common.exception.EntityNotFoundException;
import team.weacsoft.common.utils.JwtUtil;
import team.weacsoft.common.wx.SendDYTemplateMessage;
import team.weacsoft.repair.domain.RepairItemDo;
import team.weacsoft.repair.domain.dto.OrderItemDto;
import team.weacsoft.repair.repository.RepairItemRepository;
import team.weacsoft.user.domain.Admin;
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
    private Admin admin;

    @Autowired
    public RepairItemService(RepairItemRepository repairItemRepository, JwtUtil jwtUtil, UserInfoSelectService userInfoService, SendDYTemplateMessage sendDYTemplateMessage, Admin admin) {
        this.repairItemRepository = repairItemRepository;
        this.jwtUtil = jwtUtil;
        this.userInfoService = userInfoService;
        this.sendDYTemplateMessage = sendDYTemplateMessage;
        this.admin = admin;
    }

    public RepairItemDo save(RepairItemDo orderItem){
        return repairItemRepository.save(orderItem);
    }

    /**
     * 生成订单id
     */
    public String getRepairItemId(){
        //DateUtil.today():当前日期字符串，格式：yyyyMMdd
        return DateUtil.format(new Date(), "yyyyMMdd") + System.currentTimeMillis() / 100;
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

    public List<RepairItemDo> findAllByOrderUserId(String id){
        return repairItemRepository.findByOrderUserId(id);
    }

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
        repairItem = save(repairItem);
        if(!StringUtils.equals(id, admin.getRootId())){
            sendDYTemplateMessage.buildMapAndSend(userInfo.getOpenid(), repairItem.getRepairItemId(),
                    new StringBuilder().append("地点：").append(repairItem.getClassroom()).append("；")
                            .append("问题：").append(repairItem.getEquipmentType()).append("-").append(repairItem.getProblem()),
                    repairItem.getState() == 1 ? "已下单" : "下单异常，请重新再试",
                    repairItem.getCreateTime(),
                    "无");
        }
        return repairItem;
    }
}
