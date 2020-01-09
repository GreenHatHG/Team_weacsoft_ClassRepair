package team.weacsoft.repair.repository;

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
}
