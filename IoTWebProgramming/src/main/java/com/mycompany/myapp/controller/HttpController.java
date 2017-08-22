package com.mycompany.myapp.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/http")
public class HttpController {
	private static final Logger logger=LoggerFactory.getLogger(HttpController.class);
	
@RequestMapping("/exam01")
public String exam01(@RequestParam(defaultValue="0") double thermistor ,@RequestParam(defaultValue="0") double photoresistor , Model model){ //수치타입은 무조건 디폴트값 줘야됨, string은 널이있어서 괜춘
	
	model.addAttribute("thermistor",thermistor);
	model.addAttribute("photoresistor",photoresistor);
	return "http/exam01";
}

@RequestMapping("/exam02")
public String exam02(String title,String content,MultipartFile attach,Model model) throws Exception{
	String savedfilename=new Date().getTime()+"-"+attach.getOriginalFilename();
	String savedfilepath="C:/Temp/"+savedfilename;
	attach.transferTo(new File(savedfilepath));
	
	model.addAttribute("title", title);
	model.addAttribute("content",content);
	model.addAttribute("originalfilename",attach.getOriginalFilename());
	model.addAttribute("filecontenttype",attach.getContentType());
	model.addAttribute("savedfilename",savedfilename);
	return "http/exam02";
}

@RequestMapping("/exam03")
public void exam03(String param1,String param2,HttpServletResponse response) throws IOException{

	logger.info(param1);
	logger.info(param2);
	
	JSONObject jsonObject=new JSONObject();
	jsonObject.put("param1", param1);
	jsonObject.put("param2", param2);
	String json=jsonObject.toString();
	
	response.setContentType("application/json; charset=UTF-8");
    PrintWriter pw=response.getWriter();
    pw.write(json);
    pw.flush();
    pw.close();
}

@RequestMapping("/exam04")
public void exam04(String param1,String param2,MultipartFile attach,HttpServletResponse response) throws IOException{
	logger.info(param1);
	logger.info(param2);
	logger.info(attach.getOriginalFilename());
	logger.info(attach.getContentType());
	logger.info(String.valueOf(attach.getSize()));
	
	String fileName=new Date().getTime()+"-"+attach.getOriginalFilename();
	String temp="C:/Temp/"+fileName;
	attach.transferTo(new File(temp));
	
	JSONObject jsonObject=new JSONObject();
	jsonObject.put("param1", param1);
	jsonObject.put("param2", param2);
	jsonObject.put("fileName", attach.getOriginalFilename());
	jsonObject.put("fileType", attach.getContentType());
	jsonObject.put("fileSize", attach.getSize());
	String json=jsonObject.toString();
	
	response.setContentType("application/json; charset=UTF-8");
    PrintWriter pw=response.getWriter();
    pw.write(json);
    pw.flush();
    pw.close();
}
}


