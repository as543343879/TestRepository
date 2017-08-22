package com.mycompany.myapp.websocket;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Controller
public class ChatHandler extends TextWebSocketHandler{

	private static final Logger LOGGER = LoggerFactory.getLogger(ChatHandler.class);
	private List<WebSocketSession> list = new Vector<>(); //멀티스레드 환경이라 안전하게 싱크로나이즈된걸로 사용
	//리스트에 세션이 저장될때는 접속시에 실행되는 established 메소드이다.

	
	
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
		for(WebSocketSession wss:list){
			wss.sendMessage(message);
		}
	}
	//연결이 끊겼을때 실행
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		LOGGER.info("");
		list.remove(session);
	}
}
