package team.weacsoft.classrepair.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.weacsoft.classrepair.bean.RepairItem;

/**
 * @author GreenHatHG
 **/

public interface RepairItemRepository extends JpaRepository<RepairItem, String> {

    RepairItem findByRepairItemId(String repairItemId);

}
