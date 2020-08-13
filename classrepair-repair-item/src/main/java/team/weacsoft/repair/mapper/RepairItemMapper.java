package team.weacsoft.repair.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.poi.ss.formula.functions.T;
import team.weacsoft.repair.dto.request.CommonRepairItemDto;
import team.weacsoft.repair.dto.request.ExcelRepariItemDto;
import team.weacsoft.repair.dto.response.StatisticsFromEquipment;
import team.weacsoft.repair.entity.OrderSearchEntity;
import team.weacsoft.repair.entity.PeriodStatistics;
import team.weacsoft.repair.entity.PushInfo;
import team.weacsoft.repair.entity.RepairItem;

import java.util.List;

/**
 * <p>
 * 报修表 Mapper 接口
 * </p>
 *
 * @author GreenHatHG
 * @since 2020-01-27
 */
public interface RepairItemMapper extends BaseMapper<RepairItem> {

    IPage<CommonRepairItemDto> getAllRepairItem(IPage<T> page);

    IPage<CommonRepairItemDto> getRepairItemByState(IPage<T> page, String receiver, Integer state,
                                                    String neReceiver);

    IPage<CommonRepairItemDto> getUserRepairItem(IPage<T> page, String orderer, Integer state);

    /**
     *
     * 模糊搜索订单
     * @param page  页数
     * @param orderSearchEntity
     * @return
     */
    IPage<CommonRepairItemDto> searchRepairItem(IPage<T> page, OrderSearchEntity orderSearchEntity);

    List<ExcelRepariItemDto> getList();

    @Select("SELECT ri.create_time, ri.classroom, qa.title FROM repair_item ri " +
            " LEFT JOIN qa_type qa ON ri.equipment_type = qa.id " +
            " WHERE ri.create_time >= #{startTime} and ri.create_time <= #{endTime}")
    List<PeriodStatistics> typeStatisticsDao(@Param("startTime") Long startTime, @Param("endTime") Long endTime);

    /**
     * 获取状态为1的订单
     */
    @Select("SELECT ri.create_time,ri.classroom,qa.title,ri.repair_item_id, ri.state,ri.problem,ui.`name` AS orderer_name,ui.phone AS orderer_phone " +
            " FROM repair_item ri" +
            " LEFT JOIN qa_type qa ON ri.equipment_type = qa.id" +
            " LEFT JOIN user_info ui ON ri.orderer = ui.id WHERE ri.state = 1")
    List<PushInfo> pushDao();

    List<StatisticsFromEquipment> getStatisList();
}
