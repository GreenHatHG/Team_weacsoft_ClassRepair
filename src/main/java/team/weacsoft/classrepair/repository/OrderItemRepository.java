package team.weacsoft.classrepair.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import team.weacsoft.classrepair.bean.OrderItem;

import java.util.List;
import java.util.Optional;

/**
 * @author GreenHatHG
 **/

public interface OrderItemRepository extends JpaRepository<OrderItem, String> {

    @Override
    Optional<OrderItem> findById(String id);

    List<OrderItem> findByUserId(int userId);

    OrderItem findByOrderId(String orderId);

    List<OrderItem> findByClassroom(String classroom);

    List<OrderItem> findByType(String type);

    List<OrderItem> findByPhone(String phone);

    List<OrderItem> findByRepairId(String repairId);

    List<OrderItem> findByStatus(int status);

    Page<OrderItem> findByOrderId(String orderId, Pageable pageable);

    @Transactional
    @Modifying
    @Query(value = "update order_item set repair_id = ?1 where order_item.order_id = ?2", nativeQuery=true)
    void updateRepairIdByOrderId(int repairid, String orderid);

    @Transactional
    @Modifying
    @Query(value = "delete from order_item where order_item.order_id = ?1", nativeQuery=true)
    void deleteByOrderId(String orderId);


}
