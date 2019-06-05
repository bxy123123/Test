package com.cc.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cc.util.LibraryResult;


/**此注解用于标识此类为全局的异常处理类*/
@ControllerAdvice
public class ControllerExceptionHandler {
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public LibraryResult handleException(Exception e){
		System.out.println("exception");
		e.printStackTrace();
		return new LibraryResult(e);
	}	
	@ExceptionHandler(RuntimeException.class)
	@ResponseBody
	public LibraryResult handleException(RuntimeException e){
		System.out.println("runtime exception");
		e.printStackTrace();
		return new LibraryResult(e);
	}
}








