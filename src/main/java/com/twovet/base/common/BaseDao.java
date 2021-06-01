package com.twovet.base.common;

import javax.persistence.EntityManagerFactory;

public class BaseDao {
	
	public static EntityManagerFactory emf;
//	public static EntityManager em;
	
	public BaseDao(EntityManagerFactory emf) {
		// TODO Auto-generated constructor stub
		if (BaseDao.emf == null) {
			BaseDao.emf = emf;
//			if (BaseBao.em == null) {
//				BaseBao.em = emf.createEntityManager();
//			}
		}
	}
	
}
