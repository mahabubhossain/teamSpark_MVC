package edu.mum.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

	// Isolates/restricts WHERE @Logging can be applied
	// @Pointcut("execution(* edu.mum.service..*(..))")
	// @Pointcut("execution(* edu.mum.service..update(..))") // - If you want to
	// AOP ONLY updates...IGNORES Save
/*	public void applicationMethod() {
	}

	@Pointcut("@annotation(edu.mum.aspect.annotation.Logging)")
	public void logging() {
		// System.out.println("Advice logging");
	}*/

	// @After("@annotation(edu.mum.aspect.annotation.Logging")

	// Remove logging() && AND it will do a "regular" execution Point cut.
	// @Before("logging() && applicationMethod()")
	// @Before("execution(* *.*.*..*(..))") // "indiscriminate" application Try
	// it!
	//@Before("within(edu.mum.service.impl) && logging()")
	@Before("execution(* edu.mum.service..*(..))")
	public void logResource(JoinPoint joinPoint) {
		Logger log = Logger.getLogger("");
		log.info("   **********     TARGET CLASS : service request "
				+ joinPoint.getSignature().getName() + "    **********");
		System.out.println();
		System.out.println("   **********     TARGET CLASS : "
				+ joinPoint.getSignature().getDeclaringTypeName() + "."
				+ joinPoint.getSignature().getName() + "    **********");
	}
	
	/*@Before("within(edu.mum.rest.service.impl) && logging()")
	public void logRestResource(JoinPoint joinPoint) {
		Logger log = Logger.getLogger("");
		log.info("   **********     TARGET CLASS : REST service request "
				+ joinPoint.getSignature().getName() + "    **********");
		System.out.println();
		System.out.println("   **********     TARGET CLASS : "
				+ joinPoint.getSignature().getDeclaringTypeName() + "."
				+ joinPoint.getSignature().getName() + "    **********");
	}*/

}
