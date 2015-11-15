package com.wifipositioning.dao;

import com.wifiposition.dao.utils.DaoFactory;
import com.wifipositioning.dao.impl.FingerprintDao;
import com.wifipositioning.utils.type.db.DbType;
import com.wifipositioning.utils.type.source.SourceType;

public class DaoFactoryTest {

	public static void main(String[] args) throws Exception {
		DaoFactory daoFactory = DaoFactory.getDaoFactoryInstance();
		FingerprintDao fingerprintDao1 = (FingerprintDao) daoFactory.getDao(FingerprintDao.class, SourceType.C3P0, DbType.MYSQL);
		FingerprintDao fingerprintDao2 = (FingerprintDao) daoFactory.getDao(FingerprintDao.class, SourceType.C3P0, DbType.MYSQL);
		FingerprintDao fingerprintDao3 = (FingerprintDao) daoFactory.getNewDao(FingerprintDao.class, SourceType.C3P0, DbType.MYSQL);
		System.out.println(fingerprintDao1 == fingerprintDao2);
		System.out.println(fingerprintDao1 == fingerprintDao3);
	}

}
