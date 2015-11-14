package com.wifipositioning.utils.db.connector.impl;

import java.beans.PropertyVetoException;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.wifipositioning.utils.db.connector.AbstractConnectorProvider;
import com.wifipositioning.utils.db.parser.impl.C3P0DataSourceParser;
import com.wifipositioning.utils.type.db.DbType;

/**
 * 
 * 基于C3P0数据源，数据库连接池
 * 
 * @author liuyujie
 *
 */
public class ConnectorProviderC3P0Impl extends AbstractConnectorProvider<C3P0DataSourceParser, ComboPooledDataSource> {

	public ConnectorProviderC3P0Impl(){
		this(DbType.MYSQL);
	}
	
	public ConnectorProviderC3P0Impl(DbType dbType){
		try {
			initConnectorProviderC3P0Impl(dbType);
			setDataSourceCfg();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ConnectorProvider初始化<br/>
	 * 对数据源解析器和数据源类型初始化操作
	 */
	private void initConnectorProviderC3P0Impl(DbType dbType){
		dataSourceParser = new C3P0DataSourceParser(dbType);
		dataSource = new ComboPooledDataSource();
	}
	
	@Override
	public void releaseDataSource(){
		dataSource.close();
	}
	
	/* 
	 * 配置C3P0数据源
	 * 
	 * driver  url  username  password initialPoolSize minPoolSize maxPoolSize
	 *  
	 * @see com.wifipositioning.utils.db.connector.AbstractConnectorProvider#setDataSourceCfg()
	 */
	protected void setDataSourceCfg() throws Exception{
		String driver = dataSourceParser.getDriver();
		String url = dataSourceParser.getUrl();
		String username = dataSourceParser.getUsername();
		String password = dataSourceParser.getPassword();
		int initialPoolSize = dataSourceParser.getInitialPoolSize();
		int minPoolSize = dataSourceParser.getMinPoolSize();
		int maxPoolSize = dataSourceParser.getMaxPoolSize();
		
		if(driver == null || url == null || username == null){
			System.out.println("[ERROR]: Driver or URL or Username or Password is null!");
			throw new Exception("[ERROR]: Driver or URL or Username or Password is null!");
		}
		
		setDriver(driver).setUrl(url).setUsername(username);
		if(password != null){
			setPassword(password);
		}
		setInitPoolSize(initialPoolSize).setMaxPoolSize(maxPoolSize).setMinPoolSize(minPoolSize);
	}

	private ConnectorProviderC3P0Impl setDriver(String driver) throws PropertyVetoException {
		dataSource.setDriverClass(driver);
		return this;
	}

	private ConnectorProviderC3P0Impl setUrl(String url) {
		dataSource.setJdbcUrl(url);
		return this;
	}

	private ConnectorProviderC3P0Impl setUsername(String username) {
		dataSource.setUser(username);
		return this;
	}

	private ConnectorProviderC3P0Impl setPassword(String password) {
		dataSource.setPassword(password);
		return this;
	}

	private ConnectorProviderC3P0Impl setInitPoolSize(int initialPoolSize) {
		dataSource.setInitialPoolSize(initialPoolSize);
		return this;
	}

	private ConnectorProviderC3P0Impl setMinPoolSize(int minPoolSize) {
		dataSource.setMinPoolSize(minPoolSize);
		return this;
	}

	private ConnectorProviderC3P0Impl setMaxPoolSize(int maxPoolSize) {
		dataSource.setMaxPoolSize(maxPoolSize);
		return this;
	}
	
}
