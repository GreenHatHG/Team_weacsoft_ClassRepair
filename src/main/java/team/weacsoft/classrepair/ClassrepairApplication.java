package team.weacsoft.classrepair;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ClassrepairApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClassrepairApplication.class, args);
    }

}
