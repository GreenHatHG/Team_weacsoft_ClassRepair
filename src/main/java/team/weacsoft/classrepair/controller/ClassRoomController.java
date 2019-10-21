package team.weacsoft.classrepair.controller;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.weacsoft.classrepair.bean.ClassRoom;
import team.weacsoft.classrepair.entity.Result;
import team.weacsoft.classrepair.entity.ResultFactory;
import team.weacsoft.classrepair.repository.ClassRoomRepository;

import java.io.Serializable;
import java.util.*;

/**
 * @author GreenHatHG
 **/

@RestController
@RequestMapping(value="${api}")
public class ClassRoomController {

    @Autowired
    private ClassRoomRepository classRoomRepository;

    //todo 优化：不必每次都生成，可以查缓存
    @GetMapping("/classrooms")
    public ResponseEntity<Result> getClassRooms(){
        List<ClassRoom> classRooms = null;
        try{
            classRooms = classRoomRepository.findAll();
        }catch (Exception e){
            e.printStackTrace();
            return ResultFactory.buildFailResult("获取数据失败，数据库异常"+"\n"+e.getMessage());
        }

        Map<String, Resp> map= new HashMap<>(11);
        for(ClassRoom classRoom : classRooms){
            if(classRoom.getState() == 0){
                continue;
            }
            if(map.get(classRoom.getBuild()) == null){
                map.put(classRoom.getBuild(), new Resp(classRoom.getBuild()));
            }
            Resp resp = map.get(classRoom.getBuild());
            resp.layer.add(classRoom.getFloor());

            //防止数据库出现重复数据时返回的数据也重复
            List<String> list= resp.room.get(classRoom.getFloor());
            if(!(list != null && list.contains(classRoom.getRoom()))){
                resp.room.add(classRoom.getFloor(), classRoom.getRoom());
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
        return ResultFactory.buildSuccessResult(resps);
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
}
