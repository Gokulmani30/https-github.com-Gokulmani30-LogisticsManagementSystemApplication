package com.example.Aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AuditAspect {

	   @AfterReturning(
	            pointcut = "execution(* com.example.logistics.serviceimpl.*.*(..))",
	            returning = "result"
	    )
	    public void auditMethod(
	            JoinPoint joinPoint,
	            Object result) {

	        System.out.println(
	                "AOP AUDIT : Method executed -> "
	                + joinPoint.getSignature().getName()
	        );

	        System.out.println(
	                "AOP AUDIT : Result -> "
	                + result
	        );
	    }
}
