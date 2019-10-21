package team.weacsoft.classrepair.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.TimeZone;

/**
 * 设置JVM读取的时区为GMT+8,在项目启动时设置
 *
 * @author GreenHatHG
 **/

@Component
public class TimeZoneCommandLineRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {

        //获取中国时区
        final TimeZone zone = TimeZone.getTimeZone("GMT+8");
        //设置时区
        TimeZone.setDefault(zone);

    }
}