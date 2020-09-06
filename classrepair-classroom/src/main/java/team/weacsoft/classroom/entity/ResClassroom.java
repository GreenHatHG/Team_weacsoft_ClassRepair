package team.weacsoft.classroom.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @Description 提供给ClassroomService2，用来返回课室数据的返回数据类型
 * @Author 魔法はまだ解けない
 * @Date 2020/9/6
 */
@Data
public class ResClassroom {

    private String text;
    private String value;
    private HashMap<String,ResClassroom> hashMap;
    private ArrayList<ResClassroom> children;
    public ResClassroom(String text, String value){
        this.text = text;
        this.value = value;
    }

    public ResClassroom(String text, String value,HashMap<String,ResClassroom> hashMap) {
        this.text = text;
        this.value = value;
        this.hashMap=hashMap;
    }
    public ResClassroom(String text, String value,ArrayList<ResClassroom> arrayList) {
        this.text = text;
        this.value = value;
        this.children=arrayList;
    }
}
