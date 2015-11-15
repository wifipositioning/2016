package com.wifipositioning.utils.db.connector;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.wifipositioning.utils.db.parser.AbstractDataSourceParser;

/**
 * 数据源抽象类
 * 
 * @author liuyujie
 *
 * @param <T> 数据源解析器类型
 * @param <E> 数据源类型
 */
public abstract class AbstractConnectorProvider<T extends AbstractDataSourceParser, E extends DataSource> implements IConnectorProvider {

	protected T dataSourceParser = null;
	
	protected E dataSource = null;
	
	/**
	 * 配置数据源(需要结合数据源解析器，不同的数据源实现不同)
	 * 
	 * @throws Exception
	 */
	protected abstract void setDataSourceCfg() throws Exception;
	
	@Override
	public Connection getConnection() throws SQLException {
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

	public T getDataSourceParser() {
		return dataSourceParser;
	}
}
