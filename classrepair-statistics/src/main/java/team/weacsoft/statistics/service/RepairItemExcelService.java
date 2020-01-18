package team.weacsoft.statistics.service;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import team.weacsoft.repair.domain.RepairItemDo;
import team.weacsoft.repair.service.FileStorageService;
import team.weacsoft.repair.service.RepairItemService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author GreenHatHG
 */

@Component
public class RepairItemExcelService {

    private RepairItemService repairItemService;
    private FileStorageService fileStorageService;

    @Autowired
    public RepairItemExcelService(RepairItemService repairItemService, FileStorageService fileStorageService) {
        this.repairItemService = repairItemService;
        this.fileStorageService = fileStorageService;
    }

    public Resource getExcel(long startTime, long endTime){
        // Load file as Resource
        toExcel(startTime, endTime);
        return fileStorageService.loadFileAsResource("repair_item.xlsx");
    }

    private void toExcel(long startTime, long endTime){
        EasyExcel.write("repair_item.xlsx", RepairItemDo.class)
                .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy()).sheet("订单表").doWrite(filterByPeriod(startTime, endTime));
    }


    private List<RepairItemDo> filterByPeriod(long startTime, long endTime){
        if(startTime == 0 && endTime == 0){
                return repairItemService.findAll();
        }
        return repairItemService.findAll().stream()
                .filter(repairItemDo -> startTime <= repairItemDo.getUpdateTime() && endTime >= repairItemDo.getUpdateTime())
                .collect(Collectors.toList());
    }
}
