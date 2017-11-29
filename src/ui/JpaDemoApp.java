package ui;

import java.sql.Timestamp;
import java.util.ArrayList;

import business.User;
import business.UserDB;
import util.Console;

public class JpaDemoApp {

	public static void main(String[] args) {
		System.out.println("JPA Demo");
		String choice = "";
		while (!choice.equalsIgnoreCase("exit")) {
			System.out.println("Options:");
			System.out.println("all     - get all users");
			System.out.println("get     - get user by id");
			System.out.println("add     - add user");
			System.out.println("del     - delete user by id");
			System.out.println("update  - update user");
			System.out.println("exit    - exit app");
			System.out.println();
			choice = Console.getString("Option?:  ");
			
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
		}
		System.out.println("Bye!");
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
			System.out.println("User '"+u.getUserName()+"' successfully added!");
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
}
