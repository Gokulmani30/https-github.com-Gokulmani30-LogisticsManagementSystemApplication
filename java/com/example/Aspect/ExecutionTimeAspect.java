package com.example.Aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExecutionTimeAspect {

	@Around("execution(* com.example.logistics.serviceimpl.*.*(..))")
    public Object calculateExecutionTime(
            ProceedingJoinPoint joinPoint) throws Throwable {

        long startTime = System.currentTimeMillis();

        Object result = joinPoint.proceed();

        long endTime = System.currentTimeMillis();

        long executionTime = endTime - startTime;

        System.out.println(
                "AOP EXECUTION TIME : "
                + joinPoint.getSignature().getName()
                + " took "
                + executionTime
                + " ms"
        );

        return result;
    }
}
