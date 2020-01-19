package team.weacsoft.timetable.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import team.weacsoft.timetable.domain.TimeTableDo;

/**
 * @author GreenHatHG
 **/

@Component
public interface TimeTableRepository extends JpaRepository<TimeTableDo, String> {

}
