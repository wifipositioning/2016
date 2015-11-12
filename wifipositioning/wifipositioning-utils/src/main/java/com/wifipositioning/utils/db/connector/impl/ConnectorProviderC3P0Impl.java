package com.wifipositioning.utils.db.connector.impl;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.wifipositioning.utils.db.connector.ConnectorProvider;

/**
 * 
 * 基于C3P0数据源，数据库连接池<br/>以单例形式存在
 * 
 * @author liuyujie
 *
 */
public class ConnectorProviderC3P0Impl implements ConnectorProvider {

	private static ConnectorProviderC3P0Impl connectorProvider = new ConnectorProviderC3P0Impl();
	
	private ComboPooledDataSource dataSource = new ComboPooledDataSource();
	
	private ConnectorProviderC3P0Impl(){
		try {
			setDataSourceCfg();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static ConnectorProvider getConnectorProviderInstance(){
		return connectorProvider;
	}
	
	@Override
	public Connection getConnector() throws SQLException {
		return dataSource.getConnection();
	}

	@Override
	public boolean releaseConnection(Connection connection) {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	@Override
	public void releaseDataSource(){
		dataSource.close();
	}
	
	/**
	 * 配置数据源
	 */
	private void setDataSourceCfg() throws Exception{
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

	@Override
	public ConnectorProvider setDriver(String driver) throws PropertyVetoException {
		dataSource.setDriverClass(driver);
		return this;
	}

	@Override
	public ConnectorProvider setUrl(String url) {
		dataSource.setJdbcUrl(url);
		return this;
	}

	@Override
	public ConnectorProvider setUsername(String username) {
		dataSource.setUser(username);
		return this;
	}

	@Override
	public ConnectorProvider setPassword(String password) {
		dataSource.setPassword(password);
		return this;
	}

	@Override
	public ConnectorProvider setInitPoolSize(int initialPoolSize) {
		dataSource.setInitialPoolSize(initialPoolSize);
		return this;
	}

	@Override
	public ConnectorProvider setMinPoolSize(int minPoolSize) {
		dataSource.setMinPoolSize(minPoolSize);
		return this;
	}

	@Override
	public ConnectorProvider setMaxPoolSize(int maxPoolSize) {
		dataSource.setMaxPoolSize(maxPoolSize);
		return this;
	}

}
