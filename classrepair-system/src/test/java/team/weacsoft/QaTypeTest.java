package team.weacsoft;


import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import team.weacsoft.qa.entity.QaType;
import team.weacsoft.qa.service.impl.QaTypeServiceImpl;

/**
 * @author GreenHatHG
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class QaTypeTest {

    @Autowired
    private QaTypeServiceImpl qaTypeService;

    @Test
    public void addQaType(){
        String s = "10001, 1, '电脑'," +
                "10002, 2, '无线麦'," +
                "10003, 3, '鹅颈麦'," +
                "10004, 4, '投影'," +
                "10005, 5, '灯'," +
                "10006, 6, '风扇'," +
                "10007, 8, '其他问题'," +
                "10008, 7, '空调'";
        String replaceS = s.replaceAll("'", "").replaceAll("\n", "").replaceAll(" ", "");
        String[] splitS = replaceS.split(",");
        for(int i = 0; i < splitS.length; i+=3){
            QaType qaTypeDo = QaType.builder()
            .id(Integer.parseInt(splitS[i]))
            .sort(Integer.parseInt(splitS[i+1]))
            .title(splitS[i+2]).build();
            qaTypeService.save(qaTypeDo);
        }
    }
}
