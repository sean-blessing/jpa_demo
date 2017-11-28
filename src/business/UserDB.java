package business;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import db.DBUtil;

public class UserDB {
	public static User getUserById(int userId) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		try {
			User user = em.find(User.class, userId);
			return user;
		}
		finally {
			em.close();
		}
	}
	
	public static boolean addUser(User u) {
		boolean success = false;
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction et = em.getTransaction();
		
		try {
			et.begin();
			em.persist(u);
			et.commit();
			success = true;
		}
		catch (Exception e) {
			et.rollback();
			e.printStackTrace();
		}
		finally {
			em.close();
		}
		return success;
	}
}
