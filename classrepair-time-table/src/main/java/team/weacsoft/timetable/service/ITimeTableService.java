package team.weacsoft.timetable.service;

import com.baomidou.mybatisplus.extension.service.IService;
import team.weacsoft.timetable.dto.reponse.OnlineDto;
import team.weacsoft.timetable.dto.reponse.SignInOrOutResp;
import team.weacsoft.timetable.entity.TimeTable;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 值班表 服务类
 * </p>
 *
 * @author GreenHatHG
 * @since 2020-01-26
 */
public interface ITimeTableService extends IService<TimeTable> {
    /**
     * 签到/签退
     */
    SignInOrOutResp signInOrOut(Integer state, HttpServletRequest request);

    /**
     * 返回当前值班中人员及签到时间等信息
     */
    List<OnlineDto> getAllOnline();

    /**
     * 获取我的当前值班状态
     */
    String getMyState(HttpServletRequest request);
}
