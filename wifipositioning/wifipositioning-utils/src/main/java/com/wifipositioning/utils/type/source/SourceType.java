package com.wifipositioning.utils.type.source;

/**
 * 数据源类型
 * 
 * @author liuyujie
 *
 */
public enum SourceType {
	
	C3P0("c3p0"), DBCP("dbcp");
	
	/**
	 * 数据源的字符串描述
	 */
	private String typeText;
	
	private SourceType(String typeText){
		this.typeText = typeText;
	}
	
	public String getTypeText(){
		return this.typeText;
	}
}
