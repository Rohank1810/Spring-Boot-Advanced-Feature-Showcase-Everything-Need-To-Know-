package com.practice.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

	private static final Logger logger=LoggerFactory.getLogger(LoggingAspect.class);
	
//	@Pointcut("execution(* com.practice.service.StudentServiceImpl.*(..))")
//	public void studentServiceMethods() {};
	
	
	@Before("execution(* com.practice.service.StudentServiceImpl.*(..))")
	public void beforeAspect(JoinPoint joinPoint)
	{
		logger.info("Aspect Before Method call {} "+joinPoint.getSignature().getName());
	}
	@After("execution(* com.practice.service.StudentServiceImpl.*(..))")
	public void afterAspect(JoinPoint joinPoint)
	{
		logger.info("Aspect after Method call {} "+joinPoint.getSignature().getName());
	}
	
	@After("studentServiceMethods()")
    public void afterEachMethod(JoinPoint joinPoint)
    {
		logger.warn("Warn this methods end {} "+joinPoint.getSignature().getName());
    }
	
	@Around("studentServiceMethods()")
	public void around(ProceedingJoinPoint joint ) throws Throwable
	{
		int startTime=(int) System.currentTimeMillis()/100;
		logger.info("Before Method new call {} "+joint.getSignature().getName()+"Time "+startTime);
		Object res=joint.proceed();
		
		
	
		int endTime=(int) System.currentTimeMillis();
		logger.info("complete after Method new call {} "+joint.getSignature().getName()+"Time to do"+(startTime-endTime));
;	}
}
