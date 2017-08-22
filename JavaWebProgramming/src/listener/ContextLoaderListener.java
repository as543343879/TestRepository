package listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextLoaderListener implements ServletContextListener{// context가 디플로이 될때를 감지하는 감시자

	@Override
	public void contextDestroyed(ServletContextEvent event) {  //디플로이 끝나면 실행
		System.out.println("ContextLoaderListener_contextInitialized 실행");
	}

	@Override
	public void contextInitialized(ServletContextEvent event) { //디플로이 실행할때 실행
		System.out.println("ContextLoaderListener_contextDestroyed 실행");
		String key1=event.getServletContext().getInitParameter("key1"); //web.xml에서 param 값을 얻는 방법
		System.out.println(key1);
	}
}
