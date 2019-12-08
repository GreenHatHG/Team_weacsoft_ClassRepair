package team.weacsoft.classrepair.commons.jwt;

import cn.hutool.json.JSONUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import team.weacsoft.classrepair.commons.dto.ResultFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 结果处理配置
 * @author GreenHatHG
 */

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler{

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(JSONUtil.toJsonStr(ResultFactory.buildUnauthorizedResult("未授权")));
        response.getWriter().flush();
        response.getWriter().close();
    }
}
