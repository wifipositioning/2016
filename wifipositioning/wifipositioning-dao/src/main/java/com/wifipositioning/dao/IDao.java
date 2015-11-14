package com.wifipositioning.dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Dao接口
 * 
 * @author liuyujie
 *
 * @param <T> PO对象类型
 */
public interface IDao<T> {
	
	/**
	 * 添加对象
	 * 
	 * @param obj 需要添加的对象
	 * @throws SQLException
	 */
	public void add(T obj)throws SQLException;
	
	/**
	 * 更新对象
	 * 
	 * @param obj
	 * @throws SQLException
	 */
	public void update(T obj)throws SQLException;
	
	/**
	 * 删除对象
	 * 
	 * @param obj
	 * @throws SQLException
	 */
	public void delete(T obj)throws SQLException;
	
	/**
	 * 条件查找
	 * 
	 * @param obj 查找条件
	 * @return 符合条件的对象列表
	 * @throws SQLException
	 */
	public <E extends Object> List<T> find(E obj)throws SQLException;
	
	/**
	 * 查找所有
	 * 
	 * @return 符合条件的对象列表
	 * @throws SQLException
	 */
	public List<T> findAll()throws SQLException;
}
