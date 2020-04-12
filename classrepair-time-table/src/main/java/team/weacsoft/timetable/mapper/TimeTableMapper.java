package team.weacsoft.timetable.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import team.weacsoft.timetable.dto.reponse.OnlineDto;
import team.weacsoft.timetable.entity.DutyUserInfo;
import team.weacsoft.timetable.entity.TimeTable;
import team.weacsoft.timetable.entity.response.ReceiverMailList;

import java.util.List;

/**
 * <p>
 * 值班表 Mapper 接口
 * </p>
 *
 * @author GreenHatHG
 * @since 2020-01-26
 */
public interface TimeTableMapper extends BaseMapper<TimeTable> {

    @Select("SELECT t.user_id , t.create_time, name, phone, identity_id, nick_name from time_table t" +
            " LEFT JOIN user_info u ON t.user_id = u.id where t.state=1")
    List<OnlineDto> getAllOnline();

    /**
     * 获得所有在值班的人员
     */
    @Select("SELECT ui.identity_id FROM time_table tt" +
            " LEFT JOIN user_info ui ON tt.user_id = ui.id WHERE tt.state = 1")
    List<DutyUserInfo> getDutyUserInfos();

    /**
     * 获得所有维护人员通讯录
     * @return
     */
    @Select("SELECT name,identity_id AS receiver_identity_id,phone from user_info WHERE role>1 and role<9")
    List<ReceiverMailList> getMaillist();
}
