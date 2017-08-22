
package test;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExcuteExample {
    public static void main(String[] args) throws InterruptedException, IOException {
        //스레드풀생성
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        //작업생성코드
        for(int i = 0; i<10;i++){
        Runnable task = ()->{
        System.out.println(Thread.currentThread().getName()+"작업처리");
        };
        //작업처리지시(작업큐에넣기)
        executorService.submit(task); //큐에넣기 //큐에 넣기만 하면 알아서 처리해줌
        }
        //스레드풀 종료
        System.in.read(); 
        executorService.shutdown();
        
    }
}


