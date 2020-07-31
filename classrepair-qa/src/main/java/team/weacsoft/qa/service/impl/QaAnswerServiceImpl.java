package team.weacsoft.qa.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import team.weacsoft.common.exception.EntityNotFoundException;
import team.weacsoft.common.utils.JsonUtil;
import team.weacsoft.qa.dto.reponse.UpdateGoodNumResp;
import team.weacsoft.qa.entity.QaAnswer;
import team.weacsoft.qa.mapper.QaAnswerMapper;
import team.weacsoft.qa.service.IQaAnswerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 故障详情表 服务实现类
 * </p>
 *
 * @author GreenHatHG
 * @since 2020-01-27
 */
@Service
public class QaAnswerServiceImpl extends ServiceImpl<QaAnswerMapper, QaAnswer> implements IQaAnswerService {

    @Override
    public UpdateGoodNumResp updateGoodNum(Integer id) {
        QaAnswer qaAnswer = this.getById(id);
        if(qaAnswer == null){
            throw new EntityNotFoundException("QaAnswer", "id", String.valueOf(id));
        }
        qaAnswer.setGoodNum(qaAnswer.getGoodNum()+1);
        this.updateById(qaAnswer);
        return (UpdateGoodNumResp) JsonUtil.getCopyDto(this.getById(id), new UpdateGoodNumResp());
    }

    @Override
    public List<QaAnswer> searchAnswers(String s) {
        char[] chars = s.toCharArray();
        StringBuilder stringBuilder=new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            if(chars[i]!=' '){
                stringBuilder.append(chars[i]);
                stringBuilder.append('%');
            }
        }
        return baseMapper.searchAnswers(stringBuilder.toString());
    }
}
