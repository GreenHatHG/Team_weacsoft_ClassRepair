package team.weacsoft.answer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import team.weacsoft.answer.domain.QaAnswerDo;
import team.weacsoft.answer.domain.dto.AddQaAnswerDto;
import team.weacsoft.answer.repository.QaAnswerRepository;
import team.weacsoft.common.exception.EntityNotFoundException;
import team.weacsoft.qatype.service.QaTypeService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author GreenHatHG
 */

@Component
public class QaAnswerService {

    private QaAnswerRepository qaAnswerRepository;
    private QaTypeService qaTypeService;

    @Autowired
    public QaAnswerService(QaAnswerRepository qaAnswerRepository, QaTypeService qaTypeService) {
        this.qaAnswerRepository = qaAnswerRepository;
        this.qaTypeService = qaTypeService;
    }

    public List<QaAnswerDo> addQaAnswer(List<AddQaAnswerDto> addQaAnswerDtos){
        List<QaAnswerDo> list = addQaAnswerDtos.stream()
                .map(addQaAnswerDto -> {
                    if(!qaTypeService.existsQaTypeById(addQaAnswerDto.getQaTypeId())){
                        throw new EntityNotFoundException("QaType", "qaTypeId", String.valueOf(addQaAnswerDto.getQaTypeId()));
                    }
                    return QaAnswerDo.builder()
                            .answer(addQaAnswerDto.getAnswer())
                            .answerRepair(addQaAnswerDto.getAnswerRepair())
                            .goodNum(addQaAnswerDto.getGoodNum())
                            .qaTypeId(addQaAnswerDto.getQaTypeId())
                            .question(addQaAnswerDto.getQuestion())
                            .sort(addQaAnswerDto.getSort()).build();
                        })
                .collect(Collectors.toList());
        return qaAnswerRepository.saveAll(list);
    }
}
