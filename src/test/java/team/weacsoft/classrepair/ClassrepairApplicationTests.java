package team.weacsoft.classrepair;

import cn.hutool.core.date.DateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import team.weacsoft.classrepair.bean.ClassRoom;
import team.weacsoft.classrepair.repository.ClassRoomRepository;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClassrepairApplicationTests {


    @Test
    public void contextLoads() {
        Date date = DateUtil.date();
        System.out.println(date);
    }

}
