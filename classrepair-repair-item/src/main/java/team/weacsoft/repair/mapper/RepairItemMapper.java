package team.weacsoft.repair.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Select;
import org.apache.poi.ss.formula.functions.T;
import team.weacsoft.repair.dto.reponse.GetAllMissedOrderDto;
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

    @Select("SELECT ri.create_time, ri.update_time, ri.classroom, ri.problem, ri.repair_item_id ,ri.orderer, ui.name, qa.title FROM repair_item ri" +
            " LEFT JOIN user_info ui ON ri.orderer = ui.id" +
            " LEFT JOIN qa_type qa ON ri.equipment_type=qa.id WHERE ri.state=1")
    IPage<GetAllMissedOrderDto> getAllMissedOrder(IPage<T> page);
}
