package com.wifipositioning.utils.db.parser;

import java.util.List;

import org.dom4j.DocumentException;
import org.dom4j.Element;

import com.wifipositioning.utils.type.db.DbType;
import com.wifipositioning.utils.type.source.SourceType;

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
	
	/**
	 * 数据源类型，默认c3p0
	 */
	protected SourceType sourceType = SourceType.C3P0;
	
	/**
	 * 数据库类型，默认Mysql
	 */
	protected DbType dbType = DbType.MYSQL;
	/**
	 * 解析标识，默认 "c3p0/mysql"
	 */
	protected String flagTag = sourceType.getTypeText() + "/" + dbType.getTypeText();
	
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

	public SourceType getSourceType() {
		return sourceType;
	}

	public DbType getDbType() {
		return dbType;
	}
	
}
