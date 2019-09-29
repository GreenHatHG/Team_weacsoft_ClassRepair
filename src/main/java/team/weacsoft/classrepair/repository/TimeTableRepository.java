package team.weacsoft.classrepair.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.weacsoft.classrepair.bean.TimeTable;

/**
 * @author GreenHatHG
 **/
public interface TimeTableRepository extends JpaRepository<TimeTable, String> {
}
