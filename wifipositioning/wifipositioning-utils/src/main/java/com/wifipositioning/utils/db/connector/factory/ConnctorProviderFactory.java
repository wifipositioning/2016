package com.wifipositioning.utils.db.connector.factory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.wifipositioning.utils.db.connector.IConnectorProvider;
import com.wifipositioning.utils.db.connector.impl.ConnectorProviderC3P0Impl;
import com.wifipositioning.utils.db.connector.impl.ConnectorProviderDBCPImpl;
import com.wifipositioning.utils.type.db.DbType;
import com.wifipositioning.utils.type.source.SourceType;

/**
 * connector provider 工厂
 * <br/>
 * 数据库连接池唯一对外工厂类，使用时必须通过该工厂类提供数据库连接池，简单提供数据源类型和数据库类型，就可以使用。非必要底层实现不对外提供服务。
 * 
 * @author liuyujie
 *
 */
public class ConnctorProviderFactory {
	private static ConnctorProviderFactory connctorProviderFactory = null;
	
	private Map<DbType, ConnectorProviderC3P0Impl> c3p0Provider = new HashMap<>();
	
	//	暂未实现dbcp数据源连接
	private Map<DbType, ConnectorProviderDBCPImpl> dbcpProvider = new HashMap<>();
	
	private ConnctorProviderFactory(){
		
	}
	
	public synchronized static ConnctorProviderFactory getConnctorProviderFactory(){
		if(connctorProviderFactory == null){
			connctorProviderFactory = new ConnctorProviderFactory();
		}
		return connctorProviderFactory;
	}
	
	/**
	 * 
	 * 根据不同的数据源和数据库类型，得到不同的connector provider
	 * @param <T>
	 * @param <E>
	 * 
	 * @param sourceType 数据源类型
	 * @param dbType 数据库类型
	 * @return
	 */
	public synchronized IConnectorProvider getConnectorProvider(SourceType sourceType, DbType dbType){
		if(sourceType == SourceType.C3P0){
			return createC3P0ConnectorProvider(dbType);
		}
		else if(sourceType == SourceType.DBCP){
			return createDBCPConnectorProvider(dbType);
		}
		return null;
	}
	
	/**
	 * 生成C3P0的数据库连接池
	 * 
	 * @param dbType
	 * @return
	 */
	private synchronized ConnectorProviderC3P0Impl createC3P0ConnectorProvider(DbType dbType){
		if(c3p0Provider.containsKey(dbType)){
			return c3p0Provider.get(dbType);
		}
		
		ConnectorProviderC3P0Impl connectorProviderC3P0 = new ConnectorProviderC3P0Impl(dbType);
		c3p0Provider.put(dbType, connectorProviderC3P0);
		
		return connectorProviderC3P0;
	}
	
	/**
	 * 生成DBCP的数据库连接池
	 * 
	 * @param dbType
	 * @return
	 */
	private synchronized ConnectorProviderDBCPImpl createDBCPConnectorProvider(DbType dbType){
		if(dbcpProvider.containsKey(dbType)){
			return dbcpProvider.get(dbType);
		}
		
		ConnectorProviderDBCPImpl connectorProviderDBCP = new ConnectorProviderDBCPImpl(dbType);
		dbcpProvider.put(dbType, connectorProviderDBCP);
		
		return connectorProviderDBCP;
	}
	
	public static void main(String[] args) throws SQLException {
		ConnctorProviderFactory connctorProviderFactory = ConnctorProviderFactory.getConnctorProviderFactory();
		IConnectorProvider connectorProvider = connctorProviderFactory.getConnectorProvider(SourceType.C3P0, DbType.MYSQL);
		Connection connection = connectorProvider.getConnector();
		System.out.println(connection);
		connectorProvider.releaseConnection(connection);
		System.out.println("closed connection");
	}
}

