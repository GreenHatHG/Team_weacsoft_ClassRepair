package team.weacsoft;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import team.weacsoft.statistics.service.StatisticsService;

/**
 * @author GreenHatHG
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class GeneralTest {

    @Autowired
    private StatisticsService statisticsService;

    @Test
    public void test(){
        statisticsService.getStatisticsByperiod(null, null);
    }
}
