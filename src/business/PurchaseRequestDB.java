package business;

import javax.persistence.EntityManager;

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

}
