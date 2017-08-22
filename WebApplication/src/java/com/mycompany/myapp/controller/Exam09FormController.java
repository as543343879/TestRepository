package com.mycompany.myapp.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class Exam09FormController {

	@Autowired
	private ServletContext servletContext;

	@RequestMapping(value = "/form/exam01", method = RequestMethod.GET)
	public String joinForm() {

		return "form/exam01";
	}

	@RequestMapping(value = "/form/exam01", method = RequestMethod.POST)
	public String join(String mid, String mname, String mpassword,
					@RequestParam(defaultValue = "0", required = false) int mage, //defaultvalue는 입력안됬을때 초기값, required는 꼭 넣어야 하는지 (기본값true)
					String[] mskill, String mbirth) {
		System.out.println("mid" + mid);
		System.out.println("mname" + mname);
		System.out.println("mage" + mage);
		System.out.println("mpassword" + mpassword);
		System.out.println("mskill" + mskill);
		System.out.println("mbirth" + mbirth);

		return "form/exam01";
	}

	@RequestMapping(value = "/form/exam02", method = RequestMethod.GET)
	public String joinForm2() {

		return "form/exam02";
	}

	@RequestMapping(value = "/form/exam02", method = RequestMethod.POST)
	public String join2(String mid, String mname, String mpassword,
					@RequestParam(defaultValue = "0", required = false) int mage,
					String[] mskill, String mbirth,
					MultipartFile attach) throws IOException {
		//파일의 정보 얻기
		String fileName = attach.getOriginalFilename();
		String contentType = attach.getContentType();
		long fileSize = attach.getSize();

		//파일을 서버 하드디스크에 저장
		String realPath = servletContext.getRealPath("/WEB-INF/upload/" + fileName);//실행할때 동적으로 web-inf의 경로를 알수 있도록 다른사람이랑 저장경로를 맞출 필요없이 한다.
		File file = new File(realPath);
		attach.transferTo(file);

		System.out.println("fileName" + fileName);
		System.out.println("contentType" + contentType);
		System.out.println("fileSize" + fileSize);

		return "form/exam02";
	}
	
	@RequestMapping("file/exam03")
    public void download(HttpServletResponse response, @RequestHeader("User-Agent") String userAgent) throws IOException {
        // 응답 HTTP 헤더행을 추가(3가지는 다 넣어주는게 좋음)
        // 1 파일 이름(옵션)
        String fileName = "튤립.jpg";
        String encodingFileName;
        if (userAgent.contains("MSIE") || userAgent.contains("Trident") || userAgent.contains("Edge")) {
            encodingFileName = URLEncoder.encode(fileName, "UTF-8");
            // fileName을 UTF-8로 인코딩한 바이트 배열을 16진수로 출력함         
        } else {
//            encodingFileName = new String(fileName.getBytes(), "UTF-8");
            encodingFileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
        }
        System.out.println(encodingFileName);
        
        response.addHeader("Content-Disposition", "attachment; filename=\"" + encodingFileName + "\"");
        // Disposition: 위치
        // attachment: 첨부파일 이므로 브라우저는 파일로 저장하는 행동(다이얼로그 창)을 취해라
        // "attachment; filename=\" \" " > "xxx xxx.png" 를 저장하기 위해
        
        // 2 파일 종류(필수)
        response.addHeader("Content-type", "image/jpeg");
        // "image/jpeg" >  MIME
        
        // 3 파일 크기(옵션)m
        File file = new File(servletContext.getRealPath("/WEB-INF/upload/" + fileName));
        long fileSize = file.length();
        response.addHeader("Content-Length", String.valueOf(fileSize));
        
        // 응답 HTTP 본문에 파일 데이터 추가
        OutputStream os = response.getOutputStream();
        FileInputStream fis = new FileInputStream(file);
        FileCopyUtils.copy(fis, os);
        // spring 에서는 자바에서 했던 복잡한 방법으로 파일을 카피하는 방식이 아닌 FileCopyUtils 클래스 제공
        // fis 에서 읽고, os로 출력
        os.flush();
        fis.close();
        os.close();
    }

}
