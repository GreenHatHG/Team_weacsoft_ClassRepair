package team.weacsoft.qa.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import team.weacsoft.qa.dto.common.QaTypeAnswer;
import team.weacsoft.qa.entity.QaType;

import java.util.List;

/**
 * <p>
 * 故障详情表 Mapper 接口
 * </p>
 *
 * @author GreenHatHG
 * @since 2020-01-27
 */
public interface QaTypeMapper extends BaseMapper<QaType> {

    @Select("SELECT qa.id as questionid, qa.qa_type_id, qa.answer_private, qa.answer_public, qa.good_num, qa.question, qa.sort" +
            " FROM qa_type qt LEFT JOIN qa_answer qa ON qt.id = qa.qa_type_id" +
            " where qt.id = #{id}")
    List<QaTypeAnswer> getQaTypeAnswerById(@Param("id")Integer qaTypeId);
}
