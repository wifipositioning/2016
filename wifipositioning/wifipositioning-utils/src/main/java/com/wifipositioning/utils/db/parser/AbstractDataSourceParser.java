package com.wifipositioning.utils.db.parser;

import java.util.List;

import org.dom4j.DocumentException;
import org.dom4j.Element;

/**
 * 
 * 数据源解析抽象类
 * 
 * @author liuyujie
 *
 */
public abstract class AbstractDataSourceParser implements IDataSourceParser {

	protected static final String DATA_SOURCE_FILE_NAME = "/datasource/datasource.xml"; 
	protected static final String ROOT_TAG = "datasource";
	
	protected static final String DRIVER = "driver";
	protected static final String URL = "url";
	protected static final String USERNAME = "username";
	protected static final String PASSWORD = "password";
	
	protected String driver = null;
	protected String url = null;
	protected String username = null;
	protected String password = null;
	
	/**
	 * 数据源配置初始化
	 * @throws DocumentException 
	 */
	protected void initDataSource() throws DocumentException{
		List<Element> dataSourceCfg = parseDataSourceCfg();
		setDataSourceCfg(dataSourceCfg);
	}

	public String getDriver() {
		return driver;
	}
	public String getUrl() {
		return url;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	
}
