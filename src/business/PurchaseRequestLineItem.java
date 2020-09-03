package business;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class PurchaseRequestLineItem {
	@Id
	private int id;
	@ManyToOne
	@JoinColumn(name="PurchaseRequestID") 
	private PurchaseRequest purchaseRequest;
	private int productID;
	private int quantity;
	
	public PurchaseRequestLineItem() {
		id = 0;
		purchaseRequest = null;
		productID = 0;
		quantity = 0;
	}
	
	public PurchaseRequestLineItem(PurchaseRequest inPr, int inPdtID, int inQty) {
		purchaseRequest = inPr;
		productID = inPdtID;
		quantity = inQty;
	}
	
	public PurchaseRequestLineItem(int inID, PurchaseRequest inPr, int inPdtID, int inQty) {
		id = inID;
		purchaseRequest = inPr;
		productID = inPdtID;
		quantity = inQty;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public PurchaseRequest getPurchaseRequest() {
		return purchaseRequest;
	}
	public void setPurchaseRequest(PurchaseRequest purchaseRequest) {
		this.purchaseRequest = purchaseRequest;
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
		return "\npurchaseRequestLineItem [id=" + id + ", purchaseRequestID=" + purchaseRequest.getId() + ", productID="
				+ productID + ", quantity=" + quantity + "]";
	}

}
