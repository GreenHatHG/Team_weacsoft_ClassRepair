package team.weacsoft.classroom.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import team.weacsoft.classroom.entity.Classroom;
import team.weacsoft.classroom.mapper.ClassroomMapper;
import team.weacsoft.classroom.service.IClassroomService;
import team.weacsoft.common.exception.EntityNotFoundException;

import java.io.Serializable;
import java.util.*;

/**
 * <p>
 * 课室表 服务实现类
 * </p>
 *
 * @author GreenHatHG
 * @since 2020-01-27
 */
@Service
public class ClassroomServiceImpl extends ServiceImpl<ClassroomMapper, Classroom> implements IClassroomService {

    @Override
    @Cacheable(cacheNames = "classroom", key="'getClassRooms'", unless = "#result == null || #result.size() <= 0")
    public List<Map<String, Object>> getClassRooms() {
        List<Classroom> classRooms = list();

        Map<String, Resp> map= new HashMap<>(11);
        for(Classroom classroom : classRooms){
            if(classroom.getState() == 0){
                continue;
            }
            if(map.get(classroom.getBuild()) == null){
                map.put(classroom.getBuild(), new Resp(classroom.getBuild()));
            }
            Resp resp = map.get(classroom.getBuild());
            resp.layer.add(classroom.getFloor());

            //防止数据库出现重复数据时返回的数据也重复
            List<String> list= resp.room.get(classroom.getFloor());
            if(!(list != null && list.contains(classroom.getRoom()))){
                resp.room.add(classroom.getFloor(), classroom.getRoom());
            }
        }
        List<Map<String, Object>> resps = new ArrayList<>(10);
        for(Map.Entry<String, Resp> entry: map.entrySet()){
            Map<String, Object> temp = new HashMap<>(4);
            Resp resp = entry.getValue();
            temp.put("build", resp.build);
            temp.put("floor", resp.layer);
            List<List<String>> lists = new ArrayList<>();
            for (Map.Entry<String, List<String>> item : resp.room.entrySet()){
                lists.add(item.getValue());
            }
            temp.put("room", lists);
            resps.add(temp);
        }
        return resps;
    }

    @Override
    public Classroom getClassroomById(Integer id) {
        Classroom classroom = this.getById(id);
        if(classroom == null){
            throw new EntityNotFoundException("Classroom", "id", String.valueOf(id));
        }
        return classroom;
    }

    @Override
    public Map<String, Integer> buildStatistics(){
        List<Classroom> classRooms = list();
        Map<String, Integer> buildNum = new HashMap<>(10);
        for (Classroom classroom : list()) {
            int oldCount = buildNum.getOrDefault(classroom.getBuild(), 0);
            buildNum.put(classroom.getBuild(), oldCount + 1);
        }
        return buildNum;
    }

    private static class Resp implements Serializable {
        String build;
        Set<String> layer;
        MultiValueMap<String, String> room;

        Resp(String build){
            this.build = build;
            layer = new HashSet<>();
            room = new LinkedMultiValueMap<>();
        }

        @Override
        public String toString() {
            return "Resp{" +
                    "build='" + build + '\'' +
                    ", layer=" + layer +
                    ", room=" + room +
                    '}';
        }
    }
}
