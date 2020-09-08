package com.shinva.javastudy.aspectj;

import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentParser;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Objects;

/**
 * @author lrg
 * @date 2020/9/7
 **/
@Aspect
@Component
@Slf4j
public class AopLog {
    public static final String START_TIME = "request-start";
    private final String POINT_CUT = "execution(public * com.shinva.javastudy.controller.*Controller.*(..))";

    /**
     * 切入点
     */
    @Pointcut(POINT_CUT)
    public void log() {

    }

    /**
     * 前置操作
     *
     * @param point 切入点
     */
    @Before("log()")
    public void beforeLog(JoinPoint point) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = Objects.requireNonNull(attributes).getRequest();
        log.info("【请求 URL】:{}", request.getRequestURL());
        log.info("【请求 IP】:{}", request.getRemoteAddr());
        log.info("【请求 类名】:{}，【请求 方法名】:{}", point.getSignature().getDeclaringTypeName(), point.getSignature().getName());

        Map<String, String[]> parameterMap = request.getParameterMap();
        log.info("【请求 参数】:{}", JSONUtil.toJsonStr(parameterMap));
        long start = System.currentTimeMillis();
        request.setAttribute(START_TIME, start);
    }

    /**
     * 环绕操作
     * @param point 切入点
     * @return Object
     * @throws Throwable 异常
     */
    @Around("log()")
    public Object aroundLog(ProceedingJoinPoint point) throws Throwable {
        Object result = point.proceed();
        log.info("【返回值】:{}", JSONUtil.toJsonStr(result));
        return result;
    }

    /**
     * 后置操作
     */
    @AfterReturning("log()")
    public void afterReturning() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = Objects.requireNonNull(attributes).getRequest();
        Long start = (Long) request.getAttribute(START_TIME);
        Long end = System.currentTimeMillis();
        log.info("【请求耗时】:{}毫秒", end - start);

        String header = request.getHeader("User-Agent");
        UserAgent userAgent = UserAgentParser.parse(header);
        log.info("【浏览器类型】:{}，【操作系统】:{}，【原始User-Agent】:{}", userAgent.getBrowser().toString(),userAgent.getOs().toString(), header);
    }
}
