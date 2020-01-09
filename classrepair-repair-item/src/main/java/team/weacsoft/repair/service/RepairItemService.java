package team.weacsoft.repair.service;

import cn.hutool.core.date.DateUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import team.weacsoft.common.exception.EntityNotFoundException;
import team.weacsoft.repair.domain.RepairItemDo;
import team.weacsoft.repair.repository.RepairItemRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author GreenHatHG
 */

@Component
@Slf4j
public class RepairItemService {

    @Autowired
    private RepairItemRepository repairItemRepository;

    public RepairItemDo save(RepairItemDo orderItem){
        return repairItemRepository.save(orderItem);
    }

    /**
     * 生成订单id
     * @return
     */
    public String getRepairItemId(){
        //DateUtil.today():当前日期字符串，格式：yyyyMMdd
        return DateUtil.format(new Date(), "yyyyMMdd") + System.currentTimeMillis() / 100;
    }

    public RepairItemDo findByRepairItemId(String repairItemId){
        RepairItemDo repairItem = repairItemRepository.findByRepairItemId(repairItemId);
        if(repairItem == null){
            throw new EntityNotFoundException(RepairItemDo.class, "repairItemId", repairItemId);
        }
        return repairItem;
    }

    public List<RepairItemDo> findAll(){
        return repairItemRepository.findAll();
    }

    public void toExcel(){
        EasyExcel.write("repair_item.xlsx", RepairItemDo.class)
                .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy()).sheet("订单表").doWrite(findAll());
    }

    public List<RepairItemDo> getAllMissedOrder(){
        List<RepairItemDo> repairItemList = findAll();
        List<RepairItemDo> resp = new ArrayList<>();
        for(RepairItemDo repairItem : repairItemList){
            if("".equals(repairItem.getReceiverUserId())
                    && repairItem.getDeleteTime() == 0){
                resp.add((repairItem));
            }
        }
        return resp;
    }

}
