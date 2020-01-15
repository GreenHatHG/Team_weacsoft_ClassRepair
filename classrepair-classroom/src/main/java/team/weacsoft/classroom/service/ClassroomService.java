package team.weacsoft.classroom.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import team.weacsoft.classroom.domain.ClassroomDo;
import team.weacsoft.classroom.repository.ClassroomRepository;

import java.io.Serializable;
import java.util.*;

/**
 * @author GreenHatHG
 */

@Component
@Slf4j
public class ClassroomService {

    @Autowired
    private ClassroomRepository classroomRepository;

    public List<ClassroomDo> findAll(){
        return classroomRepository.findAll();
    }

    public List<Map<String, Object>> getClassRooms(){
        List<ClassroomDo> classRooms = findAll();

        Map<String, Resp> map= new HashMap<>(11);
        for(ClassroomDo classroom : classRooms){
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

    private static class Resp implements Serializable {
        String build = null;
        Set<String> layer = null;
        MultiValueMap<String, String> room = null;

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

    public ClassroomDo findByClassId(int classId){
        return classroomRepository.findByClassId(classId);
    }
}
