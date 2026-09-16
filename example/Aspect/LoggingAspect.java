package com.example.Aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    // Runs before every method in serviceimpl package
    @Before("execution(* com.example.logistics.serviceimpl.*.*(..))")
    public void beforeMethod(JoinPoint joinPoint) {

        System.out.println(
                "AOP BEFORE : Method started -> "
                + joinPoint.getSignature().getName()
        );
    }

    // Runs after every method in serviceimpl package
    @After("execution(* com.example.logistics.serviceimpl.*.*(..))")
    public void afterMethod(JoinPoint joinPoint) {

        System.out.println(
                "AOP AFTER : Method completed -> "
                + joinPoint.getSignature().getName()
        );
    }
}

