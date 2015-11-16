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
	 * 添加位置指纹对象
	 * 
	 * @param fingerprint 需要添加的对象
	 * @throws SQLException
	 */
	public void addFingerprint(Fingerprint fingerprint)throws SQLException;
	
	/**
	 * 更新对象
	 * 
	 * @param fingerprint
	 * @throws SQLException
	 */
	public void updateFingerprint(Fingerprint fingerprint)throws SQLException;
	
	/**
	 * 删除对象
	 * 
	 * @param fingerprint
	 * @throws SQLException
	 */
	public void deleteFingerprint(Fingerprint fingerprint)throws SQLException;
	
	/**
	 * 查找全部位置指纹对象
	 * 
	 * @return 符合条件的对象列表
	 * @throws SQLException
	 */
	public List<Fingerprint> findAllFingerprints()throws SQLException;
	
	/**
	 * 根据坐标，查找位置指纹对象
	 * 
	 * @param xPos x坐标
	 * @param yPos y坐标
	 * @return 符合条件的对象列表
	 * @throws SQLException
	 */
	public List<Fingerprint> findFingerprintsByPos(float xPos, float yPos)throws SQLException;
	
	/**
	 * 根据Mac地址，查找位置指纹对象
	 * 
	 * @param mac mac地址数组
	 * @return 符合条件的对象列表
	 * @throws SQLException
	 */
	public List<Fingerprint> findFingerprintsByMac(String... mac)throws SQLException;
	
	
	/**
	 * 批量添加位置指纹对象
	 * 
	 * @param fingerprints 需要添加的对象数组
	 * @throws SQLException
	 */
	public void addBatchFingerprints(Fingerprint[] fingerprints)throws SQLException;
	
	/**
	 * 批量更新位置指纹对象
	 * 
	 * @param fingerprint
	 * @throws SQLException
	 */
	public void updateBatchFingerprints(Fingerprint[] fingerprints)throws SQLException;
	
	/**
	 * 批量删除位置指纹对象
	 * 
	 * @param fingerprint
	 * @throws SQLException
	 */
	public void deleteBatchFingerprints(Fingerprint[] fingerprints)throws SQLException;
}
