package edu.whu.learneur.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Aspect
@Component
public class LoggerAspect {
    /* 定义切入点 */
    @Pointcut("@within(ResourcesLogger)")
    public void pt() {};

    @Around("pt()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        Object ret;
        String methodName = pjp.getSignature().getName();
        try {
            ret = pjp.proceed();
        } catch (Throwable t) {
            log.warn(methodName + "在" + LocalDateTime.now() + "被错误调用, 错误信息如下:\n" + t.getCause());
            throw t;
        }
        log.info(methodName + "在" + LocalDateTime.now() + "被正常调用");
        return ret;
    }

}
