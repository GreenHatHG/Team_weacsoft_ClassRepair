package team.weacsoft.log.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.weacsoft.common.exception.BadRequestException;
import team.weacsoft.common.exception.EntityNotFoundException;
import team.weacsoft.common.utils.JsonUtil;
import team.weacsoft.log.dto.reponse.FindRepairLogDto;
import team.weacsoft.log.dto.reponse.SearchRepairLogDto;
import team.weacsoft.log.entity.RepairLog;
import team.weacsoft.log.mapper.RepairLogMapper;
import team.weacsoft.log.service.IRepairLogService;
import team.weacsoft.repair.dto.response.StatisticsFromEquipment;
import team.weacsoft.repair.entity.RepairItem;
import team.weacsoft.repair.service.IRepairItemStateService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 报修表 服务实现类
 * </p>
 *
 * @author GreenHatHG
 * @since 2020-02-14
 */
@Service
public class RepairLogServiceImpl extends ServiceImpl<RepairLogMapper, RepairLog> implements IRepairLogService {

    private IRepairItemStateService repairItemService;

    @Autowired
    public RepairLogServiceImpl(IRepairItemStateService repairItemService) {
        this.repairItemService = repairItemService;
    }

    @Override
    public Map<String, Integer> addRepairLog(RepairLog repairLog) {
        if(repairItemService.getOne(Wrappers.<RepairItem>lambdaQuery()
                .eq(RepairItem::getRepairItemId, repairLog.getRepairItemId())) == null){
           throw new EntityNotFoundException("RepairItem", "RepairItemId", repairLog.getRepairItemId());
        }
        FindRepairLogDto repairLog1 = findRepairLog(repairLog.getRepairItemId());
        if (repairLog1==null){
            this.save(repairLog);
        }else {
            repairLog.setId(repairLog1.getId());
            this.updateById(repairLog);
        }
        return ImmutableMap.<String, Integer>builder().put("log_id", repairLog.getId()).build();
    }

    @Override
    public SearchRepairLogDto searchLog(String repairItemId) {
        return this.baseMapper.searchRepairLog(repairItemId);
    }

    @Override
    public FindRepairLogDto findRepairLog(String repairItemId) {
        RepairLog repairLog = this.getOne(Wrappers.<RepairLog>lambdaQuery().eq(RepairLog::getRepairItemId, repairItemId));
        if(repairLog == null){
            return null;
        }
        return (FindRepairLogDto) JsonUtil.getCopyDto(repairLog, new FindRepairLogDto());
    }
}
