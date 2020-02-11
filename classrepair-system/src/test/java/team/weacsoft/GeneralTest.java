package team.weacsoft;

import cn.hutool.crypto.SecureUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author GreenHatHG
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class GeneralTest {

    @Test
    public void test(){
        String id = "$argon2i$v=19$m=65536,t=10,p=1$gQ2tGl0Fhsf/AJ03asZ1WA$OwLrfVJTmGihNKjQ3t8LpcWomhUPMTznoDaL3Ycfjho";
        System.out.println(SecureUtil.sha256(SecureUtil.sha1(id)));
    }
}
