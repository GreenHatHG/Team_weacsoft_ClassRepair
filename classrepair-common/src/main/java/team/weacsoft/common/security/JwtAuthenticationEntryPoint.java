package team.weacsoft.common.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import team.weacsoft.common.utils.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author GreenHatHG
 */
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    /**
     * 当用户尝试访问需要权限才能的REST资源而不提供Token或者Token过期时，
     * 将调用此方法发送401响应以及错误信息
     */
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        JwtUtil.response401(response, authException);
    }


}
