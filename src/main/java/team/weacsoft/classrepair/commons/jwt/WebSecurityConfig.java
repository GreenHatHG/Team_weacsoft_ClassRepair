package team.weacsoft.classrepair.commons.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

/**
 * Spring Security 配置类，主要功能：配置哪些URL不需要认证，哪些需要认证
 * @author GreenHatHG
 */

@Configuration
@EnableWebSecurity
@Order(Ordered.HIGHEST_PRECEDENCE)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomAccessDeniedHandler customAccessDeniedHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.cors()
            // 由于使用的是JWT，我们这里不需要传统的session功能
            .and().csrf().disable().formLogin().disable().logout().disable().httpBasic().disable()
            // 因为使用了JWT，所以这里不管理Session
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and().authorizeRequests()
                .anyRequest().authenticated()

            .and().exceptionHandling().accessDeniedHandler(customAccessDeniedHandler);
        // 添加自定义 JWT 过滤器
        http.addFilterBefore(new JwtAuthenticationFilter(), BasicAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
//        web.ignoring()
//                .antMatchers("/**/public/**");
    }
}
