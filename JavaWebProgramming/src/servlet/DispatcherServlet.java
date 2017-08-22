package servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DispatcherServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("DispatcherServlet_service() 실행");        //클라이언트가 요청할때마다 실행
	}
	
	@Override
	public void init(ServletConfig config){     //앞으로 servlet을 실행할때 사용할 초기 상태 셋팅 , 초기 1회만 실행함
		System.out.println("DispatcherServlet_init실행");
		String name1=config.getInitParameter("name1");
		String name2=config.getInitParameter("name2");
		System.out.println(name1);
		System.out.println(name2);
	}
}
