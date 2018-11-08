package com.infosys.Employee_final.utility;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {
	@AfterThrowing(pointcut = "execution(* com.infosys.Employee_final.controller.*.*(..))", throwing = "exception")
	public void logExceptionFromController(Exception exception) throws Exception {		
		log(exception);		
	}
	
	@AfterThrowing(pointcut = "execution(* com.infosys.Employee_final.service.*.*(..))", throwing = "exception")
	public void logExceptionFromService(Exception exception) throws Exception {		
			log(exception);	
	}	
	
	private void log(Exception exception) {
		Logger logger = LoggerFactory.getLogger(this.getClass());
		logger.error(exception.getMessage());
	}
}
