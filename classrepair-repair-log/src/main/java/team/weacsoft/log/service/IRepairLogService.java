package team.weacsoft.log.service;

import com.baomidou.mybatisplus.extension.service.IService;
import team.weacsoft.log.dto.reponse.SearchRepairLogDto;
import team.weacsoft.log.entity.RepairLog;

import java.util.Map;

/**
 * <p>
 * 报修表 服务类
 * </p>
 *
 * @author GreenHatHG
 * @since 2020-02-14
 */
public interface IRepairLogService extends IService<RepairLog> {

    /**
     * 提交订单维护日志
     */
    Map<String, Integer> addRepairLog(RepairLog repairLog);

    /**
     * 搜索维护日志
     */
    SearchRepairLogDto searchLog(String repairItemId);
}
