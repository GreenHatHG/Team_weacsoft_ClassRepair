package team.weacsoft.qa.service;

import team.weacsoft.qa.dto.common.QaTypeAnswer;
import team.weacsoft.qa.dto.reponse.GetAllQaTypeResp;
import team.weacsoft.qa.dto.request.AddQaType;
import team.weacsoft.qa.entity.QaAnswer;
import team.weacsoft.qa.entity.QaType;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 常见故障分类表 服务类
 * </p>
 *
 * @author GreenHatHG
 * @since 2020-01-27
 */
public interface IQaTypeService extends IService<QaType> {
    /**
     * 某分类下的所有常见问题
     * @param qaTypeId 分类id
     */
    List<QaTypeAnswer> getQaTypeAnswerById(Integer qaTypeId);

    /**
     * 获取故障分类列表
     */
    List<GetAllQaTypeResp> getAllQaType();

    /**
     * 添加故障分类
     */
    QaType addQaType(AddQaType qaType);

    /**
     * 删除故障分类
     */
    List<QaAnswer> addQaAnswer(List<QaTypeAnswer> list);

    /**
     * 修改故障分类
     */
    QaType drop(Integer id);

    /**
     * 修改故障分类
     */
    QaType update(QaType qaType);

}
