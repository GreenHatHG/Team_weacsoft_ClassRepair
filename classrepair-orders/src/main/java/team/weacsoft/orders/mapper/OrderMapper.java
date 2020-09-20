package team.weacsoft.orders.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Component;
import team.weacsoft.common.persistence.PageRequest;
import team.weacsoft.orders.entity.RepairItem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import team.weacsoft.orders.entity.RespOrder;

import java.util.List;

/**
 * <p>
 * 报修表 Mapper 接口
 * </p>
 *
 * @author GreenHatHG、魔法はまだ解けない
 * @since 2020-09-12
 */
@Component
public interface OrderMapper extends BaseMapper<RepairItem> {

    /**
     * 通过订单号获取指定订单
     * @return
     */
    RespOrder findOrderByOrderId(Integer id);

    /**
     * 可以根据参数获取订单
     * 不填，即不进行筛选
     * @param state   订单状态：1待处理，2已查看，3处理中，4已处理，5已取消
     * @param receiver 接单人id
     * @param orderer 下单人id
     * @return
     */
    IPage<RespOrder> AllOrders(IPage<T> page, Integer state, Integer receiver, Integer orderer);

    /**
     * 查找除个人的某状态订单
     * @param state
     * @return
     */
    IPage<RespOrder> OtherOrders(IPage<T> page,Integer state, Integer receiver, Integer orderer);

}
