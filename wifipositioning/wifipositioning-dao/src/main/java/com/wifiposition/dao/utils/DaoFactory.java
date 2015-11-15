package com.wifiposition.dao.utils;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

import com.wifipositioning.utils.type.db.DbType;
import com.wifipositioning.utils.type.source.SourceType;

/**
 * Dao工厂类
 * <br/>
 * Dao层的唯一对外开放的工厂类，上层使用时必须通过该工厂类获取Dao对象。非必要底层实现不对外提供服务。
 * 
 * @author liuyujie
 *
 */
public class DaoFactory {
	private static DaoFactory daoFactory = null;
	
	private Map<Class<?>, Object> daoContainer= new HashMap<Class<?>, Object>();
	
	private DaoFactory(){
		
	}
	
	public synchronized static DaoFactory getDaoFactoryInstance(){
		if(daoFactory == null){
			daoFactory = new DaoFactory();
		}
		
		return daoFactory;
	}
	
	/**
	 * 
	 * 获取Dao对象(Singleton)
	 * 
	 * @param clazz Dao类型
	 * @param sourceType 数据源类型
	 * @param dbType 数据库类型
	 * @return
	 * @throws Exception
	 */
	public synchronized Object getDao(Class<?> clazz, SourceType sourceType, DbType dbType) throws Exception{
		if(daoContainer.containsKey(clazz)){
			return daoContainer.get(clazz);
		}
		return createDao(clazz, sourceType, dbType);
	}
	
	/**
	 * 
	 * 获取新的Dao对象
	 * <br/>
	 * 一般用于数据源或者数据库变化时，重新生成Dao对象，此方法可以忽略
	 * 
	 * @param clazz Dao类型
	 * @param sourceType 数据源类型
	 * @param dbType 数据库类型
	 * @return
	 * @throws Exception
	 */
	public synchronized Object getNewDao(Class<?> clazz, SourceType sourceType, DbType dbType) throws Exception{
		if(daoContainer.containsKey(clazz)){
			daoContainer.remove(clazz);
		}
		return createDao(clazz, sourceType, dbType);
	}
	
	/**
	 * 
	 * 创建Dao对象
	 * 
	 * @param clazz Dao类型
	 * @param sourceType 数据源类型
	 * @param dbType 数据库类型
	 * @return
	 * @throws Exception
	 */
	private Object createDao(Class<?> clazz, SourceType sourceType, DbType dbType) throws Exception{
		Constructor<?> constructor = (Constructor<?>) clazz.getConstructor(SourceType.class, DbType.class);
		Object obj = constructor.newInstance(sourceType, dbType);
		daoContainer.put(clazz, obj);
		return obj;
	}
}
