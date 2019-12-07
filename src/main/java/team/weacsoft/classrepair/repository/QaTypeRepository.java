package team.weacsoft.classrepair.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import team.weacsoft.classrepair.bean.QaType;

/**
 * @author GreenHatHG
 **/

@Component
public interface QaTypeRepository extends JpaRepository<QaType, String> {

}
