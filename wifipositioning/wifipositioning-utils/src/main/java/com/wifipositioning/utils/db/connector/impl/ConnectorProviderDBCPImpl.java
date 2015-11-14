package com.wifipositioning.utils.db.connector.impl;

import javax.sql.DataSource;

import com.wifipositioning.utils.db.connector.AbstractConnectorProvider;
import com.wifipositioning.utils.db.parser.AbstractDataSourceParser;
import com.wifipositioning.utils.type.db.DbType;

/**
 * 
 * 基于DBCP数据源，数据库连接池(暂未实现)
 * 
 * @author liuyujie
 *
 */
public class ConnectorProviderDBCPImpl extends AbstractConnectorProvider<AbstractDataSourceParser, DataSource> {

	public ConnectorProviderDBCPImpl() {
		this(DbType.MYSQL);
	}
	
	public ConnectorProviderDBCPImpl(DbType dbType){
		
	}
	
	@Override
	public void releaseDataSource() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void setDataSourceCfg() throws Exception {
		// TODO Auto-generated method stub
		
	}

}
