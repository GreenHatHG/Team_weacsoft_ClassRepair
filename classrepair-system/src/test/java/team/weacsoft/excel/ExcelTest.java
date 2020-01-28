package team.weacsoft.excel;

import com.alibaba.excel.EasyExcel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import team.weacsoft.qa.service.IQaAnswerService;

/**
 * @author GreenHatHG
 * @since 2020-01-27
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ExcelTest {

    @Autowired
    private IQaAnswerService qaAnswerService;

    @Test
    public void test(){
        String fileName = "C:\\Users\\cc\\Desktop\\qa_answer.xls";
        EasyExcel.read(fileName, QaAnswerExcel.class, new Listener(qaAnswerService)).sheet().doRead();
    }
}
