package org.pyatakov.t1studywebstarter.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LogTrackingAspect {

    @Before("@annotation(org.pyatakov.t1studywebstarter.aspect.annotation.LogTracking)")
    public void beforeAdvice() {
        log.debug("Before advice: Method execution is about to start.");
    }

    @AfterThrowing(pointcut = "@annotation(org.pyatakov.t1studywebstarter.aspect.annotation.LogTracking)", throwing = "error")
    public void afterThrowingAdvice(Throwable error) {
        log.error("AfterThrowing advice: An exception has been thrown: {}", error.getMessage());
    }

    @AfterReturning(pointcut = "@annotation(org.pyatakov.t1studywebstarter.aspect.annotation.LogTracking)", returning = "result")
    public void afterReturningAdvice(Object result) {
        log.info("AfterReturning advice: Method execution has completed successfully. Return value: {}", result);
    }

    @Around("@annotation(org.pyatakov.t1studywebstarter.aspect.annotation.LogTracking)")
    public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result;
        long start = System.currentTimeMillis();
        try {
            result = joinPoint.proceed();
        } catch (Throwable error) {
            log.error("AroundAdvice advice: An exception has been thrown: " + error.getMessage());
            throw error;
        }
        long end = System.currentTimeMillis();
        log.debug("The run time of the method was: {} milisecond", end - start);
        return result;
    }
}
