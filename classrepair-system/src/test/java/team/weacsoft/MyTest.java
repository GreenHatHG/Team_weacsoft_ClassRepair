package team.weacsoft;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import team.weacsoft.classroom.entity.ResClassroom;
import team.weacsoft.classroom.service.ClassroomService2;
import team.weacsoft.classroom.service.impl.ClassroomServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MyTest {

    /**
     * 个人测试
     */
    private void a(){
        ArrayList<String> strings = new ArrayList<>();
        strings.add("1");
        strings.add("2");
        strings.add("3");
        strings.add("4");
        System.out.println(strings.stream().anyMatch(s -> s.equals("5")));
    }
    @Autowired
    ClassroomServiceImpl classroomService;
    @Autowired
    ClassroomService2 classroomService2;
    /**
     * 测试获取教室接口
     */
    @Test
    public void test(){
        System.out.println(classroomService2.getClassrooms());
    }
    /**
     * 测试获取教室接口2
     */
    @Test
    public void test2(){
    }
}
