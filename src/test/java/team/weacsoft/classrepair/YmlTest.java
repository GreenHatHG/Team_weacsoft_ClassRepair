package team.weacsoft.classrepair;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

/**
 * @author GreenHatHG
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class YmlTest {

    @Value("${ignore}")
    private String[] configIgnores;

    @Test
    public void test(){
        System.out.println(Arrays.toString(configIgnores));
    }
}
