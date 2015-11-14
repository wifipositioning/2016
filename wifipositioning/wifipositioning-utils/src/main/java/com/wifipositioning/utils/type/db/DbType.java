package com.wifipositioning.utils.type.db;

/**
 * 数据库类型
 * 
 * @author liuyujie
 *
 */
public enum DbType {
	
	MYSQL("mysql"), 
	SQL_SERVER("sqlserver"),
	ORACLE("oracle");
	
	/**
	 * 数据库的字符串描述
	 */
	private String typeText;
	
	private DbType(String typeText){
		this.typeText = typeText;
	}
	
	public String getTypeText(){
		return this.typeText;
	}
}
