package com.mycompany.myapp.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.mycompany.myapp.controller.Exam12DBController;

@Component
@Aspect
public class RuntimeCheckAspect {
	private static final Logger LOGGER=LoggerFactory.getLogger(RuntimeCheckAspect.class);
	//PointCut
	//execution은 실행할때 public 메소드 중에서 리턴타입 상관없고 해당 클래스 안의 모든 메소드, 매개변수가 몇개든지 상관없다.
	@Pointcut("execution(public * com.mycompany.myapp.controller.Exam12DBController.*(..))") //실행할때 어드바이스를 실행해라 , 메소드 지정 . 어떤 메소드가 실행할때 횡단 관심사 코드를 넣어줄거냐
    private void runtimeCheckMethod(){}
	//위의 메소드의 이름은 아무 상관이 없다. 역할도 없다. 위의 정의한 것을 advice에 전달하기 위해 만들어짐
	
	
	//Advice
	@Around("runtimeCheckMethod()") //pointcut을 정의해 놓은 메소드 이름//around는 코드 사이에 들어간다는말
	public Object runtimeCheckAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
		//before code
		long startTime=System.nanoTime();
		Object result=joinPoint.proceed(); //실제 호출해야할 메소드를 호출하는 것, 오브젝트를 리턴한다. 다양한 타입이 가능하기 때문에 object타입으로 받음
		//after code
		long endTime=System.nanoTime();
		long time= endTime-startTime;
		String realMethod=joinPoint.getSignature().toShortString(); //실제메소드 이름
		LOGGER.info(realMethod+"의 실행시간 :"+time+"ns");
		return result;
	}	
}
