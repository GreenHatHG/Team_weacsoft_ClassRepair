package team.weacsoft.classrepair.commons.jwt;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Jwt 认证过滤器
 * @author GreenHatHG
 */

@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private JwtUtil jwtUtil = new JwtUtil();

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse
            , FilterChain filterChain) throws ServletException, IOException {

        String jwt = jwtUtil.getJwtFromRequest(httpServletRequest);
        if (StrUtil.isNotBlank(jwt)) {
            jwtUtil.getId(jwt);
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        } else {
            throw new AccessDeniedException("token不正确, 请重新登录!");
        }
    }

}
