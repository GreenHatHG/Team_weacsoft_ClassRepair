package team.weacsoft.system.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import team.weacsoft.system.security.handler.JwtAccessDeniedHandler;
import team.weacsoft.system.security.handler.JwtAuthenticationEntryPoint;
import team.weacsoft.user.service.IUserInfoService;

/**
 * @author GreenHatHG
 */

@Configuration
@EnableWebSecurity
//在这里，prePostEnabled = true启用对方法级安全性的支持，并允许使用@PreAuthorize
//类似地启用@Secured注释，我们需要secureEnabled = true。
@EnableGlobalMethodSecurity(prePostEnabled = true)
// @ EnableGlobalMethodSecurity(securedEnabled = true)
public class JwtSecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    private IUserInfoService userInfoService;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors().and().csrf().disable()
                .authorizeRequests()
                .anyRequest().authenticated().and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .exceptionHandling().authenticationEntryPoint(new JwtAuthenticationEntryPoint())
                .accessDeniedHandler(new JwtAccessDeniedHandler());
        httpSecurity.addFilterBefore(jwtRequestFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    /**
     * 忽略对这些url的限制，不经过过滤器
     */
    @Override
    public void configure(WebSecurity web){
        web.ignoring().antMatchers("/**/notification/**",
                "/**/public/**",
                "/**/druid/**");
    }

    private JwtRequestFilter jwtRequestFilter(){
        return new JwtRequestFilter(userInfoService);
    }
}
