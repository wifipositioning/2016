package com.wifipositioning.dao;

import java.util.List;

import com.wifiposition.dao.utils.DaoFactory;
import com.wifipositioning.dao.impl.FingerprintDao;
import com.wifipositioning.model.po.Fingerprint;
import com.wifipositioning.utils.type.db.DbType;
import com.wifipositioning.utils.type.source.SourceType;

public class DaoFactoryTest {

	public static void main(String[] args) throws Exception {
		DaoFactory daoFactory = DaoFactory.getDaoFactoryInstance();
		FingerprintDao fingerprintDao = (FingerprintDao) daoFactory.getDao(FingerprintDao.class, SourceType.C3P0, DbType.MYSQL);
//		FingerprintDao fingerprintDao2 = (FingerprintDao) daoFactory.getDao(FingerprintDao.class, SourceType.C3P0, DbType.MYSQL);
//		FingerprintDao fingerprintDao3 = (FingerprintDao) daoFactory.getNewDao(FingerprintDao.class, SourceType.C3P0, DbType.MYSQL);
//		System.out.println(fingerprintDao == fingerprintDao2);
//		System.out.println(fingerprintDao == fingerprintDao3);
		
		Fingerprint fingerprint = new Fingerprint("10", 32, 42.2f, 65.8f);
		Fingerprint fingerprint2 = new Fingerprint("11", 72, 39.8f, 19.8f);
		Fingerprint fingerprint3 = new Fingerprint("12", 52, 39.2f, 49.8f);
		Fingerprint[] fingerprints = {fingerprint, fingerprint2, fingerprint3};
//		fingerprintDao.addFingerprint(fingerprint);
//		fingerprintDao.addBatchFingerprints(fingerprints);
//		fingerprintDao.updateFingerprint(fingerprint);
//		fingerprintDao.updateBatchFingerprints(fingerprints);
//		fingerprintDao.deleteBatchFingerprints(fingerprints);
//		fingerprintDao.deleteFingerprint(fingerprint);
//		List<Fingerprint> results = fingerprintDao.findAllFingerprints();
//		List<Fingerprint> results = fingerprintDao.findFingerprintsByPos(39.2f, 49.8f);
//		System.out.println(results.size());
		
	}

}
