package team.weacsoft.classrepair.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import team.weacsoft.classrepair.bean.OrderItem;
import team.weacsoft.classrepair.commons.exception.DataBaseException;
import team.weacsoft.classrepair.contests.EventEnum;
import team.weacsoft.classrepair.repository.OrderItemRepository;

/**
 * @author GreenHatHG
 */

@Component
public class OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private OperationLogService operationLogService;

    private static final Logger log = LoggerFactory.getLogger(OrderItemService.class);

    public void save(OrderItem orderItem, String userId){
        try{
            orderItemRepository.save(orderItem);
        }catch (Exception e){
            log.error("OrderItemService", e);
            operationLogService.addLog(userId, EventEnum.ORDERITEM.event
                    , EventEnum.ORDERITEM_FAILED.event+"->保存到数据库出错");
            throw new DataBaseException("保存订单到数据库失败" + "-->" + e.getMessage());
        }
    }
}
