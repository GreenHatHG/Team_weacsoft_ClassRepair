package team.weacsoft.classroom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import team.weacsoft.classroom.domain.ClassroomDo;

/**
 * @author GreenHatHG
 **/

@Component
public interface ClassroomRepository extends JpaRepository<ClassroomDo, String> {
    ClassroomDo findByClassId(int classId);
}
