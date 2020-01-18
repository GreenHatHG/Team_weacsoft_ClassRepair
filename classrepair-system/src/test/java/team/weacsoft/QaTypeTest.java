package team.weacsoft;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import team.weacsoft.qatype.domain.QaTypeDo;
import team.weacsoft.qatype.repository.QaTypeRepository;

/**
 * @author GreenHatHG
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class QaTypeTest {

    @Autowired
    private QaTypeRepository qaTypeRepository;

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
            QaTypeDo qaTypeDo = new QaTypeDo();
            qaTypeDo.setQaTypeId(Integer.parseInt(splitS[i]));
            qaTypeDo.setSort(Integer.parseInt(splitS[i+1]));
            qaTypeDo.setTitle(splitS[i+2]);
            qaTypeRepository.save(qaTypeDo);
        }
    }
}
