package team.weacsoft;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import team.weacsoft.qa.mapper.QaTypeMapper;
import team.weacsoft.user.service.impl.LoginServiceImpl;

import java.util.List;

/**
 * @author GreenHatHG
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class GeneralTest {

    @Autowired
    private LoginServiceImpl loginService;

    @Autowired
    private QaTypeMapper qaTypeService;


}
