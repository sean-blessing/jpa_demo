package ui;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;

import business.Product;
import business.ProductDB;
import business.PurchaseRequest;
import business.PurchaseRequestDB;
import business.PurchaseRequestLineItem;
import business.Status;
import business.StatusDB;
import business.User;
import business.UserDB;
import util.Console;

public class JpaDemoApp {
	// Keep a cache of Status records, key=id value=description
	private static HashMap<Integer, String> statusMap;
	private static User validatedUser;

	public static void main(String[] args) {
		//initializeStatusMap();
		System.out.println("JPA Demo");
		String choice = "";
		while (!choice.equalsIgnoreCase("exit")) {
			System.out.println("Options:");
			if (validatedUser==null) {
				System.out.println("login   - login");	
			}
			else {
				System.out.println("all     - get all users");
				System.out.println("get     - get user by id");
				System.out.println("add     - add user");
				System.out.println("del     - delete user by id");
				System.out.println("update  - update user");
				System.out.println("pr      - get purchase request");
				System.out.println("newpr   - enter new pr request");
				System.out.println("prli    - enter pr line items");
				System.out.println("status  - get all status records");
			}
			System.out.println("exit    - exit app");
			System.out.println();
			choice = Console.getString("Option?:  ");
			
			if (choice.equals("login")) {
				login();
			}
			if (choice.equals("get")) {
				getUser();
			}
			else if (choice.equals("all")) {
				getAllUsers();
			}
			else if (choice.equals("add")) {
				addUser();
			}
			else if (choice.equals("del")) {
				deleteUser();
			}
			else if (choice.equals("update")) {
				updateUser();
			}
			else if (choice.equals("status")) {
				getAllStatus();
			}
			else if (choice.equals("newpr")) {
				enterPR();
			}
			else if (choice.equals("prli")) {
				enterPRLineItems();
			}
			else if (choice.equals("pr")) {
				getPR();
			}
		}
		System.out.println("Bye!");
	}

	public static void login() {
		System.out.println("User Login");
		String userName = Console.getString("Username: ");
		String password = Console.getString("Password: ");
		validatedUser = UserDB.authenticateUser(userName, password);
		if (validatedUser!= null) {
			System.out.println("\nSuccess: you are logged in.");
			System.out.println(validatedUser);
			}
		else {
			System.out.println("Invalid username/password combination");
		}
	}
	private static void getUser() {
		int userId = Console.getInt("Enter userID to retrieve:  ");
		User u = UserDB.getUserById(userId);
		System.out.println("User details:  "+u);
		System.out.println();
	}

	private static void getAllUsers() {
		ArrayList<User> users = UserDB.getAllUsers();
		System.out.println("All users:");
		for (User u:users) {
			System.out.println(u);
		}
		System.out.println();
	}

	private static void getAllStatus() {
		ArrayList<Status> status = StatusDB.getAllStatus();
		System.out.println("All status records:");
		for (Status s:status) {
			System.out.println(s);
		}
		System.out.println();
	}

	private static void initializeStatusMap() {
		statusMap = new HashMap<>();
		ArrayList<Status> status = StatusDB.getAllStatus();
		for (Status s:status) {
			statusMap.put(s.getId(), s.getDescription());
		}
	}

	private static void addUser() {
		String userName = Console.getString("Username:  ");
		String password = Console.getString("Pwd:  ");
		String firstName = Console.getString("FName:  ");
		String lastName = Console.getString("LName:  ");
		String phoneNumber = Console.getString("Phone:  ");
		String email = Console.getString("Email:  ");
		boolean reviewer = Console.getBoolean("Reviewer:  "); 
		boolean admin = Console.getBoolean("Admin:  ");
		User u = new User(userName, password, firstName, lastName, phoneNumber, email, reviewer, admin);
		u.setDateCreated(new Timestamp(System.currentTimeMillis()));
		
		if (UserDB.addUser(u)) {
			System.out.println("User '"+u.getUserName()+"', id="+u.getId()+" successfully added!");
		}
		System.out.println();
	}

