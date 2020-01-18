package team.weacsoft.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 跨域过滤器
 *
 * @author GreenHatHG
 **/

@Component
public class GlobalCorsConfig implements WebMvcConfigurer {

    @Bean
    public CorsFilter corsFilter() {
        //1. 添加 CORS配置信息
        CorsConfiguration config = new CorsConfiguration();
        //放行哪些原始域
//        config.addAllowedOrigin("https://www.pistachiol.club:8084");
//        config.addAllowedOrigin("https://localhost:8080");
//        config.addAllowedOrigin("http://localhost:8080");
//        config.addAllowedOrigin("https://localhost:8081");
//        config.addAllowedOrigin("http://localhost:8081");
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        //是否发送 Cookie
        //config.setAllowCredentials();
        //放行哪些请求方式
//        config.addAllowedMethod("GET");
//        config.addAllowedMethod("POST");
        config.addAllowedMethod("*");
        //2. 添加映射路径
        UrlBasedCorsConfigurationSource corsConfigurationSource = new UrlBasedCorsConfigurationSource();
        corsConfigurationSource.registerCorsConfiguration("/**", config);
        //3. 返回新的CorsFilter
        return new CorsFilter(corsConfigurationSource);
    }
}
