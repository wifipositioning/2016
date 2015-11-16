package com.wifipositioning.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.wifiposition.dao.utils.ConnectionManager;
import com.wifipositioning.dao.mapper.IEntityMapper;
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
	
	private IEntityMapper<T> entityMapper = null;
	
	public BaseDao(SourceType sourceType, DbType dbType, IEntityMapper<T> entityMapper){
		this.connectionManager = ConnectionManager.getConnectionManager(sourceType, dbType);
		this.entityMapper = entityMapper;
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
		doUpdate(sql, args);
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
		doUpdate(sql, args);
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
		doUpdate(sql, args);
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
	 * @throws SQLException 
	 */
	public List<T> findAll(String sql, Object... args) throws SQLException{
		Connection connection = connectionManager.getConnection();
		PreparedStatement preparedStatement = null;
		preparedStatement = connection.prepareStatement(sql);
		
		int paramCount = args.length;
		for(int i = 0; i < paramCount; i++){
			// PreparedStatement下标从1开始	
			preparedStatement.setObject(i + 1, args[i]);
		}
		
		ResultSet resultSet = preparedStatement.executeQuery();
		List<T> results = entityMapper.mappingEntity(resultSet);
		
		resultSet.close();
		preparedStatement.close();
		
		return results;
	}
	
	/**
	 * 设置PreparedStatement对象参数
	 * <br/>
	 * 分批量和单条记录处理，二者不能混用
	 * 
	 * @param preparedStatement
	 * @param objs
	 * @return 批量操作 <code>false</code>， 单条操作 <code>true</code>
	 * @throws SQLException 
	 */
	private boolean setParams(PreparedStatement preparedStatement, Object... args) throws SQLException{
		int paramCount = args.length;
		// 批量标识		
		boolean batchFlag = false;
		// 非批量标识		
		boolean singleFlag = false;
		for(int i = 0; i < paramCount; i++){
			// 批量处理			
			if(args[i] instanceof Object[]){
				if(singleFlag){
					throw new SQLException("Batch and Single Mixed");
				}
				batchFlag = true;
				
				Object[] objs = (Object[]) args[i];
				int size = objs.length;
				for(int j = 0; j < size; j++){
					// PreparedStatement下标从1开始	
					preparedStatement.setObject(j + 1, objs[j]);
				}
				
				preparedStatement.addBatch();
			}
			//非批量处理
			else{
				if(batchFlag){
					throw new SQLException("Batch and Single Mixed");
				}
				singleFlag = true;
				// PreparedStatement下标从1开始				
				preparedStatement.setObject(i + 1, args[i]);
			}
		}
		
		return singleFlag;
	}
	
	/**
	 * 
	 * 数据库Update操作，针对插入、更新和删除操作
	 * 
	 * @param sql
	 * @param args
	 * @throws SQLException
	 */
	private void doUpdate(String sql, Object... args) throws SQLException{
		Connection connection = connectionManager.getConnection();
		PreparedStatement preparedStatement = null;
		preparedStatement = connection.prepareStatement(sql);
		
		// 单条记录		
		if(setParams(preparedStatement, args)){
			@SuppressWarnings("unused")
			int result = preparedStatement.executeUpdate();
//			System.out.println(result);
		}
		// 批量插入		
		else{
			int[] result = preparedStatement.executeBatch();
			for(int i = 0; i < result.length; i++){
//				System.out.println(result[i]);
			}
		}
		
		preparedStatement.close();
	}
}
