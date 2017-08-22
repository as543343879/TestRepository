package com.mycompany.myapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Exam16WebSocketController {

	@RequestMapping("/websocket/echoClient")
	public String echoClient(){
		return "websocket/echoClient";
	}
	@RequestMapping("/websocket/measureClient")
	public String measureClient(){
		return "websocket/measureClient";
	}

	@RequestMapping("/websocket/chatClient")
	public String chatClient(){
		return "websocket/chatClient";
	}
}
