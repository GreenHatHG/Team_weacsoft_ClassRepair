package team.weacsoft.classrepair;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import team.weacsoft.classrepair.bean.ClassRoom;
import team.weacsoft.classrepair.repository.ClassRoomRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClassrepairApplicationTests {

    @Autowired
    ClassRoomRepository classRoomRepository;
    @Test
    public void contextLoads() {
        ClassRoom classRoom = new ClassRoom();
        classRoom.setClassId("3");
        classRoomRepository.save(classRoom);
    }

}
