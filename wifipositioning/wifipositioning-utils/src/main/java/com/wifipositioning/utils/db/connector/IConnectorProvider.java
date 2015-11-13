package com.wifipositioning.utils.db.connector;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 
 * 数据源接口
 * 
 * @author liuyujie
 *
 */
public interface IConnectorProvider {
	
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
