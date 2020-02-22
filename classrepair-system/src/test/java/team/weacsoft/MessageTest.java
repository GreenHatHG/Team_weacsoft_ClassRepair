//package team.weacsoft;
//
//import com.google.common.collect.ImmutableMap;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//import team.weacsoft.common.wx.TemplateMessage;
//
///**
// * @author GreenHatHG
// */
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@Slf4j
//public class MessageTest {
//
//    @Autowired
//    protected TemplateMessage templateMessage;
//
//    @Test
//    public void test(){
//        templateMessage.buildMapAndSend(
//                ImmutableMap.<String, String> builder()
//                        .put("character_string6", "123")
//                        .put("thing1","12334")
//                        .put("phrase2", "已接单")
//                        .put("date3", "2019年10月1日 15:01")
//                        .put("thing5","ljwx").build());
//    }
//}
