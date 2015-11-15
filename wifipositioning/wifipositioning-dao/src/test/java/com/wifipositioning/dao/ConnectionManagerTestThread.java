package com.wifipositioning.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.wifiposition.dao.utils.ConnectionManager;
import com.wifipositioning.utils.type.db.DbType;
import com.wifipositioning.utils.type.source.SourceType;

public class ConnectionManagerTestThread extends Thread {
	
	private ConnectionManager connectionManager = ConnectionManager.getConnectionManager(SourceType.C3P0, DbType.MYSQL); 
	
	@Override
	public void run() {
		try {
			Connection connection = connectionManager.getConnection();
			System.out.println(Thread.currentThread().getName() + connection);
			connection = connectionManager.getConnection();
			System.out.println(Thread.currentThread().getName() + connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
