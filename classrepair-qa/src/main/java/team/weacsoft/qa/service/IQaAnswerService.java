package team.weacsoft.qa.service;

import team.weacsoft.qa.dto.common.QaTypeAnswer;
import team.weacsoft.qa.dto.reponse.UpdateGoodNumResp;
import team.weacsoft.qa.entity.QaAnswer;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 故障详情表 服务类
 * </p>
 *
 * @author GreenHatHG
 * @since 2020-01-27
 */
public interface IQaAnswerService extends IService<QaAnswer> {

    /**
     * 方案采纳
     */
    UpdateGoodNumResp updateGoodNum(Integer id);
}
