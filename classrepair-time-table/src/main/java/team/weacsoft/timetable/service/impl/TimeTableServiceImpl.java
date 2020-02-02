package team.weacsoft.timetable.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.weacsoft.common.exception.BadRequestException;
import team.weacsoft.common.utils.JsonUtil;
import team.weacsoft.common.utils.JwtUtil;
import team.weacsoft.timetable.dto.reponse.OnlineDto;
import team.weacsoft.timetable.dto.reponse.SignInOrOutResp;
import team.weacsoft.timetable.entity.TimeTable;
import team.weacsoft.timetable.mapper.TimeTableMapper;
import team.weacsoft.timetable.service.ITimeTableService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 值班表 服务实现类
 * </p>
 *
 * @author GreenHatHG
 * @since 2020-01-26
 */
@Service
public class TimeTableServiceImpl extends ServiceImpl<TimeTableMapper, TimeTable> implements ITimeTableService {

    @Transactional
    @Override
    public SignInOrOutResp signInOrOut(Integer state, HttpServletRequest request) {
        String id = JwtUtil.getIdFromRequest(request);
        TimeTable timeTable = null;
        if (state == 1) {
            if (getOne(new QueryWrapper<TimeTable>().eq("user_id", id).eq("state", 1)) != null) {
                throw new BadRequestException(40020, "不能重复签到");
            }
            timeTable = TimeTable.builder()
                    .userId(Integer.valueOf(id))
                    .state(state).build();
            save(timeTable);
        } else if (state == 2) {
            timeTable = getOne(new QueryWrapper<TimeTable>().eq("user_id", id).eq("state", 1));
            if (timeTable == null) {
                throw new BadRequestException(40030, "该用户还没有签到，请先进行签到");
            }
            timeTable.setState(2);
            updateById(timeTable);
            timeTable = getOne(new QueryWrapper<TimeTable>().eq("user_id", id).eq("create_time", timeTable.getCreateTime()));
        }
        return (SignInOrOutResp) JsonUtil.getCopyDto(timeTable, new SignInOrOutResp());
    }

    @Override
    public List<OnlineDto> getAllOnline(){
        return this.getBaseMapper().getAllOnline();
    }

    @Override
    public String getMyState(HttpServletRequest request) {
        TimeTable timeTable = getOne(Wrappers.<TimeTable>lambdaQuery()
                                    .eq(TimeTable::getUserId, JwtUtil.getIdFromRequest(request))
                                    .orderByDesc(TimeTable::getCreateTime)
                                    .last("limit 1").select(TimeTable::getCreateTime, TimeTable::getState));
        return timeTable != null && timeTable.getState() == 1 ? "已签到" : "未签到";
    }
}
