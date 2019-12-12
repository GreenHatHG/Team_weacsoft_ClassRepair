package team.weacsoft.classrepair.service;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import team.weacsoft.classrepair.bean.RepairItem;
import team.weacsoft.classrepair.commons.exception.DataBaseException;
import team.weacsoft.classrepair.commons.exception.NotFoundException;
import team.weacsoft.classrepair.repository.RepairItemRepository;

import java.text.SimpleDateFormat;
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

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

    /**
     * 报修
     */
    public void save(RepairItem orderItem){
        try{
            repairItemRepository.save(orderItem);
        }catch (Exception e){
            log.error("保存报修单到数据库失败", e);
            throw new DataBaseException("保存报修单到数据库失败：" + e.getMessage());
        }
    }

    /**
     * 接单
     * @param repairItem
     */
    public void update(RepairItem repairItem){
        try {
            repairItemRepository.save(repairItem);
        }catch (Exception e){
            log.error("接单失败，数据库出错", e);
            throw new DataBaseException("接单失败：" + e.getMessage());
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
        RepairItem repairItem = repairItemRepository.findByRepairItemId(repairItemId);
        if(repairItem == null){
            throw new NotFoundException("找不到此报修单，repairItemId：" + repairItemId);
        }
        return repairItem;
    }

    public List<RepairItem> findAll(){
        return repairItemRepository.findAll();
    }

    public void toExcel(){
        EasyExcel.write("repair_item.xlsx", RepairItem.class)
                .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy()).sheet("订单表").doWrite(findAll());
    }
}
