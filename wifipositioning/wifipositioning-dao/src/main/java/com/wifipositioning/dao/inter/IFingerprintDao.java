package com.wifipositioning.dao.inter;

import java.sql.SQLException;
import java.util.List;

import com.wifipositioning.model.po.Fingerprint;

/**
 * 位置指纹Dao接口
 * 
 * @author liuyujie
 *
 * @param <T> PO对象类型
 */
public interface IFingerprintDao{
	
	/**
	 * 添加对象
	 * 
	 * @param fingerprint 需要添加的对象
	 * @throws SQLException
	 */
	public void add(Fingerprint fingerprint)throws SQLException;
	
	/**
	 * 更新对象
	 * 
	 * @param fingerprint
	 * @throws SQLException
	 */
	public void update(Fingerprint fingerprint)throws SQLException;
	
	/**
	 * 删除对象
	 * 
	 * @param fingerprint
	 * @throws SQLException
	 */
	public void delete(Fingerprint fingerprint)throws SQLException;
	
	/**
	 * 条件查找位置指纹
	 * 
	 * @param sql SQL查询语句
	 * @return 符合条件的对象列表
	 * @throws SQLException
	 */
	public Fingerprint find(String sql)throws SQLException;
	
	/**
	 * 查找位置指纹对象
	 * 
	 * @return 符合条件的对象列表
	 * @throws SQLException
	 */
	public List<Fingerprint> findBatch()throws SQLException;
	
	
	/**
	 * 批量添加位置指纹对象
	 * 
	 * @param fingerprints 需要添加的对象数组
	 * @throws SQLException
	 */
	public void addBatch(Fingerprint[] fingerprints)throws SQLException;
	
	/**
	 * 批量更新位置指纹对象
	 * 
	 * @param fingerprint
	 * @throws SQLException
	 */
	public void updateBatch(Fingerprint[] fingerprints)throws SQLException;
	
	/**
	 * 批量删除位置指纹对象
	 * 
	 * @param fingerprint
	 * @throws SQLException
	 */
	public void deleteBatch(Fingerprint[] fingerprints)throws SQLException;
}
