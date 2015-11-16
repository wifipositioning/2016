package com.wifipositioning.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * 结果集和实体对象的映射器
 * 
 * @author liuyujie
 *
 * @param <T>
 */
public interface IEntityMapper<T> {
	
	/**
	 * 返回结果集所映射的实体对象集合
	 * 
	 * @return
	 */
	public List<T> mappingEntity(ResultSet rs)  throws SQLException;
}
