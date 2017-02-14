package com.userfront.util;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;


@Aspect
@Component
public class UserFrontDaoLogging {
	
	public static Logger LOGGER = Logger.getLogger(UserFrontDaoLogging.class);
	
	
	@Around("execution(* com.userfront.dao.impl.*.*(..))")
	public Object movieLogging(ProceedingJoinPoint pjp)throws Throwable{
		
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		Object output = pjp.proceed();
		stopWatch.stop();
		LOGGER.info(pjp.getSignature().getName()+" method execution time is : "+stopWatch.getTotalTimeSeconds()+" seconds");
		return output;
		
	}

}
