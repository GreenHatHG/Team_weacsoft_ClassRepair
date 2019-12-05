package team.weacsoft.classrepair.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import team.weacsoft.classrepair.bean.ClassRoom;

/**
 * @author GreenHatHG
 **/

@Component
public interface ClassRoomRepository extends JpaRepository<ClassRoom, String> {

}
