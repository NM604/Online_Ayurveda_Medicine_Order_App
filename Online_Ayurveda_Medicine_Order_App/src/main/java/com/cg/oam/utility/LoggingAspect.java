package com.cg.oam.utility;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.cg.oam.exception.InvalidDataException;

/**
 * The Class LoggingAspect.
 */
@Component
@Aspect
public class LoggingAspect {
	
	/** The Constant LOGGER. */
	public static final Log LOGGER = LogFactory.getLog(LoggingAspect.class);

	/**
	 * Log service exception.
	 *
	 * @param exception the exception
	 */
	@AfterThrowing(pointcut = "execution(* com.cg.oam.service.*Impl.*(..))", throwing = "exception")
	public void logServiceException(InvalidDataException exception) {
		LOGGER.error(exception.getMessage(), exception);
	}
	
	/**
	 * Log service exception.
	 *
	 * @param exception the exception
	 */
	@AfterThrowing(pointcut = "execution(* com.cg.oam.service.*Impl.*(..))", throwing = "exception")
	public void logServiceException(Exception exception) {
		LOGGER.error(exception.getMessage(), exception);
	}

}
