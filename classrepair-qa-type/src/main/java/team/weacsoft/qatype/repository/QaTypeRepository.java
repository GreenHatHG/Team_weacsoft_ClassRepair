package team.weacsoft.qatype.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import team.weacsoft.qatype.domain.QaTypeDo;

/**
 * @author GreenHatHG
 **/

@Component
public interface QaTypeRepository extends JpaRepository<QaTypeDo, String> {
    boolean existsByQaTypeId(int qaTypeId);
}
