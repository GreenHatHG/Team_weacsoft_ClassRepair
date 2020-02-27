package team.weacsoft.classroom.service;

import com.baomidou.mybatisplus.extension.service.IService;
import team.weacsoft.classroom.entity.Classroom;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课室表 服务类
 * </p>
 *
 * @author GreenHatHG
 * @since 2020-01-27
 */
public interface IClassroomService extends IService<Classroom> {

    /**
     * 获取课室信息
     */
     List<Map<String, Object>> getClassRooms();

    /**
     * 根据id获取某一课室
     * @param id
     * @return
     */
     Classroom getClassroomById(Integer id);

    /**
     * 统计每个build的数量
     */
    Map<String, Integer> buildStatistics();
}
