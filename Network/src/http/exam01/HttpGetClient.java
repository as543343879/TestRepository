package http.exam01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;

public class HttpGetClient {
	//Field

	//Constructor
	//Method
	public static void main(String[] args) throws IOException {
		//코드상으로 닫을 수 있는 HttpClient// 응답이 오기 전에도 끊을 수 있다.
		CloseableHttpClient httpClient = HttpClients.createDefault();
		try {
			//get 방식으로 uri를 제공할 때
			URIBuilder uriBuilder = new URIBuilder("http://192.168.3.133:8080/IoTWebProgramming/http/exam01");
			//param 값 추가
			uriBuilder.setParameter("thermistor", String.valueOf(25));
			uriBuilder.setParameter("photoresistor", String.valueOf(200));
			//get방식으로 통신 할 때
			HttpGet httpGet=new HttpGet(uriBuilder.build());
			
		System.out.println("excuting request"+httpGet.getRequestLine());
			
		//execute()를 할 때 실행을 하게 됨 // 요청을 하면 동기 방식으로 응답을 받음
			CloseableHttpResponse response = httpClient.execute(httpGet); //execute할때 요청이 들어감 , 응답이 올때까지 블로킹되서 대기함
			try {
				HttpEntity resEntity = response.getEntity();    //응답의 바디부분의 전체를 가지고있는 객체
				if (resEntity != null) {  //만약에 내용이 존재한다면 있다면
					InputStream is = resEntity.getContent();   // 가져와
					try {
						InputStreamReader isr = new InputStreamReader(is); // json으로 받는 데이터는 모두 문자열이기 때문에 reader로 받음
						BufferedReader br = new BufferedReader(isr);   // 한행씩읽을수 있음 (readLine)
						String json = "";
						while (true) {
							String data = br.readLine(); //한행씩 읽어서 저장을 하고 다읽으면 null을 리턴하고 while을 빠져나옴
							if (data == null) {
								break;
							}
							json += data; 
						}
						//json문자열을 파싱하기 위한 코드
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
