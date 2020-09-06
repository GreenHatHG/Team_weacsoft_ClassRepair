package team.weacsoft.classroom.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import team.weacsoft.classroom.entity.Classroom;
import team.weacsoft.classroom.entity.ResClassroom;
import team.weacsoft.classroom.mapper.ClassroomMapper;
import team.weacsoft.classroom.service.ClassroomService2;
import team.weacsoft.common.utils.JsonUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * 这是第二个版本的ClassroomService，提供新的获取课室内容的方法
 * @Author 魔法はまだ解けない
 * @Date 2020/9/6
 */
@Service
public class ClassroomServiceImpl2 extends ServiceImpl<ClassroomMapper, Classroom> implements ClassroomService2 {

    @Autowired
    ClassroomServiceImpl classroomServicel;

    @Cacheable("cache1")
    public ArrayList<ResClassroom> getClassrooms (){
        List<Classroom> list = classroomServicel.list();
        ArrayList<ResClassroom> resClassrooms = new ArrayList<>();
        HashMap<String, ResClassroom> stringResClassroomHashMap = new HashMap<>();
        //筛掉状态不佳的教室
        list.stream()
                //过滤器，筛选掉不良状态的classroom
                .filter((Classroom classroom)->classroom.getState() != 0)
                .forEach((Classroom classroom)->{//循环，构造一个前端要求的数据类型
                    //map若是没有表示building的ResClassroom元素，新建一个，并将楼号加入
                    if (stringResClassroomHashMap.get(classroom.getBuild())==null){
                        stringResClassroomHashMap
                                .put(classroom.getBuild(),new ResClassroom(classroom.getBuild(),classroom.getBuild(),new HashMap<>()));
                    }
                    //拿到第一级ResClassroom的子map
                    HashMap<String, ResClassroom> children = stringResClassroomHashMap.get(classroom.getBuild()).getHashMap();
                    //map若是没有表示floor的ResClassroom元素，新建一个，并将楼层加入
                    if (children.get(classroom.getFloor())==null){
                        children.put(classroom.getFloor(),new ResClassroom(classroom.getFloor(),classroom.getFloor(),new ArrayList<>()));
                    }

                    ArrayList<ResClassroom> arrayList = children.get(classroom.getFloor()).getChildren();
                    //将数据加入列表
//                    String a =classroom.getBuild()+"-"+classroom.getFloor()+ classroom.getRoom();
                    arrayList.add(new ResClassroom(classroom.getRoom(),classroom.getRoom()));
                });
            stringResClassroomHashMap.entrySet().forEach((Map.Entry<String,ResClassroom> a)-> {
            ResClassroom resClassroom = new ResClassroom(a.getValue().getText(), a.getValue().getValue(), new ArrayList<>());

            a.getValue().getHashMap().entrySet().stream().forEach((Map.Entry<String,ResClassroom> b)-> resClassroom.getChildren().add(b.getValue()));
            resClassrooms.add(resClassroom);
        });
        System.out.println(resClassrooms);
        return resClassrooms;
    }

}
