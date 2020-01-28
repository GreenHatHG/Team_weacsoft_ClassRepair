package team.weacsoft.qa.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import team.weacsoft.common.exception.EntityNotFoundException;
import team.weacsoft.common.utils.JsonUtil;
import team.weacsoft.qa.dto.common.QaTypeAnswer;
import team.weacsoft.qa.dto.reponse.GetAllQaTypeResp;
import team.weacsoft.qa.dto.request.AddQaType;
import team.weacsoft.qa.entity.QaAnswer;
import team.weacsoft.qa.entity.QaType;
import team.weacsoft.qa.mapper.QaTypeMapper;
import team.weacsoft.qa.service.IQaAnswerService;
import team.weacsoft.qa.service.IQaTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 常见故障分类表 服务实现类
 * </p>
 *
 * @author GreenHatHG
 * @since 2020-01-27
 */

@Service
public class QaTypeServiceImpl extends ServiceImpl<QaTypeMapper, QaType> implements IQaTypeService {

    private IQaAnswerService qaAnswerService;

    @Autowired
    public QaTypeServiceImpl(IQaAnswerService qaAnswerService) {
        this.qaAnswerService = qaAnswerService;
    }

    @Override
    public List<QaTypeAnswer> getQaTypeAnswerById(Integer qaTypeId) {
        if(this.getById(qaTypeId) == null){
            throw new EntityNotFoundException("QaType", "id", qaTypeId.toString());
        }
        return getBaseMapper().getQaTypeAnswerById(qaTypeId);
    }

    @Override
    public List<GetAllQaTypeResp> getAllQaType() {
        List<QaType> qaTypes = this.list();
        List<GetAllQaTypeResp> list = new ArrayList<>();
        for (QaType qaType : qaTypes) {
            GetAllQaTypeResp getAllQaTypeResp = new GetAllQaTypeResp();
            BeanUtils.copyProperties(qaType, getAllQaTypeResp);
            list.add(getAllQaTypeResp);
        }
        return list;
    }

    @Override
    @Transactional
    public QaType addQaType(AddQaType qaType) {
        QaType qaTypeSave = new QaType();
        this.save((QaType) JsonUtil.getCopyDto(qaType, qaTypeSave));
        return qaTypeSave;
    }

    @Transactional
    @Override
    public List<QaAnswer> addQaAnswer(List<QaTypeAnswer> list) {
        List<QaAnswer> qaAnswers = list.stream()
                .map(qaTypeAnswer -> {
                    if(getById(qaTypeAnswer.getQaTypeId()) == null){
                        throw new EntityNotFoundException("QaType", "id", qaTypeAnswer.getQaTypeId().toString());
                    }
                    return QaAnswer.builder()
                            .answerPrivate(qaTypeAnswer.getAnswerPrivate())
                            .answerPublic(qaTypeAnswer.getAnswerPublic())
                            .qaTypeId(qaTypeAnswer.getQaTypeId())
                            .goodNum(qaTypeAnswer.getGoodNum())
                            .question(qaTypeAnswer.getQuestion())
                            .sort(qaTypeAnswer.getSort()).build();
                })
                .collect(Collectors.toList());
        qaAnswerService.saveBatch(qaAnswers);
        return qaAnswers;
    }
}
