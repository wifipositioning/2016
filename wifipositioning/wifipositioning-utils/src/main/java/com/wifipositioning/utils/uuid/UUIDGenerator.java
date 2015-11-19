package com.wifipositioning.utils.uuid;

import java.util.UUID;

/**
 * uuid生成器
 * 
 * @author liuyujie
 *
 */
public class UUIDGenerator {
	
	/**
	 * 生成uuid
	 * 
	 * @return uuid
	 */
	public static String generateUUID(){
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(generateUUID());
		System.out.println(generateUUID().getBytes().length);
	}
}
