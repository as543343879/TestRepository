package com.mycompany.myapp.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.coap.MediaTypeRegistry;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
private static String ipAddress="192.168.3.42";
	
	public static String getIpaddress() {
	return ipAddress;
}

	@RequestMapping("/")
	public String home(Model model) {
		CoapClient coapClient = new CoapClient();
		JSONObject jsonObject = null;
		String json = null;
		CoapResponse coapResponse = null;
		
		//--------------------------------------------------
		
		/*
		jsonObject = new JSONObject();
		jsonObject.put("command", "status");
		json = jsonObject.toString();
		coapClient.setURI("coap://"+ipAddress+"/camera");
		coapResponse = coapClient.post(json, MediaTypeRegistry.APPLICATION_JSON);
		json = coapResponse.getResponseText();
		jsonObject = new JSONObject(json);
		model.addAttribute("leftright", jsonObject.getString("leftright"));
		model.addAttribute("updown", jsonObject.getString("updown")); 
		*/
		//-------------------------------------------------------
		jsonObject = new JSONObject();
		jsonObject.put("command", "status");
		json = jsonObject.toString();
		coapClient.setURI("coap://"+ipAddress+"/rgbled");
		coapResponse = coapClient.post(json, MediaTypeRegistry.APPLICATION_JSON);
		json = coapResponse.getResponseText();
		jsonObject = new JSONObject(json);
		model.addAttribute("red", jsonObject.getString("red"));
		model.addAttribute("green", jsonObject.getString("green"));
		model.addAttribute("blue", jsonObject.getString("blue"));
		//-------------------------------------------------------
		
		jsonObject = new JSONObject();
		jsonObject.put("command", "status");
		json = jsonObject.toString();
		coapClient.setURI("coap://"+ipAddress+"laseremitter");
		coapResponse = coapClient.post(json, MediaTypeRegistry.APPLICATION_JSON);
		json = coapResponse.getResponseText();
		jsonObject = new JSONObject(json);
		model.addAttribute("laseremitterStatus", jsonObject.getString("status"));
		
		//-------------------------------------------------------
		jsonObject = new JSONObject();
		jsonObject.put("command", "status");
		json = jsonObject.toString();
		coapClient.setURI("coap://"+ipAddress+"/buzzer");
		coapResponse = coapClient.post(json, MediaTypeRegistry.APPLICATION_JSON);
		json = coapResponse.getResponseText();
		jsonObject = new JSONObject(json);
		model.addAttribute("buzzerStatus", jsonObject.getString("status"));
		//-------------------------------------------------------
		jsonObject = new JSONObject();
		jsonObject.put("command", "status");
		json = jsonObject.toString();
		coapClient.setURI("coap://"+ipAddress+"/ultrasonicsensor");
		coapResponse = coapClient.post(json, MediaTypeRegistry.APPLICATION_JSON);
		json = coapResponse.getResponseText();
		jsonObject = new JSONObject(json);
		model.addAttribute("angle", jsonObject.getString("angle"));
		model.addAttribute("distance", jsonObject.getString("distance"));
		
		//-------------------------------------------------------
		jsonObject = new JSONObject();
		jsonObject.put("command", "status");
		json = jsonObject.toString();
		coapClient.setURI("coap://"+ipAddress+"/lcd");
		coapResponse = coapClient.post(json, MediaTypeRegistry.APPLICATION_JSON);
		json = coapResponse.getResponseText();
		jsonObject = new JSONObject(json);
		model.addAttribute("line0", jsonObject.getString("line0"));
		model.addAttribute("line1", jsonObject.getString("line1"));
		
		//-------------------------------------------------------
		jsonObject = new JSONObject();
		jsonObject.put("command", "status");
		json = jsonObject.toString();
		coapClient.setURI("coap://"+ipAddress+"/fronttire");
		coapResponse = coapClient.post(json, MediaTypeRegistry.APPLICATION_JSON);
		json = coapResponse.getResponseText();
		jsonObject = new JSONObject(json);
		model.addAttribute("angle", jsonObject.getString("angle"));
		
		//-------------------------------------------------------
		jsonObject = new JSONObject();
		jsonObject.put("command", "status");
		json = jsonObject.toString();
		coapClient.setURI("coap://"+ipAddress+"/backtire");
		coapResponse = coapClient.post(json, MediaTypeRegistry.APPLICATION_JSON);
		json = coapResponse.getResponseText();
		jsonObject = new JSONObject(json);
		String speed=jsonObject.getString("speed");
		model.addAttribute("direction", jsonObject.getString("direction"));
		model.addAttribute("speed",speed); 
		
		//-------------------------------------------------------
		model.addAttribute("cameraUrl", "http://"+ipAddress+":50001?action=stream");
		coapClient.shutdown();
		return "controlpanel";
	}
	
	@RequestMapping("/camera")
	public void camera(String command, String leftright, String updown, HttpServletResponse response) throws IOException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("command", command);
		jsonObject.put("leftright", leftright);
		jsonObject.put("updown", updown);
		String reqJson = jsonObject.toString();
		
		CoapClient coapClient = new CoapClient();
		coapClient.setURI("coap://"+ipAddress+"/camera");
		CoapResponse coapResponse = coapClient.post(reqJson, MediaTypeRegistry.APPLICATION_JSON);
		String resJson = coapResponse.getResponseText();
		coapClient.shutdown();
		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter pw = response.getWriter();
		pw.write(resJson);
		pw.flush();
		pw.close();
	}
	
	@RequestMapping("/rgbled")
	public void rgbled(String command, String red, String green, String blue, HttpServletResponse response) throws IOException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("command", command);
		jsonObject.put("red", red);
		jsonObject.put("green", green);
		jsonObject.put("blue", blue);
		String reqJson = jsonObject.toString();
		
		CoapClient coapClient = new CoapClient();
		coapClient.setURI("coap://"+ipAddress+"/rgbled");
		CoapResponse coapResponse = coapClient.post(reqJson, MediaTypeRegistry.APPLICATION_JSON);
		String resJson = coapResponse.getResponseText();
		coapClient.shutdown();
		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter pw = response.getWriter();
		pw.write(resJson);
		pw.flush();
		pw.close();
	}
	
	@RequestMapping("/laseremitter")
	public void rgbled(String command, String status, HttpServletResponse response) throws IOException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("command", command);
		jsonObject.put("status", status);
		String reqJson = jsonObject.toString();
		
		CoapClient coapClient = new CoapClient();
		coapClient.setURI("coap://"+ipAddress+"/laseremitter");
		CoapResponse coapResponse = coapClient.post(reqJson, MediaTypeRegistry.APPLICATION_JSON);
		String resJson = coapResponse.getResponseText();
		coapClient.shutdown();
		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter pw = response.getWriter();
		pw.write(resJson);
		pw.flush();
		pw.close();
	}
	
	@RequestMapping("/buzzer")
	public void buzzer(String command, String status, HttpServletResponse response) throws IOException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("command", command);
		jsonObject.put("status", status);
		String reqJson = jsonObject.toString();
		
		CoapClient coapClient = new CoapClient();
		coapClient.setURI("coap://"+ipAddress+"/buzzer");
		CoapResponse coapResponse = coapClient.post(reqJson, MediaTypeRegistry.APPLICATION_JSON);
		String resJson = coapResponse.getResponseText();
		coapClient.shutdown();
		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter pw = response.getWriter();
		pw.write(resJson);
		pw.flush();
		pw.close();
	}
	

	@RequestMapping("/ultrasonicsensor")
	public void ultrasonicsensor(String command, String angle,HttpServletResponse response) throws IOException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("command", command);
		jsonObject.put("angle", angle);
		String reqJson = jsonObject.toString();
		
		CoapClient coapClient = new CoapClient();
		coapClient.setURI("coap://"+ipAddress+"/ultrasonicsensor");   //coap에 등록해놓은 리소스 이름
		CoapResponse coapResponse = coapClient.post(reqJson, MediaTypeRegistry.APPLICATION_JSON);
		String resJson = coapResponse.getResponseText();
		coapClient.shutdown();
		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter pw = response.getWriter();
		pw.write(resJson);
		pw.flush();
		pw.close();
	}
	
	@RequestMapping("/lcd")
	public void lcd(String command, String line0,String line1,HttpServletResponse response) throws IOException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("command", command);
		jsonObject.put("line0", line0);
		jsonObject.put("line1", line1);
		String reqJson = jsonObject.toString();
		
		CoapClient coapClient = new CoapClient();
		coapClient.setURI("coap://"+ipAddress+"/lcd");   //coap에 등록해놓은 리소스 이름
		CoapResponse coapResponse = coapClient.post(reqJson, MediaTypeRegistry.APPLICATION_JSON);
		String resJson = coapResponse.getResponseText();
		coapClient.shutdown();
		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter pw = response.getWriter();
		pw.write(resJson);
		pw.flush();
		pw.close();
	}
	
	@RequestMapping("/fronttire")
	public void fronttire(String command, String angle,HttpServletResponse response) throws IOException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("command", command);
		jsonObject.put("angle", angle);
		String reqJson = jsonObject.toString();
		
		CoapClient coapClient = new CoapClient();
		coapClient.setURI("coap://"+ipAddress+"/fronttire");   //coap에 등록해놓은 리소스 이름
		CoapResponse coapResponse = coapClient.post(reqJson, MediaTypeRegistry.APPLICATION_JSON);
		String resJson = coapResponse.getResponseText();
		coapClient.shutdown();
		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter pw = response.getWriter();
		pw.write(resJson);
		pw.flush();
		pw.close();
	}
	@RequestMapping("/backtire")
	public void backtire(String command, String direction, String speed, HttpServletResponse response) throws IOException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("command", command);
		jsonObject.put("direction", direction);
		jsonObject.put("speed", speed);
		String reqJson = jsonObject.toString();
		
		CoapClient coapClient = new CoapClient();
		coapClient.setURI("coap://"+ipAddress+"/backtire");
		CoapResponse coapResponse = coapClient.post(reqJson, MediaTypeRegistry.APPLICATION_JSON);
		String resJson = coapResponse.getResponseText();
		coapClient.shutdown();
		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter pw = response.getWriter();
		pw.write(resJson);
		pw.flush();
		pw.close();
	}
	
}
