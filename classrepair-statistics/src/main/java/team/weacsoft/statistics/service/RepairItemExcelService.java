package team.weacsoft.statistics.service;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import team.weacsoft.common.exception.BadRequestException;
import team.weacsoft.repair.domain.RepairItemDo;
import team.weacsoft.repair.service.FileStorageService;
import team.weacsoft.repair.service.RepairItemService;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
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

    public Resource getExcel(Long startTime, Long endTime){
        // Load file as Resource
        toExcel(startTime, endTime);
        return fileStorageService.loadFileAsResource("repair_item.xlsx");
    }

    private void toExcel(Long startTime, Long endTime){
        EasyExcel.write("repair_item.xlsx", RepairItemDo.class)
                .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy()).sheet("订单表").doWrite(
                        filterByPeriod(startTime, endTime).orElseThrow(() -> new BadRequestException("还没有订单信息，不能生成excel")));
    }


    private Optional<List<RepairItemDo>> filterByPeriod(Long startTime, Long endTime){
        List<RepairItemDo> list = repairItemService.findAll();
        Optional<List<RepairItemDo>> optionalRepairItemDos = Optional.ofNullable(list.size() == 0 ? null : list);
        if(startTime == null || endTime == null){
            return optionalRepairItemDos;
        }
        return optionalRepairItemDos.map(repairItemDos -> repairItemDos.stream()
                        .filter(repairItemDo -> startTime <= repairItemDo.getUpdateTime()
                                && endTime >= repairItemDo.getUpdateTime())
                .collect(Collectors.toList()));
    }

}
