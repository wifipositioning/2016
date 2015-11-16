package com.wifipositioning.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wifipositioning.dao.BaseDao;
import com.wifipositioning.dao.inter.IFingerprintDao;
import com.wifipositioning.dao.mapper.IEntityMapper;
import com.wifipositioning.model.po.Fingerprint;
import com.wifipositioning.utils.type.db.DbType;
import com.wifipositioning.utils.type.source.SourceType;
import com.wifipositioning.utils.uuid.UUIDGenerator;

/**
 * 位置指纹Dao
 * 
 * @author liuyujie
 *
 */
public class FingerprintDao  extends BaseDao<Fingerprint> implements IFingerprintDao {
	
	private static IEntityMapper<Fingerprint> fingerprintMapper = new FingerprintMapper();
	
	public FingerprintDao(SourceType sourceType, DbType dbType){
		super(sourceType, dbType, fingerprintMapper);
	}
	
	public void addFingerprint(Fingerprint fingerprint) throws SQLException {
		String sql = "insert into fingerprints(id, mac, rss, x_pos, y_pos) values (?, ?, ?, ?, ?)";
		Object[] objs = {UUIDGenerator.generateUUID(), fingerprint.getMac(), fingerprint.getRss(), fingerprint.getxPos(), fingerprint.getyPos()};
		insert(sql, objs);
	}
	
	public void addBatchFingerprints(Fingerprint[] fingerprints) throws SQLException {
		String sql = "insert into fingerprints(id, mac, rss, x_pos, y_pos) values (?, ?, ?, ?, ?)";
		int count = fingerprints.length;
		Object[] objs = new Object[count];
		for(int i = 0; i <count; i++){
			Fingerprint fingerprint = fingerprints[i];
			Object[] obj = {UUIDGenerator.generateUUID(), fingerprint.getMac(), fingerprint.getRss(), fingerprint.getxPos(), fingerprint.getyPos()};
			objs[i] = obj;
		}
		insert(sql, objs);
	}

	public void updateFingerprint(Fingerprint fingerprint) throws SQLException {
		String sql = "update fingerprints set rss = ? where mac = ? and LTRIM(x_pos) = ? and LTRIM(y_pos) = ?";
		Object[] objs = {fingerprint.getRss(), fingerprint.getMac(), fingerprint.getxPos(), fingerprint.getyPos()};
		update(sql, objs);
	}

	public void updateBatchFingerprints(Fingerprint[] fingerprints) throws SQLException {
		String sql = "update fingerprints set rss = ? where mac = ? and LTRIM(x_pos) = ? and LTRIM(y_pos) = ?";
		int count = fingerprints.length;
		Object[] objs = new Object[count];
		for(int i = 0; i <count; i++){
			Fingerprint fingerprint = fingerprints[i];
			Object[] obj = {fingerprint.getRss(), fingerprint.getMac(), fingerprint.getxPos(), fingerprint.getyPos()};
			objs[i] = obj;
		}
		update(sql, objs);
	}

	public void deleteFingerprint(Fingerprint fingerprint) throws SQLException {
		String sql = "delete from fingerprints where mac = ? and LTRIM(x_pos) = ? and LTRIM(y_pos) = ?";
		Object[] objs = {fingerprint.getMac(), fingerprint.getxPos(), fingerprint.getyPos()};
		delete(sql, objs);
	}

	public void deleteBatchFingerprints(Fingerprint[] fingerprints) throws SQLException {
		String sql = "delete from fingerprints where mac = ? and LTRIM(x_pos) = ? and LTRIM(y_pos) = ?";
		int count = fingerprints.length;
		Object[] objs = new Object[count];
		for(int i = 0; i <count; i++){
			Fingerprint fingerprint = fingerprints[i];
			Object[] obj = {fingerprint.getMac(), fingerprint.getxPos(), fingerprint.getyPos()};
			objs[i] = obj;
		}
		delete(sql, objs);
	}

	@Override
	public List<Fingerprint> findAllFingerprints() throws SQLException {
		String sql = "select * from fingerprints";
		return findAll(sql);
	}

	@Override
	public List<Fingerprint> findFingerprintsByPos(float xPos, float yPos) throws SQLException {
		String sql = "select * from fingerprints where LTRIM(x_pos) = ? and LTRIM(y_pos) = ?";
		Object[] objs = {xPos, yPos}; 
		return findAll(sql, objs);
	}

	@Override
	public List<Fingerprint> findFingerprintsByMac(String... mac) throws SQLException {
		String sql = "select * from fingerprints where mac = ?";
		return findAll(sql, (Object[])mac);
	}
	
	public static class FingerprintMapper implements IEntityMapper<Fingerprint>{

		@Override
		public List<Fingerprint> mappingEntity(ResultSet rs) throws SQLException {
			List<Fingerprint> fingerprints = new ArrayList<>();
			while(rs.next()){
				Fingerprint fingerprint = new Fingerprint();
				fingerprint.setMac(rs.getString("mac"));
				fingerprint.setRss(rs.getInt("rss"));
				fingerprint.setxPos(rs.getFloat("x_pos"));
				fingerprint.setyPos(rs.getFloat("y_pos"));
				fingerprints.add(fingerprint);
			}
			return fingerprints;
		}
	}
	
}
