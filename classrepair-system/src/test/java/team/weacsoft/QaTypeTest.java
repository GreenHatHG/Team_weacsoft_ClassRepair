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
            QaTypeDo qaType = QaTypeDo.builder()
                    .sort(Integer.valueOf(ss[i-1]))
                    .title(ss[i]).build();
            qaTypeRepository.save(qaType);
        }
    }
}
