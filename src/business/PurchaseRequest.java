package business;

import java.io.Serializable;
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
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@ManyToOne
	@JoinColumn(name="UserID") 
	private User user;
	private String description;
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="PurchaseRequestID")
	private ArrayList<PurchaseRequestLineItem> lineItems;
	
	public PurchaseRequest() {
		
	}
	
	public PurchaseRequest(int id, User user, String description) {
		this.id = id;
		this.user = user;
		this.description = description;
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

	public ArrayList<PurchaseRequestLineItem> getLineItems() {
		return lineItems;
	}

	public void setLineItems(ArrayList<PurchaseRequestLineItem> lineItems) {
		this.lineItems = lineItems;
	}

	@Override
	public String toString() {
		return "PurchaseRequest [id=" + id + ", \nuser=" + user + ", description=" + description + ", "
				+ "\nlineItems="+lineItems+"]";
	}
	
}
