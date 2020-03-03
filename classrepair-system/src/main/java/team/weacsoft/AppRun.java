package team.weacsoft;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author GreenHatHG
 */
@SpringBootApplication
@MapperScan("team.weacsoft.*.mapper")
@EnableCaching
@EnableScheduling
public class AppRun {
    public static void main(String[] args) {
        SpringApplication.run(AppRun.class, args);
    }
}
