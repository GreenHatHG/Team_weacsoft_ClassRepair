package team.weacsoft;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringRunner;
import team.weacsoft.repair.service.RepairItemExcelService;

/**
 * @author GreenHatHG
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class RepairItemTest {

    @Autowired
    private RepairItemExcelService repairItemService;

    @Test
    public void testExcel(){
        Resource resource = repairItemService.getExcel(1578486548508L, 1578486976219L);

    }
}
