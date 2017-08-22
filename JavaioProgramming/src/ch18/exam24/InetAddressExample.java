
package ch18.exam24;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressExample {
    public static void main(String[] args) throws UnknownHostException {
   
        InetAddress ia = InetAddress.getLocalHost();
        String address = ia.getHostAddress();   // 나의 주소얻기
        System.out.println(address);
        
        InetAddress iaNaver = InetAddress.getByName("www.naver.com"); //네이버주소얻기
        String addressNaver = iaNaver.getHostAddress();
        System.out.println(addressNaver);
        
        InetAddress[] iaNavers=InetAddress.getAllByName("com-pc");////매개변수localhost
        for(InetAddress i :iaNavers){
         System.out.println(i.getHostAddress());
        }
    }
    

}
