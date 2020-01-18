package team.weacsoft.qatype.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import team.weacsoft.common.exception.EntityExistException;
import team.weacsoft.qatype.domain.QaTypeDo;
import team.weacsoft.qatype.domain.dto.AddQaTypeDto;
import team.weacsoft.qatype.repository.QaTypeRepository;


/**
 * @author GreenHatHG
 */

@Component
public class UpdateQaTypeService {

    @Autowired
    private QaTypeRepository qaTypeRepository;

    public QaTypeDo addQaType(AddQaTypeDto addQaTypeDto){
        QaTypeDo qaTypeDo = QaTypeDo.builder()
                .qaTypeId(addQaTypeDto.getQaTypeId())
                .remark(addQaTypeDto.getRemark())
                .sort(addQaTypeDto.getSort())
                .title(addQaTypeDto.getTitle()).build();
         try{
             qaTypeDo = qaTypeRepository.save(qaTypeDo);
         }catch (DataIntegrityViolationException e){
             throw new EntityExistException(QaTypeDo.class, "qaTypeId", String.valueOf(addQaTypeDto.getQaTypeId()));
         }
         return qaTypeDo;
    }
}
