package com.wifipositioning.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.wifipositioning.dao.BaseDao;
import com.wifipositioning.dao.inter.IFingerprintDao;
import com.wifipositioning.model.po.Fingerprint;
import com.wifipositioning.utils.type.db.DbType;
import com.wifipositioning.utils.type.source.SourceType;

public class FingerprintDao implements IFingerprintDao {

	private BaseDao<Fingerprint> baseDao = null;
	
	public FingerprintDao(SourceType sourceType, DbType dbType){
		this.baseDao = new BaseDao<Fingerprint>(sourceType, dbType);
	}
	
	public void add(Fingerprint fingerprint) throws SQLException {
		
	}

	public void update(Fingerprint fingerprint) throws SQLException {
		
	}

	public void delete(Fingerprint fingerprint) throws SQLException {
		
	}

	public Fingerprint find(String sql) throws SQLException {
		return null;
	}

	public List<Fingerprint> findBatch() throws SQLException {
		return null;
	}

	public void addBatch(Fingerprint[] fingerprints) throws SQLException {
		
	}

	public void updateBatch(Fingerprint[] fingerprints) throws SQLException {
		
	}

	public void deleteBatch(Fingerprint[] fingerprints) throws SQLException {
		
	}
	
}
