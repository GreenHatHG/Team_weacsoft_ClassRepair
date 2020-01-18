package team.weacsoft.answer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import team.weacsoft.answer.domain.QaAnswerDo;
import team.weacsoft.answer.domain.dto.AddQaAnswerDto;
import team.weacsoft.answer.repository.QaAnswerRepository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author GreenHatHG
 */

@Component
public class QaAnswerService {

    @Autowired
    private QaAnswerRepository qaAnswerRepository;

    public List<QaAnswerDo> addQaAnswer(List<AddQaAnswerDto> addQaAnswerDtos){
        List<QaAnswerDo> list = addQaAnswerDtos.stream()
                .map(addQaAnswerDto -> QaAnswerDo.builder()
                        .answer(addQaAnswerDto.getAnswer())
                        .answerRepair(addQaAnswerDto.getAnswerRepair())
                        .goodNum(addQaAnswerDto.getGoodNum())
                        .menuId(addQaAnswerDto.getMenuId())
                        .question(addQaAnswerDto.getQuestion())
                        .sort(addQaAnswerDto.getSort()).build())
                .collect(Collectors.toList());
        return qaAnswerRepository.saveAll(list);
    }
}
