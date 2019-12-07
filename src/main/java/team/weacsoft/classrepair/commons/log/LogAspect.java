package team.weacsoft.classrepair.commons.log;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import team.weacsoft.classrepair.commons.util.WxRequests;
import team.weacsoft.classrepair.service.UserInfoService;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;

/**
 * @author GreenHatHG
 */

@Aspect
@Component
@Slf4j
public class LogAspect {

    @Autowired
    private WxRequests wxRequests;

    @Autowired
    private UserInfoService userInfoService;

    //表示匹配带有自定义注解的方法
    @Pointcut("@annotation(team.weacsoft.classrepair.commons.log.Log)")
    public void pointcut() {}

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        //获取当前请求对象
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = Objects.requireNonNull(attributes).getRequest();

        MethodSignature signature = (MethodSignature)point.getSignature();
        Method method = signature.getMethod();

        Log userAction = method.getAnnotation(Log.class);

        Object result = null;
        result = point.proceed();

        log.info("Userid:{}，Module:{}，Operation:{}，Result:{}，Request_type:{}，Class:{}，Method:{}，Parameter:{}, Reponse:{}"
                , MDC.get("userTableId") == null ? "未设置" : MDC.get("userTableId"), userAction.module(), userAction.operation(), "成功", request.getMethod()
                    , point.getTarget().getClass().getName(),signature.getName(),getParameter(method, point.getArgs()), JSONUtil.parse(result));
        return  result;
    }

    /**
     * 配置异常通知
     */
    @AfterThrowing(pointcut = "pointcut()", throwing = "e")
    public void afterThrowing(JoinPoint point, Throwable e) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = Objects.requireNonNull(attributes).getRequest();

        MethodSignature signature = (MethodSignature)point.getSignature();
        Method method = signature.getMethod();

        Log userAction = method.getAnnotation(Log.class);

        log.info("Userid:{}，Module:{}，Operation:{}，Result:失败->{}，Request_type:{}，Class:{}，Method:{}，Parameter:{}"
                ,MDC.get("userTableId") == null ? "未设置" : MDC.get("userTableId"),userAction.module(), userAction.operation(), e.getMessage(), request.getMethod()
                , point.getTarget().getClass().getName(),signature.getName(),getParameter(method, point.getArgs()));
    }

    /**
     * 根据方法和传入的参数获取请求参数
     */
    private List<Object> getParameter(Method method, Object[] args) {
        List<Object> argList = new ArrayList<>();
        Parameter[] parameters = method.getParameters();
        for (int i = 0; i < parameters.length; i++) {
            //将RequestBody注解修饰的参数作为请求参数
            RequestBody requestBody = parameters[i].getAnnotation(RequestBody.class);
            if (requestBody != null) {
                argList.add(args[i].toString().replaceAll("\n", "")
                .replaceAll("\t", ""));
            }
            //将RequestParam注解修饰的参数作为请求参数
            RequestParam requestParam = parameters[i].getAnnotation(RequestParam.class);
            if (requestParam != null) {
                Map<String, Object> map = new HashMap<>();
                String key = parameters[i].getName();
                if (!StringUtils.isEmpty(requestParam.value())) {
                    key = requestParam.value();
                }
                map.put(key, args[i]);
                argList.add(map);
            }
        }
        if (argList.size() == 0) {
            return null;
        }
        return argList;
    }
}
