
package http.exam02;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;

public class HttpMultipartClient {
	public static void main(String[] args) {
		String title="제목";
		StringBody titleBody = new StringBody(title,ContentType.create("text/plain", Charset.forName("UTF-8"))); //텍스트 타입으로전달할때 UTF-8사용 
		String content="내용";
		StringBody contentBody = new StringBody(content,ContentType.create("text/plain", Charset.forName("UTF-8")));
		//파일은 위에처럼 컨텐트타입을 쓰기에 애매함 파일이 텍스트 파일도 아니기 때문
		File attach = new File("C:/Temp/사막.jpg");
		FileBody attachBody= new FileBody(attach,ContentType.create("image/jpeg"));//filecontenttype에 담아놓을문자
		
		HttpPost httpPost=new HttpPost("http://192.168.3.133:8080/IoTWebProgramming/http/exam02");
		MultipartEntityBuilder multipartEntityBulder = MultipartEntityBuilder.create();
		//문자파트
		multipartEntityBulder.addPart("title",titleBody);
		multipartEntityBulder.addPart("content",contentBody);
		//파일파트
		multipartEntityBulder.setCharset(Charset.forName("UTF-8")); //파일 이름이 한글이 포함되어 있을 경우
		multipartEntityBulder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE); // 브라우저가 파일을 보내는 방식과 동일하게 함
		multipartEntityBulder.addPart("attach",attachBody);
		//멀티파트 인코딩된 본문 열기
		HttpEntity reqEntity=multipartEntityBulder.build();
		
		httpPost.setEntity(reqEntity);
		CloseableHttpClient httpClient = HttpClients.createDefault();
		
		
		try{
			CloseableHttpResponse response = httpClient.execute(httpPost);
			try {
				HttpEntity resEntity = response.getEntity();    //응답의 바디부분의 전체를 가지고있는 객체
				if (resEntity != null) {  //만약에 내용이 존재한다면 있다면
					InputStream is = resEntity.getContent();   // 가져와
					try {
						InputStreamReader isr = new InputStreamReader(is);
						BufferedReader br = new BufferedReader(isr);   // 한행씩읽을수 있음 (readLine)
						String json = "";
						while (true) {
							String data = br.readLine(); //한행씩 읽어서 저장을 하고 다읽으면 null을 리턴하고 while을 빠져나옴
							if (data == null) {
								break;
							}
							json += data; 
						}
						JSONObject jsonObject=new JSONObject(json);
						String restitle=jsonObject.getString("title");
						String rescontent=jsonObject.getString("content");
						String originalfilename=jsonObject.getString("originalfilename");
						String savedfilename=jsonObject.getString("savedfilename");
						String filecontenttype=jsonObject.getString("filecontenttype");
						System.out.println("title :"+title);
						System.out.println("content :"+content);
						System.out.println("originalfilename :"+originalfilename);
						System.out.println("savedfilename :"+savedfilename);
						System.out.println("filecontenttype :"+filecontenttype);
						

					} catch (Exception e) {
						e.printStackTrace();
					} finally {

						is.close();
					}
				}
			} finally {
				response.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				httpClient.close();
			} catch (IOException ex) {
				Logger.getLogger(HttpMultipartClient.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
						
	}
}
