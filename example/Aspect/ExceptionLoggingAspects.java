package com.example.Aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExceptionLoggingAspects {

	 @AfterThrowing(
	            pointcut = "execution(* com.example.logistics.serviceimpl.*.*(..))",
	            throwing = "exception"
	    )
	    public void logException(
	            JoinPoint joinPoint,
	            Exception exception) {

	        System.out.println(
	                "AOP EXCEPTION : Method -> "
	                + joinPoint.getSignature().getName()
	        );

	        System.out.println(
	                "AOP EXCEPTION : Error -> "
	                + exception.getMessage()
	        );
	    }
}
