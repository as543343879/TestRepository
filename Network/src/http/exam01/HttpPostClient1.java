package http.exam01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

public class HttpPostClient1 {
	//Field

	//Constructor
	//Method
	public static void main(String[] args) throws IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		try {
		
			HttpPost httpPost=new HttpPost("http://192.168.3.133:8080/IoTWebProgramming/http/exam01");
			
		//System.out.println("excuting request"+httpGet.getRequestLine());
		List<NameValuePair> params= new ArrayList<>();
		params.add(new BasicNameValuePair("thermistor", String.valueOf(25)));
		params.add(new BasicNameValuePair("photoresistor", String.valueOf(200)));
		// thermistor=25&photoresistor=200으로 바꿔주는 코드
		HttpEntity reqEntity = new UrlEncodedFormEntity(params,Charset.forName("UTF-8"));
		httpPost.setEntity(reqEntity);
		
			CloseableHttpResponse response = httpClient.execute(httpPost); //execute할때 요청이 들어감 , 응답이 올때까지 블로킹되서 대기함
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
						String thermistor=jsonObject.getString("thermistor");
						String photoresistor=jsonObject.getString("photoresistor");
						System.out.println("thermistor :"+thermistor);
						System.out.println("photoresistor :"+photoresistor);

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
			httpClient.close();
		}
	}
}
