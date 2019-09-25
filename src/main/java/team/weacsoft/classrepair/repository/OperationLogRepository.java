package team.weacsoft.classrepair.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.weacsoft.classrepair.bean.OperationLog;
import team.weacsoft.classrepair.bean.UserInfo;

/**
 * @author GreenHatHG
 **/
public interface OperationLogRepository extends JpaRepository<OperationLog, String> {



}
