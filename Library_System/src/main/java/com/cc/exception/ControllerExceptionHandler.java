package com.cc.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cc.util.LibraryResult;


/**��ע�����ڱ�ʶ����Ϊȫ�ֵ��쳣������*/
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








