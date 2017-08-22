package com.mycompany.myapp.exception;

//Spring에서 트랜잭션을 처리하려면 발생되는 예외가 반드시 RuntimeException 이어야 한다.
public class NoAccountException extends RuntimeException{
	
	public NoAccountException(){}
	public NoAccountException(String message){ //예외발생이유를 넣기 위해 message
		super(message);
	}
	

}
