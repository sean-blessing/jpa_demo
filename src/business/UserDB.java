package business;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import db.DBUtil;

public class UserDB {
	public static ArrayList<User> getAllUsers() {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		try {
			TypedQuery<User> query = em.createQuery("SELECT u FROM User u",User.class);
			ArrayList<User> allUsers = new ArrayList<User>(query.getResultList());
			return allUsers;
		}
		finally {
			em.close();
		}
	}
	
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
			//em.flush();
			et.commit();
			success = true;
		}
		catch (Exception e) {
			e.printStackTrace();
			et.rollback();
		}
		finally {
			em.close();
		}
		return success;
	}

	public static boolean deleteUser(User u) {
		boolean success = false;
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction et = em.getTransaction();
		
		try {
			et.begin();
			em.remove(em.merge(u));
			et.commit();
			success = true;
		}
		catch (Exception e) {
			e.printStackTrace();
			et.rollback();
		}
		finally {
			em.close();
		}
		return success;
	}

	public static boolean updateUser(User u) {
		boolean success = false;
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction et = em.getTransaction();
		
		try {
			et.begin();
			em.merge(u);
			et.commit();
			success = true;
		}
		catch (Exception e) {
			e.printStackTrace();
			et.rollback();
		}
		finally {
			em.close();
		}
		return success;
	}
}
