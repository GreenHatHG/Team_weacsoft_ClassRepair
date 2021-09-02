package team.weacsoft.common.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import com.google.common.base.Optional;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import com.google.common.base.Function;
import com.google.common.base.Predicate;

/**
 * @Author 魔法はまだ解けない
 * @Date 2021/7/16
 */

@Configuration
@EnableSwagger2
@EnableKnife4j
public class SwaggerConfig {

    /**
     * 定义分隔符
     */
    private static final String splitor = ";";

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
//                .apis(RequestHandlerSelectors.basePackage("team.weacsoft.feedback.controller2"))
                .apis(basePackage(
                        "team.weacsoft.user.controller2" + splitor +
                                "team.weacsoft.timetable.controller2" + splitor +
                                "team.weacsoft.repair.controller2" + splitor +
                                "team.weacsoft.system.controller" + splitor +
                                "team.weacsoft.log.controller2" + splitor +
                                "team.weacsoft.qa.controller2" + splitor +
                                "team.weacsoft.orders.controller2" + splitor +
                                "team.weacsoft.material.controller2" + splitor +
                                "team.weacsoft.invitation.controller2" + splitor +
                                "team.weacsoft.classroom.controller2" + splitor +
                                "team.weacsoft.timetable.controller2"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("华广课室RESTful APIs")
                .description("华广课室接口文档")
                .termsOfServiceUrl("http://blog.didispace.com/")
                .contact("华广课室")
                .version("1.0")
                .build();
    }

    public static Predicate<RequestHandler> basePackage(final String basePackage) {
        return input -> declaringClass(input).transform(handlerPackage(basePackage)).or(true);
    }

    private static Function<Class<?>, Boolean> handlerPackage(final String basePackage) {
        return input -> {
            // 循环判断匹配
            for (String strPackage : basePackage.split(splitor)) {
                boolean isMatch = input.getPackage().getName().startsWith(strPackage);
                if (isMatch) {
                    return true;
                }
            }
            return false;
        };
    }

    private static Optional<? extends Class<?>> declaringClass(RequestHandler input) {
        return Optional.fromNullable(input.declaringClass());
    }

}
