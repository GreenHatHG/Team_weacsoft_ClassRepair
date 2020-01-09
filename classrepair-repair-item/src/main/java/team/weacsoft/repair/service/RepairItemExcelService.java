package team.weacsoft.repair.service;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import team.weacsoft.repair.domain.RepairItemDo;

/**
 * @author GreenHatHG
 */
public class RepairItemExcelService {

    @Autowired
    private RepairItemService repairItemService;

    public void toExcel(){
        EasyExcel.write("repair_item.xlsx", RepairItemDo.class)
                .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy()).sheet("订单表").doWrite(repairItemService.findAll());
    }
}
