package com.wifipositioning.utils.uuid;

import java.util.UUID;

public class UUIDGenerator {
	public static String generateUUID(){
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(generateUUID());
		System.out.println(generateUUID().getBytes().length);
	}
}
