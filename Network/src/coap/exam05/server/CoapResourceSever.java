
package coap.exam05.server;

//리소스는 센서들을 얘기함

import coap.exam04.server.*;
import coap.exam02.server.*;
import coap.exam01.server.*;
import java.io.IOException;
import java.net.InetSocketAddress;
import org.eclipse.californium.core.CoapServer;
import org.eclipse.californium.core.network.CoapEndpoint;
import org.eclipse.californium.proxy.DirectProxyCoapResolver;
import org.eclipse.californium.proxy.ProxyHttpServer;
import org.eclipse.californium.proxy.resources.ForwardingResource;
import org.eclipse.californium.proxy.resources.ProxyCoapClientResource;

public class CoapResourceSever {
	//Field
	private CoapServer coapServer;
	
	//Constructor
	public CoapResourceSever() throws IOException{
		coapServer = new CoapServer();
		InetSocketAddress isa1=new InetSocketAddress("192.168.3.52",5683);
		InetSocketAddress isa2=new InetSocketAddress("localhost",5683);
		coapServer.addEndpoint(new CoapEndpoint(isa1));
		coapServer.addEndpoint(new CoapEndpoint(isa2));
		//관리할 센서들을 여기서 등록해줌
		coapServer.add(new CoapResource01()); 
		coapServer.add(new CoapResource02());
		coapServer.add(new CoapResource03());
		coapServer.add(new CoapResource04()); 
		coapServer.add(new CoapResource05());
		coapServer.add(new CoapResource06());
		
        //coap-> coap 프록시 설정
		ForwardingResource coap2coap=new ProxyCoapClientResource("coap2coap"); //아래 coapresolver에서 사용한다.
		coapServer.add(coap2coap);
		
		//Http->Coap 포워드 프록시 설정
		//웹서버 생성
		ProxyHttpServer httpServer= new ProxyHttpServer(9090); // 포트번호입력 //http://ip주소:9090/coap2coap 까지 변환하여 입력해주는 부분
		httpServer.setProxyCoapResolver(new DirectProxyCoapResolver(coap2coap)); //coap2coap 까지 변환하여 입력해주는 부분//변환하기 위해서 위에 forwardingresource의 정보를 참고한다.
		
		
		
		
		
		coapServer.start(); //서버시작
	}
	//Method
	
	public void shutdown(){
		coapServer.stop();
		coapServer.destroy();
	}
	
	public static void main(String[] args) throws IOException {
		CoapResourceSever server = new CoapResourceSever();
		System.out.println("CoAP server is listening on port 5683");
	}

}
