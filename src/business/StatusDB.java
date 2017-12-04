package business;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import db.DBUtil;

public class StatusDB {
	public static ArrayList<Status> getAllStatus() {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		try {
			TypedQuery<Status> query = em.createQuery("SELECT u FROM Status u",Status.class);
			ArrayList<Status> allStatus = new ArrayList<Status>(query.getResultList());
			return allStatus;
		}
		finally {
			em.close();
		}
	}
	
	public static Status getStatusById(int statusId) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		try {
			Status status = em.find(Status.class, statusId);
			return status;
		}
		finally {
			em.close();
		}
	}

}
