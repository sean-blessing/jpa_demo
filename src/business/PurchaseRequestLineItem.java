package business;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PurchaseRequestLineItem {
	@Id
	private int id;
	private int purchaseRequestID;
	private int productID;
	private int quantity;
	
	public PurchaseRequestLineItem() {
		id = 0;
		purchaseRequestID = 0;
		productID = 0;
		quantity = 0;
	}
	
	public PurchaseRequestLineItem(int inPrID, int inPdtID, int inQty) {
		purchaseRequestID = inPrID;
		productID = inPdtID;
		quantity = inQty;
	}
	
	public PurchaseRequestLineItem(int inID, int inPrID, int inPdtID, int inQty) {
		id = inID;
		purchaseRequestID = inPrID;
		productID = inPdtID;
		quantity = inQty;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPurchaseRequestID() {
		return purchaseRequestID;
	}
	public void setPurchaseRequestID(int purchaseRequestID) {
		this.purchaseRequestID = purchaseRequestID;
	}
	public int getProductID() {
		return productID;
	}
	public void setProductID(int productID) {
		this.productID = productID;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "\npurchaseRequestLineItem [id=" + id + ", purchaseRequestID=" + purchaseRequestID + ", productID="
				+ productID + ", quantity=" + quantity + "]";
	}

}
