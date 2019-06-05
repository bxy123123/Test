package com.cc.util;

import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.SimpleByteSource;

/**
 * —Œ÷µº”√‹
 * @author Administrator
 *
 */
public class ByteSourceUtils{
	public static ByteSource bytes(byte[] bytes){
		return new SimpleByteSource(bytes);
	}
	public static ByteSource bytes(String arg0){
		return new SimpleByteSource(arg0.getBytes());
	}
}

