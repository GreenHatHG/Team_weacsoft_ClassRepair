package team.weacsoft.log.mapper;

import team.weacsoft.log.dto.reponse.SearchRepairLogDto;
import team.weacsoft.log.entity.RepairLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 报修表 Mapper 接口
 * </p>
 *
 * @author GreenHatHG
 * @since 2020-02-14
 */
public interface RepairLogMapper extends BaseMapper<RepairLog> {

    SearchRepairLogDto searchRepairLog(String repairItemId);
}
