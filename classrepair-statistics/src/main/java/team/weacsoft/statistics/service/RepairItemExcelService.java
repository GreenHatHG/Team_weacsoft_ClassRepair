package team.weacsoft.statistics.service;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import team.weacsoft.common.exception.BadRequestException;
import team.weacsoft.repair.dto.request.ExcelRepariItemDto;
import team.weacsoft.repair.entity.RepairItemForExcel;
import team.weacsoft.repair.service.IRepairItemStateService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author GreenHatHG
 * @since 2020-01-28
 */

@Service
public class RepairItemExcelService {
    private IRepairItemStateService repairItemService;
    private FileStorageService fileStorageService;

    @Autowired
    public RepairItemExcelService(IRepairItemStateService repairItemService, FileStorageService fileStorageService) {
        this.repairItemService = repairItemService;
        this.fileStorageService = fileStorageService;
    }

    public Resource getExcel(Long startTime, Long endTime){
        // Load file as Resource
        toExcel(startTime, endTime);
        return fileStorageService.loadFileAsResource("repair_item.xlsx");
    }

    private void toExcel(Long startTime, Long endTime){
        EasyExcel.write("repair_item.xlsx", RepairItemForExcel.class)
                .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy()).sheet("订单表").doWrite(filterByPeriod(startTime, endTime));
    }

    private List<ExcelRepariItemDto> filterByPeriod(Long startTime, Long endTime){
        List<ExcelRepariItemDto> list = repairItemService.getList();
        if(list == null || list.size() == 0){
            throw new BadRequestException("还没有订单信息，不能生成excel");
        }
        if(startTime == null || endTime == null){
            return list;
        }
        return list.stream()
                .filter(repairItemDo -> startTime <= repairItemDo.getUpdateTime() && endTime >= repairItemDo.getUpdateTime())
                .collect(Collectors.toList());
    }
}
