package team.weacsoft.repair.service.impl;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.weacsoft.common.utils.JwtUtil;
import team.weacsoft.repair.entity.RepairItem;
import team.weacsoft.repair.service.BaseRepairItemService;
import team.weacsoft.user.entity.UserInfo;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户报修
 * @author GreenHatHG
 * @since 2020-01-27
 */
@Service
public class AddRepairItemServiceImpl extends BaseRepairItemService {

    @Autowired
    private SimpMessagingTemplate wsTemplate;

    @Transactional
    public RepairItem getRepairItem(RepairItem repairItem, HttpServletRequest request) {
        UserInfo userInfo = userInfoService.getById(JwtUtil.getIdFromRequest(request));
        repairItem.setRepairItemId(getRepairItemId());
        repairItem.setOrderer(userInfo.getId());
        if(repairItem.getOrdererPhone() == null){
            repairItem.setOrdererPhone(userInfo.getPhone());
        }
        this.save(repairItem);
        sendMessage(repairItem, userInfo.getOpenid(), "已下单", "无");
        return repairItem;
    }

    public void websocket(RepairItem repairItem){
        wsTemplate.convertAndSend("/topic/repair_item", JSON.toJSON(repairItem));
    }

}