	private static void deleteUser() {
		int userId = Console.getInt("Enter userID to delete:  ");
		User u = UserDB.getUserById(userId);
		if(UserDB.deleteUser(u)) {
			System.out.println("User '"+u.getUserName()+"' successfully deleted!");
		}
		System.out.println();
	}

	private static void updateUser() {
		int userId = Console.getInt("Enter userID to update:  ");
		User u = UserDB.getUserById(userId);
		String fName = Console.getString("Enter new value for first name:  ");
		u.setFirstName(fName);
		if(UserDB.updateUser(u)) {
			System.out.println("User '"+u.getUserName()+"' successfully updated!");
		}
		System.out.println();
	}
	
	private static void getPR() {
		int prID = Console.getInt("Enter id of pr to retrieve: ");
		PurchaseRequest pr = PurchaseRequestDB.getPRById(prID);
		//String desc = statusMap.get(pr.getStatusId());
		//System.out.println("PR:  "+pr+" status desc = "+desc);
		System.out.println("PR # of LineItems:"+pr.getLineItems().size());
		System.out.println("PR:  "+pr);
		System.out.println("bye");
	}
	
	private static void enterPR() {
		PurchaseRequest pr = new PurchaseRequest();
		String desc = Console.getLine("Description:  ");
		String just = Console.getLine("Justification:  ");
		String dateNeededStr = Console.getString("Date needed by (YYYY-MM-DD):	");
		Timestamp dateNeeded = Console.getTimestampFromYYYYMMDD(dateNeededStr);
		String dlvMode = Console.getString("Delivery Mode:   ");
		Status s = StatusDB.getStatusById(1);
		
		pr.setUser(validatedUser);
		pr.setDescription(desc);
		pr.setJustification(just);
		pr.setDateNeeded(dateNeeded);
		pr.setDeliveryMode(dlvMode);
		pr.setStatus(s);
		pr.setSubmittedDate(new Timestamp(System.currentTimeMillis()));
		if (PurchaseRequestDB.addPurchaseRequest(pr)) {
			System.out.println("Success!  Added PR.");
			System.out.println("PR details:"+pr);
		}
		else {
			System.out.println("Problem adding PR.");
		}

	}
	
	private static void enterPRLineItems() {
		ArrayList<PurchaseRequestLineItem> prlis = new ArrayList<>();
		// get pr for id user enters
		int prID = Console.getInt("Enter pr id:  ");
		PurchaseRequest pr = PurchaseRequestDB.getPRById(prID);
		System.out.println("PurchaseRequest: id="+pr.getId()+", desc="+pr.getDescription());
		
		String choice = "y";
		double newTotal = 0.0;
		while (choice.equalsIgnoreCase("y")) {
		
			// list products
			System.out.println("Product selection:  ");
			System.out.println("====================");
			ArrayList<Product> products = ProductDB.getProducts();
			for (Product p: products) {
				System.out.println("ID="+p.getId()+", part#="+p.getPartNumber()+", name="+p.getName()+", price="+p.getPrice());
			}
			System.out.println();
			
			// choose product, enter qty
			int pdtId = Console.getInt("Product ID:  ");
			Product p = ProductDB.getProductById(pdtId);
			int qty = Console.getInt("Quantity?:  ");
			newTotal += p.getPrice() * qty;
			
			// Create prli and add to list
			PurchaseRequestLineItem prli = new PurchaseRequestLineItem(pr, p.getId(), qty);
			prlis.add(prli);
			choice = Console.getString("Continue?:  ");
		}		
		// update PR
		pr.setSubmittedDate(new Timestamp(System.currentTimeMillis()));
		pr.setTotal(newTotal);
		if (PurchaseRequestDB.updatePurchaseRequest(pr))
			System.out.println("PR updated successfully!");
		else
			System.out.println("Error updating PR.");
		
		// insert prlis
		if (PurchaseRequestDB.addPurchaseRequestLineItems(prlis))
			System.out.println("PR Line Items added successfully!");
		else
			System.out.println("Issue adding PR Line Items");
	}
	
}
