package com.mycompany.myapp.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Controller
public class EchoHandler extends TextWebSocketHandler{

	private static final Logger LOGGER = LoggerFactory.getLogger(EchoHandler.class);

	//연결이 된후에 실행
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		LOGGER.info("");
	}
	
	//메세지를 받았을 때 실행
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		LOGGER.info("");
		//메세지를 받고나서 다시 보낸다.
		String strMessage=message.getPayload();
		TextMessage textMessage = new TextMessage(strMessage);
		session.sendMessage(textMessage);
	}
	//연결이 끊겼을때 실행
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		LOGGER.info("");
	}
}
