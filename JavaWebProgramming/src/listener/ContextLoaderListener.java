package listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextLoaderListener implements ServletContextListener{// context�� ���÷��� �ɶ��� �����ϴ� ������

	@Override
	public void contextDestroyed(ServletContextEvent event) {  //���÷��� ������ ����
		System.out.println("ContextLoaderListener_contextInitialized ����");
	}

	@Override
	public void contextInitialized(ServletContextEvent event) { //���÷��� �����Ҷ� ����
		System.out.println("ContextLoaderListener_contextDestroyed ����");
		String key1=event.getServletContext().getInitParameter("key1"); //web.xml���� param ���� ��� ���
		System.out.println(key1);
	}
}
