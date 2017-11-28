package ui;

import java.sql.Timestamp;
import java.util.Scanner;

import business.User;
import business.UserDB;
import util.Console;

public class JpaDemoApp {

	public static void main(String[] args) {
		System.out.println("JPA Demo");
		int userId = Console.getInt("Enter userID to retrieve:  ");
		User u = UserDB.getUserById(userId);
		System.out.println("User details:  "+u);

		String userName = Console.getString("Username:  ");
		String password = Console.getString("Pwd:  ");
		String firstName = Console.getString("FName:  ");
		String lastName = Console.getString("LName:  ");
		String phoneNumber = Console.getString("Phone:  ");
		String email = Console.getString("Email:  ");
		boolean reviewer = Console.getBoolean("Reviewer:  "); 
		boolean admin = Console.getBoolean("Admin:  ");
		User u2 = new User(userName, password, firstName, lastName, phoneNumber, email, reviewer, admin);
		u2.setDateCreated(new Timestamp(System.currentTimeMillis()));
		
		if (UserDB.addUser(u2)) {
			System.out.println("User successfully added!");
		}
		System.out.println("Bye!");

	}

}
