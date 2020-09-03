package business;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import db.DBUtil;

public class PurchaseRequestDB {
	public static PurchaseRequest getPRById(int prId) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		PurchaseRequest pr = null;
		try {
			pr = em.find(PurchaseRequest.class, prId);
		}
		finally {
			em.close();
		}
		return pr;
	}
	
	public static boolean addPurchaseRequest(PurchaseRequest pr){
		boolean success = false;
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction et = em.getTransaction();
		
		try {
			et.begin();
			em.persist(pr);
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
	
	public static boolean updatePurchaseRequest(PurchaseRequest pr){
		boolean success = false;
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction et = em.getTransaction();
		
		try {
			et.begin();
			em.merge(pr);
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
	
	public static boolean addPurchaseRequestLineItems(ArrayList<PurchaseRequestLineItem> prlis){
		boolean success = false;
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction et = em.getTransaction();
		
		try {
			et.begin();
			// insert prlis
			for (PurchaseRequestLineItem prli:prlis) {
				//prli.setPurchaseRequestID(pr.getId());  --> should already be done in app
				em.persist(prli);				
			}
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
