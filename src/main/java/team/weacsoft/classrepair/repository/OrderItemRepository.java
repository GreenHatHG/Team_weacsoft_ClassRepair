package team.weacsoft.classrepair.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.weacsoft.classrepair.bean.OrderItem;

/**
 * @author GreenHatHG
 **/
public interface OrderItemRepository extends JpaRepository<OrderItem, String> {
}
