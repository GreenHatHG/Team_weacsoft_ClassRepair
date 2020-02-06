package team.weacsoft.repair.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.poi.ss.formula.functions.T;
import team.weacsoft.repair.dto.request.CommonRepairItemDto;
import team.weacsoft.repair.entity.RepairItem;

/**
 * <p>
 * 报修表 Mapper 接口
 * </p>
 *
 * @author GreenHatHG
 * @since 2020-01-27
 */
public interface RepairItemMapper extends BaseMapper<RepairItem> {

    IPage<CommonRepairItemDto> getRepairItemByState(IPage<T> page, String receiver, Integer state,
                                                        String neReceiver);
}
