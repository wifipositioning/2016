package com.wifipositioning.utils.msg;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * MAC RSS 映射关系分离器
 * 
 * @author liuyujie
 *
 */
public class MacRssMappingSpilter {
	
	private static final String SEMICOLON = ";";
	
	private static final String VERTICAL_LINE = "\\|";
	
	public static Map<String, Integer> spiltMacRsss(String macRssStr){
		
		Map<String, Integer> macRssMappings = new HashMap<String, Integer>();
		if(macRssMappings != null)
		{
			String[] macRsss = macRssStr.split(SEMICOLON);
			for(String macRss : macRsss){
				String mac = macRss.split(VERTICAL_LINE)[0];
				Integer rss = Integer.parseInt(macRss.split(VERTICAL_LINE)[1]);
				macRssMappings.put(mac, rss);
			}
		}
		
		return macRssMappings;
	}
	
	public static void main(String[] args) {
		Map<String, Integer> maps = spiltMacRsss("Ac:12:df:12|23;AC:23:df:23|108");
		Set<Entry<String, Integer>> sets = maps.entrySet();
		
		for(Entry<String, Integer> entry: sets){
			System.out.println(entry.getValue() + "---" + entry.getKey());
		}
	}
}
