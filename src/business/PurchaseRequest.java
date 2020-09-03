package business;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class PurchaseRequest implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name="UserID") 
	private User user;
	private String description;
	private String justification;
	private Timestamp dateNeeded;
	private String deliveryMode;
	@ManyToOne
	@JoinColumn(name="StatusID") 
	private Status status;
	private double total;
	private Timestamp submittedDate;
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="PurchaseRequestID")
	private ArrayList<PurchaseRequestLineItem> lineItems;
	
	public PurchaseRequest() {
		
	}
	
	public PurchaseRequest(int id, User user, String description, Status status) {
		this.id = id;
		this.user = user;
		this.description = description;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public String getJustification() {
		return justification;
	}

	public void setJustification(String justification) {
		this.justification = justification;
	}

	public Timestamp getDateNeeded() {
		return dateNeeded;
	}

	public void setDateNeeded(Timestamp dateNeeded) {
		this.dateNeeded = dateNeeded;
	}

	public String getDeliveryMode() {
		return deliveryMode;
	}

	public void setDeliveryMode(String deliveryMode) {
		this.deliveryMode = deliveryMode;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Timestamp getSubmittedDate() {
		return submittedDate;
	}

	public void setSubmittedDate(Timestamp submittedDate) {
		this.submittedDate = submittedDate;
	}

	public ArrayList<PurchaseRequestLineItem> getLineItems() {
		return lineItems;
	}

	public void setLineItems(ArrayList<PurchaseRequestLineItem> lineItems) {
		this.lineItems = lineItems;
	}

	@Override
	public String toString() {
		return "PurchaseRequest [id=" + id + ", \nuser=" + user + ", description=" + description + ", "
				+ "status="+status+ ", total="+total+"\nlineItems="+lineItems+"]";
	}
	
}
