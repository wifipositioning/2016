package com.wifiposition.dao.utils;

import java.sql.Connection;
import java.sql.SQLException;

import com.wifipositioning.dao.ConnectionManagerTestThread;
import com.wifipositioning.utils.db.connector.IConnectorProvider;
import com.wifipositioning.utils.db.connector.factory.ConnctorProviderFactory;
import com.wifipositioning.utils.type.db.DbType;
import com.wifipositioning.utils.type.source.SourceType;

/**
 * 数据库连接事务管理类
 * <br/>
 * 管理数据库连接、事务
 * 
 * @author liuyujie
 *
 */
public class ConnectionManager {
	
	private static ConnectionManager connectionManager = null;
	
	private static ConnctorProviderFactory connctorProviderFactory = ConnctorProviderFactory.getConnctorProviderFactory();
	
	private IConnectorProvider connectorProvider = null;
	
	private ThreadLocal<Connection> connection = new ThreadLocal<Connection>();
	
	private SourceType sourceType = null;
	private DbType dbType = null;
	
	public ConnectionManager(SourceType sourceType, DbType dbType) throws SQLException{
		
		if(sourceType == null || dbType == null){
			throw new SQLException("Param is Illegal");
		}
		
		this.sourceType = sourceType;
		this.dbType = dbType;
		
		this.connectorProvider = connctorProviderFactory.getConnectorProvider(sourceType, dbType);
	}
	
	/**
	 * 获取ConnectionManager实例
	 * 
	 * @param sourceType 数据源类型(eg. c3p0)
	 * @param dbType 数据库类型(eg. mysql)
	 * @return 创建失败返回null
	 * @throws SQLException 
	 */
	public synchronized static ConnectionManager getConnectionManager(SourceType sourceType, DbType dbType){
		if(connectionManager == null){
			
			if(sourceType == null || dbType == null){
				return null;
			}
			
			try {
				connectionManager = new ConnectionManager(sourceType, dbType);
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		}
		
		return connectionManager;
	}
	
	/**
	 * 获取数据库连接
	 * 
	 * @return
	 * @throws SQLException 
	 */
	public Connection getConnection() throws SQLException{
		
		Connection conn = connection.get();
		if(conn == null){
			conn = connectorProvider.getConnection();
			connection.set(conn);
		}
		
		return conn;
	}
	
	/**
	 * 关闭连接
	 * <br/>
	 * ThreadLocal对象清空
	 * 
	 */
	public void closeConnection(){
		connectorProvider.releaseConnection(connection.get());
		connection.remove();
	}
	
	/**
	 * 开启事务
	 * 
	 * @throws SQLException
	 */
	public void begin() throws SQLException{
		if(connection.get() == null){
			throw new SQLException("Connection is Null");
		}
		try {
			connection.get().setAutoCommit(false);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException("Can not begin transaction");
		}
	}
	
	/**
	 * 提交事务
	 * 
	 * @throws SQLException
	 */
	public void commit() throws SQLException{
		
		// 数据库连接为空		
		if(connection.get() == null){
			throw new SQLException("Connection is Null");
		}
		
		// 没有开启事务		
		if(connection.get().getAutoCommit()){
			throw new SQLException("No Transaction");
		}
		
		try {
			connection.get().commit();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException("Commit Transaction Exception");
		}
		
		try {
			connection.get().setAutoCommit(true);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException("End Transaction Exception");
		}
	}
	
	/**
	 * 回滚事务
	 * @throws SQLException 
	 */
	public void rollback() throws SQLException{
		// 数据库连接为空		
		if(connection.get() == null){
			throw new SQLException("Connection is Null");
		}
		
		try {
			connection.get().rollback();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException("Transaction Rollback Exception");
		}
	}
	
	public SourceType getSourceType(){
		return this.sourceType;
	}
	
	public DbType getDbType(){
		return this.dbType;
	}
	
	public static void main(String[] args){
		new ConnectionManagerTestThread().start();
		new ConnectionManagerTestThread().start();
	}
}
