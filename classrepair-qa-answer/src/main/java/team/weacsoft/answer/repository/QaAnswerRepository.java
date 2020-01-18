package team.weacsoft.answer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import team.weacsoft.answer.domain.QaAnswerDo;

/**
 * @author GreenHatHG
 */
@Component
public interface QaAnswerRepository extends JpaRepository<QaAnswerDo, String> {

}
