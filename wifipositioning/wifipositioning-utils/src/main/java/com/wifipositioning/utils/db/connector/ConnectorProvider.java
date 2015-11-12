package com.wifipositioning.utils.db.connector;

import java.sql.Connection;
import java.sql.SQLException;

import com.wifipositioning.utils.db.parser.DataSourceParser;

/**
 * 
 * 数据源配置接口
 * 
 * @author liuyujie
 *
 */
public interface ConnectorProvider {
	
	DataSourceParser dataSourceParser = DataSourceParser.getDataSourceParser();
	
	/**
	 * 设置数据源驱动
	 * 
	 * @param driver 驱动Class
	 * @return 数据库连接provider
	 * @throws Exception
	 */
	public ConnectorProvider setDriver(String driver) throws Exception;
	
	/**
	 * 设置数据源Url
	 * 
	 * @param url jdbc url
	 * @return 数据库连接provider
	 */
	public ConnectorProvider setUrl(String url);
	
	/**
	 * 设置数据源用户名
	 * 
	 * @param username 用户名
	 * @return 数据库连接provider
	 */
	public ConnectorProvider setUsername(String username);
	
	/**
	 * 设置数据源密码
	 * 
	 * @param password 密码
	 * @return 数据库连接provider
	 */
	public ConnectorProvider setPassword(String password);
	
	/**
	 * 设置数据源连接池初始化大小
	 * 
	 * @param initialPoolSize 连接池初始大小
	 * @return 数据库连接provider
	 */
	public ConnectorProvider setInitPoolSize(int initialPoolSize);
	
	/**
	 * 
	 * 设置数据源连接池连接数的最小值
	 * 
	 * @param minPoolSize 最小连接数
	 * @return 数据库连接provider
	 */
	public ConnectorProvider setMinPoolSize(int minPoolSize);
	
	/**
	 * 
	 * 设置数据源连接池连接数的最大值
	 * 
	 * @param maxPoolSize 最大连接数
	 * @return 数据库连接provider
	 */
	public ConnectorProvider setMaxPoolSize(int maxPoolSize);
	
	/**
	 * 
	 * 从数据源连接池中获取数据库的Connection对象
	 * 
	 * @return Connection对象
	 * @throws SQLException
	 */
	public Connection getConnector() throws SQLException;

	/**
	 * 
	 * 释放数据库Connection对象
	 * <br/>
	 * 没有真正关闭连接，仅将数据库Connection对象返回给连接池
	 * 
	 * @param connection 需要释放的数据库连接对象
	 * @return 释放成功 <code>true</code>   释放失败或异常<code>false</code>
	 */
	public boolean releaseConnection(Connection connection);
	
	/**
	 * 释放连接池
	 */
	public void releaseDataSource();
}
