package business;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import db.DBUtil;

public class ProductDB {
	public static ArrayList<Product> getProducts() {
		ArrayList<Product> products = new ArrayList<>();
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		try {
			TypedQuery<Product> query = em.createQuery("SELECT p FROM Product p",Product.class);
			products = new ArrayList<Product>(query.getResultList());
		}
		finally {
			em.close();
		}

		
		return products;
	}

	public static Product getProductById(int pID) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		try {
			Product p = em.find(Product.class, pID);
			return p;
		}
		finally {
			em.close();
		}
	}

}
