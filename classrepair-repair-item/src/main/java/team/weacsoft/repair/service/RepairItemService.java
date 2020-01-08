package team.weacsoft.repair.service;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import team.weacsoft.common.exception.EntityNotFoundException;
import team.weacsoft.repair.domain.RepairItemDo;
import team.weacsoft.repair.repository.RepairItemRepository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author GreenHatHG
 */

@Component
@Slf4j
public class RepairItemService {

    @Autowired
    private RepairItemRepository repairItemRepository;

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

    /**
     * 报修
     */
    public void save(RepairItemDo orderItem){
        repairItemRepository.save(orderItem);
    }

    /**
     * 接单
     * @param repairItem
     */
    public void update(RepairItemDo repairItem){
        repairItemRepository.save(repairItem);
    }

    /**
     * 生成订单id
     * @return
     */
    public String getRepairItemId(){
        Date date = new Date();
        return sdf.format(date) + System.currentTimeMillis() / 100;
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

    public List<Map<String, String>> getAllMissedOrder(){
        List<RepairItemDo> repairItemList = findAll();
        List<Map<String, String>> resp = new ArrayList<>();
        for(RepairItemDo repairItem : repairItemList){
            if("".equals(repairItem.getReceiverUserId())
                    && repairItem.getDeleteTime() == 0){
                resp.add(repairItemFilter(repairItem));
            }
        }
        return resp;
    }

    private Map<String, String> repairItemFilter(RepairItemDo repairItem){
        return ImmutableMap.<String, String>builder()
                .put("id", repairItem.getId())
                .put("repairItemId", repairItem.getRepairItemId())
                .put("classroom", repairItem.getClassroom())
                .put("equipmentType", repairItem.getEquipmentType())
                .put("problem", repairItem.getProblem())
                .build();
    }
}
