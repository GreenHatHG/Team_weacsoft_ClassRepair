package team.weacsoft.classrepair;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import team.weacsoft.classrepair.bean.ClassRoom;
import team.weacsoft.classrepair.repository.ClassRoomRepository;
import team.weacsoft.classrepair.repository.UserInfoRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ClassrepairApplicationTests {

    public ClassrepairApplicationTests() {
        MDC.put("username", "1243");
    }

    @Autowired
    ClassRoomRepository classRoomRepository;

    @Autowired
    UserInfoRepository userInfoRepository;

    @Test
    public void addClassRoom() {
        String s = "A1\t1\t02\n" +
                "A1\t1\t03\n" +
                "A1\t1\t04\n" +
                "A1\t1\t05\n" +
                "A1\t1\t07\n" +
                "A1\t1\t08\n" +
                "A1\t1\t09\n" +
                "A1\t1\t10\n" +
                "A1\t2\t02\n" +
                "A1\t2\t03\n" +
                "A1\t2\t04\n" +
                "A1\t2\t05\n" +
                "A1\t2\t07\n" +
                "A1\t2\t08\n" +
                "A1\t2\t09\n" +
                "A1\t2\t10\n" +
                "A1\t3\t02\n" +
                "A1\t3\t03\n" +
                "A1\t3\t04\n" +
                "A1\t3\t05\n" +
                "A1\t3\t07\n" +
                "A1\t3\t08\n" +
                "A1\t3\t09\n" +
                "A1\t3\t10\n" +
                "A1\t4\t02\n" +
                "A1\t4\t03\n" +
                "A1\t4\t04\n" +
                "A1\t4\t05\n" +
                "A1\t4\t08\n" +
                "A1\t4\t09\n" +
                "A1\t4\t10\n" +
                "A2\t1\t02\n" +
                "A2\t1\t03\n" +
                "A2\t1\t04\n" +
                "A2\t1\t05\n" +
                "A2\t1\t07\n" +
                "A2\t1\t09\n" +
                "A2\t1\t10\n" +
                "A2\t2\t02\n" +
                "A2\t2\t03\n" +
                "A2\t2\t04\n" +
                "A2\t2\t05\n" +
                "A2\t2\t07\n" +
                "A2\t2\t08\n" +
                "A2\t2\t09\n" +
                "A2\t2\t10\n" +
                "A2\t3\t02\n" +
                "A2\t3\t03\n" +
                "A2\t3\t04\n" +
                "A2\t3\t05\n" +
                "A2\t3\t07\n" +
                "A2\t3\t08\n" +
                "A2\t3\t09\n" +
                "A2\t3\t10\n" +
                "A2\t4\t02\n" +
                "A2\t4\t03\n" +
                "A2\t4\t04\n" +
                "A2\t4\t05\n" +
                "A2\t4\t08\n" +
                "A2\t4\t09\n" +
                "A2\t4\t10\n" +
                "A3\t1\t01\n" +
                "A3\t1\t02\n" +
                "A3\t1\t03\n" +
                "A3\t1\t04\n" +
                "A3\t1\t05\n" +
                "A3\t1\t08\n" +
                "A3\t1\t09\n" +
                "A3\t1\t10\n" +
                "A3\t2\t01\n" +
                "A3\t2\t02\n" +
                "A3\t2\t03\n" +
                "A3\t2\t04\n" +
                "A3\t2\t05\n" +
                "A3\t2\t08\n" +
                "A3\t2\t09\n" +
                "A3\t2\t10\n" +
                "A3\t3\t01\n" +
                "A3\t3\t02\n" +
                "A3\t3\t03\n" +
                "A3\t3\t04\n" +
                "A3\t3\t05\n" +
                "A3\t3\t08\n" +
                "A3\t3\t09\n" +
                "A3\t3\t10\n" +
                "A3\t4\t02\n" +
                "A3\t4\t03\n" +
                "A3\t4\t04\n" +
                "A3\t4\t09\n" +
                "A3\t4\t10\n" +
                "A4\t2\t01\n" +
                "A4\t2\t02\n" +
                "A4\t2\t03\n" +
                "A4\t2\t04\n" +
                "A4\t2\t05\n" +
                "A4\t2\t08\n" +
                "A4\t2\t09\n" +
                "A4\t2\t10\n" +
                "A4\t3\t01\n" +
                "A4\t3\t02\n" +
                "A4\t3\t03\n" +
                "A4\t3\t04\n" +
                "A4\t3\t05\n" +
                "A4\t3\t08\n" +
                "A4\t3\t09\n" +
                "A4\t3\t10\n" +
                "A4\t4\t01\n" +
                "A4\t4\t02\n" +
                "A4\t4\t03\n" +
                "A4\t4\t04\n" +
                "A4\t4\t05\n" +
                "A4\t4\t08\n" +
                "A4\t4\t09\n" +
                "A4\t4\t10\n" +
                "A5\t1\t01\n" +
                "A5\t1\t02\n" +
                "A5\t1\t03\n" +
                "A5\t1\t05\n" +
                "A5\t1\t07\n" +
                "A5\t1\t08\n" +
                "A5\t1\t09\n" +
                "A5\t1\t10\n" +
                "A5\t1\t11\n" +
                "A5\t1\t12\n" +
                "A5\t2\t01\n" +
                "A5\t2\t02\n" +
                "A5\t2\t03\n" +
                "A5\t2\t04\n" +
                "A5\t2\t05\n" +
                "A5\t2\t06\n" +
                "A5\t3\t01\n" +
                "A5\t3\t03\n" +
                "A5\t3\t04\n" +
                "A5\t3\t05\n" +
                "A5\t3\t06\n" +
                "A5\t4\t01\n" +
                "A5\t4\t03\n" +
                "A5\t4\t04\n" +
                "A5\t4\t05\n" +
                "A5\t4\t06\n" +
                "A6\t1\t01\n" +
                "A6\t3\t01\n" +
                "A6\t3\t03\n" +
                "A6\t3\t04\n" +
                "A6\t3\t05\n" +
                "A6\t3\t08\n" +
                "A6\t3\t11\n" +
                "A6\t3\t12\n" +
                "A6\t3\t15\n" +
                "A6\t3\t18\n" +
                "A6\t4\t01\n" +
                "A6\t4\t03\n" +
                "A6\t4\t04\n" +
                "A6\t4\t05\n" +
                "A6\t4\t08\n" +
                "A6\t4\t11\n" +
                "A6\t4\t12\n" +
                "A6\t4\t15\n" +
                "A6\t4\t18\n" +
                "A6\t5\t01\n" +
                "A6\t5\t02\n" +
                "A6\t5\t03\n" +
                "A6\t5\t08A\n" +
                "A6\t5\t08B\n";
        String[] ss = s.split("\n");
        for(String string: ss){
            String[] sss = string.split("\t");
            ClassRoom classRoom = new ClassRoom();
            classRoom.setClassId(RandomUtil.randomUUID());
            classRoom.setBuild(sss[0]);
            classRoom.setFloor(sss[1]);
            classRoom.setRoom(sss[2]);
            try{
                classRoomRepository.save(classRoom);
                System.out.println(classRoom + "保存成功");
            }catch (Exception e){
                System.out.println(classRoom + "保存失败");
            }

        }
    }

    @Test
    public void addUser(){
        log.error("123SDA");
//        log.error("test", "test2eqeq");

//        log.error("12");userid,
//        UserInfo userInfo = UserInfo.builder()
//                .name("林浩铌")
//                .sessionKey("a5CywnEAyIEFcBSaF4Bp6A==").build();
//        userInfo.setOpenid("o0Dsd5ApwqBIrc7TjFk8VUnzJipg");
////        userInfoRepository.save(userInfo);
//        MDC.put("userid", userInfo.getId());
//        MDC.put("operateContent", "测试记录日志");
//        log.info("123");
//        UserInfo userInfo = userInfoRepository.findByOpenid("o0Dsd5ApwqBIrc7TjFk8VUnzJipg");
//        userInfo.setAvatar("444");
//        userInfoRepository.save(userInfo);
    }
}
