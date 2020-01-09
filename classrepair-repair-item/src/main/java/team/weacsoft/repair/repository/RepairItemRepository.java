package team.weacsoft.repair.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import team.weacsoft.repair.domain.RepairItemDo;

import java.util.List;

/**
 * @author GreenHatHG
 **/

@Component
public interface RepairItemRepository extends JpaRepository<RepairItemDo, String> {

    RepairItemDo findByRepairItemId(String repairItemId);

    List<RepairItemDo> findByOrderUserId(String id);

    /**
     * 查找某个id的未接或者是未接订单
     * @param o OrderUserId
     * @param r ReceiverUserId这里为""
     * @param d DeleteTime，为(Long)0
     * @param s State，为1
     * @param pageable 分页
     * @return
     */
    Page<RepairItemDo> findByOrderUserIdAndReceiverUserIdAndDeleteTimeAndState(String o, String r, Long d, int s, Pageable pageable);

    Page<RepairItemDo> findByReceiverUserIdAndDeleteTimeAndState(String r, Long d, int s, Pageable pageable);

    /**
     * 已接
     * @param o
     * @param r
     * @param d
     * @param s
     * @param pageable
     * @return
     */
    Page<RepairItemDo> findByOrderUserIdAndReceiverUserIdNotAndDeleteTimeAndState(String o, String r, Long d, int s, Pageable pageable);

    Page<RepairItemDo> findByReceiverUserIdNotAndDeleteTimeAndState(String r, Long d, int s, Pageable pageable);

}
