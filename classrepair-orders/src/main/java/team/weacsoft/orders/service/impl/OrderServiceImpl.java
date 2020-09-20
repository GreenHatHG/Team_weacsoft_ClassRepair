package team.weacsoft.orders.service.impl;

import team.weacsoft.orders.entity.RepairItem;
import team.weacsoft.orders.mapper.OrderMapper;
import team.weacsoft.orders.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 报修表 服务实现类
 * </p>
 *
 * @author GreenHatHG、魔法はまだ解けない
 * @since 2020-09-12
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, RepairItem> implements OrderService {

}
