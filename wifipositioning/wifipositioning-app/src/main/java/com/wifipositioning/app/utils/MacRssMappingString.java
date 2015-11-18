package com.wifipositioning.app.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class MacRssMappingString {
	private static final String SEMICOLON = ";";
	
	private static final String VERTICAL_LINE = "|";
	
	/**
	 * 将Map mac-rss key-value对映射成字符串形式 mac1|rss1;mac2|rss2;mac3|rss3 返回
	 * 
	 * @param macRssMap
	 * @return
	 */
	public static String macRsssMappingString(Map<String, Integer> macRssMap){
		
		StringBuffer results = new StringBuffer();
		if(macRssMap != null && !macRssMap.isEmpty()){
			Set<Entry<String, Integer>> entrySets = macRssMap.entrySet();
			for(Entry<String, Integer> entry : entrySets){
				results.append(entry.getKey()).append(VERTICAL_LINE).append(entry.getValue()).append(SEMICOLON);
			}
			results.deleteCharAt(results.length() - 1);
		}
		
		return results.toString();
	}
	
	public static void main(String[] args) {
		Map<String, Integer> macRssMappings = new HashMap<String, Integer>();
		macRssMappings.put("12:12:12:12:12", 65);
		macRssMappings.put("12:12:12:12:13", 65);
		System.out.println(macRsssMappingString(macRssMappings));
	}
}
