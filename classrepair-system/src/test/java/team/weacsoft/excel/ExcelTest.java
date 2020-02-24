package team.weacsoft.excel;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import team.weacsoft.statistics.service.RepairItemExcelService;

/**
 * @author GreenHatHG
 * @since 2020-01-27
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ExcelTest {

    @Autowired
    private RepairItemExcelService repairItemExcelService;

    @Test
    public void testNull(){
        repairItemExcelService.getExcel(0L, -1L);
    }
}
