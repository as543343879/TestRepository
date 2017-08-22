package com.mycompany.myapp.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

@Component
@Aspect
public class LoginCheckAspect {
	//PointCut
	@Pointcut("execution(public * com.mycompany.myapp.controller.Exam14AopController.exam02*(..))") //exam02로 시작하는 메소드 전부 
	private void exam02Method(){}
   
	@Around("exam02Method()")
	public Object runtimeCheckAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
		
		RequestAttributes requestAttributes=RequestContextHolder.currentRequestAttributes();
		String mid=(String)requestAttributes.getAttribute("mid", requestAttributes.SCOPE_SESSION); //세션에서 정보 검색
		
		if(mid==null){
			requestAttributes.setAttribute("loginNeed", "로그인이 필요해.",requestAttributes.SCOPE_REQUEST); //request가 아닌 session에 저장할 필요가 없기 때문에 범위를 request로 한정.
			return "aop/exam02LoginForm";
		}else{
			Object result=joinPoint.proceed(); //리얼메소드 호출
			return result;
		}
	
		
	}
	

}
