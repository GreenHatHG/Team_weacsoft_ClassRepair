package team.weacsoft;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import team.weacsoft.orders.entity.RepairItem;
import team.weacsoft.orders.mapper.OrderMapper;

import java.time.LocalDate;
import java.util.Date;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MyTest {
    /**
     * 测试获取教室接口2
     */
    @Autowired
    OrderMapper orderMapper;

    @Test
    public void test2(){
    }

}
