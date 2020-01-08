package team.weacsoft.repair.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import team.weacsoft.repair.domain.RepairItemDo;

/**
 * @author GreenHatHG
 **/

@Component
public interface RepairItemRepository extends JpaRepository<RepairItemDo, String> {

    RepairItemDo findByRepairItemId(String repairItemId);
}
