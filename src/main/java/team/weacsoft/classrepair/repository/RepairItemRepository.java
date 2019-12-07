package team.weacsoft.classrepair.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import team.weacsoft.classrepair.bean.RepairItem;

/**
 * @author GreenHatHG
 **/

@Component
public interface RepairItemRepository extends JpaRepository<RepairItem, String> {

    RepairItem findByRepairItemId(String repairItemId);

}
