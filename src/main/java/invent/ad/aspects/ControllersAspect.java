package invent.ad.aspects;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class ControllersAspect {

	Logger aspectLogger=Logger.getLogger(ControllersAspect.class);
	
	@Before("execution(* invent.ad.controllers.*.get*(..))")
	public void logGetCalls(JoinPoint joinPoint){
		aspectLogger.info(joinPoint.getSignature().getName()+" executed");
	}
	
	
}
