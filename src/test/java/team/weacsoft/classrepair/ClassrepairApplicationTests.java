package team.weacsoft.classrepair;

import cn.hutool.core.util.RandomUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import team.weacsoft.classrepair.bean.ClassRoom;
import team.weacsoft.classrepair.bean.QaType;
import team.weacsoft.classrepair.bean.RepairItem;
import team.weacsoft.classrepair.bean.UserInfo;
import team.weacsoft.classrepair.repository.ClassRoomRepository;
import team.weacsoft.classrepair.repository.QaTypeRepository;
import team.weacsoft.classrepair.repository.RepairItemRepository;
import team.weacsoft.classrepair.repository.UserInfoRepository;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ClassrepairApplicationTests {

    @Autowired
    ClassRoomRepository classRoomRepository;

    @Autowired
    UserInfoRepository userInfoRepository;

    @Autowired
    QaTypeRepository qaTypeRepository;

    @Autowired
    RepairItemRepository repairItemRepository;

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
    public void addQaType(){
        String s = "1\t电脑\n" +
                "2\t无线麦\n" +
                "3\t鹅颈麦\n" +
                "4\t投影\n" +
                "5\t灯\n" +
                "6\t风扇\n" +
                "8\t其他问题\n" +
                "7\t空调\n";
        s = s.replaceAll("\n", "\t");
        String[] ss = s.split("\t");
        for(int i = 1; i < ss.length; i+=2){
            QaType qaType = QaType.builder()
                    .sort(Integer.valueOf(ss[i-1]))
                    .title(ss[i]).build();
            qaTypeRepository.save(qaType);
        }
    }

    @Test
    public void addUser(){
        UserInfo userInfo = UserInfo.builder()
                .name("林浩铌")
                .sessionKey("a5CywnEAyIEFcBSaF4Bp6A==").build();
//        UserInfo userInfo = new UserInfo();
//        userInfo.setName("林浩铌");
//        userInfo.setSessionKey("a5CywnEAyIEFcBSaF4Bp6A==");
        userInfo.setOpenid("o0Dsd5ApwqBIrc7TjFk8VUnzJipg");
        log.info(userInfo.toString());
        userInfoRepository.save(userInfo);
        userInfo = userInfoRepository.findByOpenid("o0Dsd5ApwqBIrc7TjFk8VUnzJipg");
        log.info(userInfo.toString());
    }

    private List<RepairItem> data(){
        return repairItemRepository.findAll();
    }

    @Test
    public void toExcel(){

        EasyExcel.write("repair_item.xlsx", RepairItem.class)
                .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy()).sheet("订单表").doWrite(data());
    }
}
