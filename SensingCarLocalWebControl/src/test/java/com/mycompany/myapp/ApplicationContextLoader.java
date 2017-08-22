package com.mycompany.myapp;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

//테스팅을 수행하는 실행 클래스(스프링이 제공해주는)
@RunWith(SpringJUnit4ClassRunner.class)
//스프링 설정파일을 지정
@ContextConfiguration({
	"file:WebContent/WEB-INF/spring/applicationContext.xml",
	"file:WebContent/WEB-INF/spring/dispatcher-servlet.xml",
})
//내가 테스트 하고 있는 것은 웹이기 때문에달아주는거이고
//@WebAppConfiguration() 를 쓰려면  웹컨텐트가 src/main/webapp/WEB-INF에 존재해야 한다.
//WEB-INF가 다른폴더에 있다면 ()안에 폴더이름을 넣어줘야한다.
//WebContent폴더의 위치 지정
@WebAppConfiguration("WebContent")
public class ApplicationContextLoader {

}
