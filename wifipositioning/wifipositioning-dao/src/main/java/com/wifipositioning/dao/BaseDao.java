package com.wifipositioning.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.wifiposition.dao.utils.ConnectionManager;
import com.wifipositioning.utils.type.db.DbType;
import com.wifipositioning.utils.type.source.SourceType;

/**
 * 
 * Dao操作
 * 
 * @author liuyujie
 *
 * @param <T>
 */
public class BaseDao<T> {
	
	private ConnectionManager connectionManager = null;
	
	public BaseDao(SourceType sourceType, DbType dbType){
		this.connectionManager = ConnectionManager.getConnectionManager(sourceType, dbType);
	}
	
	/**
	 * 
	 * 数据库插入操作
	 * 
	 * @param sql
	 * @param objs
	 * @throws SQLException 
	 */
	public void insert(String sql, Object... args) throws SQLException{
		Connection connection = connectionManager.getConnection();
		PreparedStatement preparedStatement = null;
		preparedStatement = connection.prepareStatement(sql);
		
		// 条件		
		if(args != null && args.length > 0){
			for(int i = 0; i < args.length; i++){
				preparedStatement.setObject(i, args[i]);
			}
		}
		
		preparedStatement.execute();
		preparedStatement.close();
	}
	
	/**
	 * 
	 * 数据库删除操作
	 * 
	 * @param sql
	 * @param objs
	 * @throws SQLException 
	 */
	public void delete(String sql, Object... args) throws SQLException{
		Connection connection = connectionManager.getConnection();
		PreparedStatement preparedStatement = null;
		preparedStatement = connection.prepareStatement(sql);
		
		// 条件		
		if(args != null && args.length > 0){
			for(int i = 0; i < args.length; i++){
				preparedStatement.setObject(i, args[i]);
			}
		}
		
		preparedStatement.executeUpdate();
		preparedStatement.close();
	}
	
	/**
	 * 
	 * 数据库更新操作
	 * 
	 * @param sql
	 * @param objs
	 * @throws SQLException 
	 */
	public void update(String sql, Object... args) throws SQLException{
		Connection connection = connectionManager.getConnection();
		PreparedStatement preparedStatement = null;
		preparedStatement = connection.prepareStatement(sql);
		
		// 条件		
		if(args != null && args.length > 0){
			for(int i = 0; i < args.length; i++){
				preparedStatement.setObject(i, args[i]);
			}
		}
		
		preparedStatement.executeUpdate();
		preparedStatement.close();
	}
	
	/**
	 * 
	 * 查找一条记录
	 * 
	 * @param sql
	 * @param objs
	 * @return
	 */
	public T find(String sql, Object... args){
		return null;
	}
	
	/**
	 * 
	 * 查询数量
	 * 
	 * @param sql
	 * @param objs
	 * @return
	 */
	public long getCounts(String sql, Object... args){
		return 0;
	}
	
	/**
	 * 
	 * 查找全部
	 * 
	 * @param sql
	 * @param objs
	 * @return
	 */
	public List<T> findAll(String sql, Object... objs){
		return null;
	}
}
