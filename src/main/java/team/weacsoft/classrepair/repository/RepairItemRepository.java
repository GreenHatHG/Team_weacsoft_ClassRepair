package team.weacsoft.classrepair.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.weacsoft.classrepair.bean.RepairItem;

/**
 * @author GreenHatHG
 **/

public interface RepairItemRepository extends JpaRepository<RepairItem, String> {

    RepairItem findByRepairItemId(String repairItemId);
//    @Override
//    Optional<RepairItem> findById(String id);

//    RepairItem findByOrderId(String orderId);
//
//    List<RepairItem> findByClassroom(String classroom);
//
//    List<RepairItem> findByType(String type);
//
//    List<RepairItem> findByPhone(String phone);
//
//    List<RepairItem> findByState(int status);
//
//    Page<RepairItem> findByReceiverId(String orderId, Pageable pageable);
//
//    @Transactional
//    @Modifying
//    @Query(value = "update order_item set repair_id = ?1 where order_item.order_id = ?2", nativeQuery=true)
//    void updateRepairIdByOrderId(int repairid, String orderid);
//
//    @Transactional
//    @Modifying
//    @Query(value = "delete from order_item where order_item.order_id = ?1", nativeQuery=true)
//    void deleteByOrderId(String orderId);

}
