package com.jalian.springdemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;
//
@Aspect
@Component
public class CRMLoggingAspect {

    private Logger myLogger = Logger.getLogger(getClass().getName());

    @Pointcut("execution(* com.jalian.springdemo.controller.*.*(..))")
    public void forControllerPackage() {

    }
    @Pointcut("execution(* com.jalian.springdemo.dao.*.*(..))")
    public void forDaoPackage() {

    }
    @Pointcut("execution(* com.jalian.springdemo.service.*.*(..))")
    public void forServicePackage() {

    }
    @Pointcut("forServicePackage() || forDaoPackage() || forControllerPackage()")
    public void forAppFlow() {

    }

    @Before("forAppFlow()")
    public void before(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().toShortString();
        myLogger.info("=====> in @Before: calling method" + methodName);

        Object[] args = joinPoint.getArgs();
        for (Object o : args) {
            myLogger.info("=======> argument: " + o);
        }
    }

    @AfterReturning(pointcut = "forAppFlow()", returning = "theResult")
    public void afterReturning(JoinPoint joinPoint, Object theResult) {
        String methodName = joinPoint.getSignature().toShortString();
        myLogger.info("=====> in @AfterReturning: calling method" + methodName);
        myLogger.info("=====> the result: " + theResult);
    }
}
