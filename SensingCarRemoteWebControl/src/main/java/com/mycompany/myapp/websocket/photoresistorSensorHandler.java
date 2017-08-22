package com.mycompany.myapp.websocket;

import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.Vector;

import javax.annotation.PostConstruct;

import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapHandler;
import org.eclipse.californium.core.CoapObserveRelation;
import org.eclipse.californium.core.CoapResponse;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.mycompany.myapp.controller.HomeController;

@Controller
public class photoresistorSensorHandler extends TextWebSocketHandler implements ApplicationListener{

	private static final Logger LOGGER = LoggerFactory.getLogger(photoresistorSensorHandler.class);
	private List<WebSocketSession> list = new Vector<>(); //멀티스레드 환경이라 안전하게 싱크로나이즈된걸로 사용
	//리스트에 세션이 저장될때는 접속시에 실행되는 established 메소드이다.
	private CoapClient coapClient;
	private CoapObserveRelation coapObserveRelation;
	private String ipAddress=HomeController.getIpaddress();

	//만약이게 coap이라면 여기서 옵저버로 만들고 측정값 받아서 전송하게하면 됨?
	@PostConstruct
	public void init(){
		coapClient = new CoapClient();
		coapClient.setURI("coap://"+ipAddress+"/photoresistor");
	    coapObserveRelation=coapClient.observe(new CoapHandler() {
			
			@Override
			public void onLoad(CoapResponse response) {
				String json=response.getResponseText();
				JSONObject jsonObject=new JSONObject(json);
				double  doubleT= Double.parseDouble(jsonObject.getString("photoresistor"));
				int photoresistor =((int)doubleT);
				
				jsonObject=new JSONObject();
				jsonObject.put("time", getUTCTime(new Date().getTime()));//temperature정보밖에 없기때문에 time추가해줌
				jsonObject.put("photoresistor", photoresistor);
				json=jsonObject.toString();
				try{
				for(WebSocketSession session:list){
					session.sendMessage(new TextMessage(json));
				}
				}catch(Exception e){}
			}
			
			@Override
			public void onError() {
				
			}
		});
		
	}
	
	//연결이 된후에 실행
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		LOGGER.info("");
		list.add(session);
	}
	
	//메세지를 받았을 때 실행
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		LOGGER.info("");
	}
	//연결이 끊겼을때 실행
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		LOGGER.info("");
		list.remove(session);
	}
	//우리가 흔히보는 시간을 얻기위해서
	public long getUTCTime(long localTime){
		long utcTime=0;
		TimeZone tz = TimeZone.getDefault();
		try{
			int offset=tz.getOffset(localTime);
			utcTime=localTime+offset;
		}catch(Exception e){}
		return utcTime;
	}

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		
		if(event instanceof ContextClosedEvent){
			LOGGER.info("된다되");
				coapObserveRelation.proactiveCancel();
				coapClient.shutdown();
		}
		
	}
}
