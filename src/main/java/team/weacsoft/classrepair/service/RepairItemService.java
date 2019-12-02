package team.weacsoft.classrepair.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import team.weacsoft.classrepair.bean.RepairItem;
import team.weacsoft.classrepair.commons.exception.DataBaseException;
import team.weacsoft.classrepair.commons.exception.NotFoundException;
import team.weacsoft.classrepair.contests.EventEnum;
import team.weacsoft.classrepair.repository.RepairItemRepository;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author GreenHatHG
 */

@Component
public class RepairItemService {

    public RepairItemService() {
        MDC.put("operateResult", "success");
        MDC.put("operateModuleName","报修管理");
    }

    @Autowired
    private RepairItemRepository orderItemRepository;

    @Autowired
    private OperationLogService operationLogService;

    private static final Logger log = LoggerFactory.getLogger(RepairItemService.class);

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

    /**
     * 报修
     */
    public void save(RepairItem orderItem, String userId){
        try{
            MDC.put("operateContent",
                "增加报修单,订单id:"+orderItem.getRepairItemId()+"用户id:"+userId);
            orderItemRepository.save(orderItem);
            log.info("");
        }catch (Exception e){
            log.error("OrderItemService", e);
            MDC.put("operateResult", "fail");
//            operationLogService.addLog(userId, EventEnum.REPAIR.event
//                    , EventEnum.REPAIR_FAILED.event+"->保存到数据库出错");
            throw new DataBaseException("保存订单到数据库失败" + "-->" + e.getMessage());
        }
    }

    /**
     * 接单
     * @param repairItem
     */
    public void update(RepairItem repairItem){
        try {
            orderItemRepository.save(repairItem);
        }catch (Exception e){
            log.error("OrderItemService", e);
            operationLogService.addLog("", EventEnum.ORDER.event
                    , EventEnum.ORDER_FAILED.event+"->保存到数据库出错");
            throw new DataBaseException("更新订单到数据库失败" + "-->" + e.getMessage());
        }
    }


    /**
     * 生成订单id
     * @return
     */
    public String getRepairItemId(){
        Date date = new Date();
        return sdf.format(date) + System.currentTimeMillis() / 100;
    }

    public RepairItem findByRepairItemId(String repairItemId){
        RepairItem repairItem = orderItemRepository.findByRepairItemId(repairItemId);
        if(repairItem == null){
            throw new NotFoundException("找不到此报修单，repairItemId -->" + repairItemId);
        }
        return repairItem;
    }

}
