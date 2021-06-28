package com.tweetapp.service;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


import com.tweetapp.dao.LoginDAO;


public class LoginService {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));  
	LoginDAO dao = new LoginDAO();

	public void loginMenu(String email) throws IOException { 

		System.out.println("Your Choices:");
		System.out.println("1.> Change Password");
		System.out.println("2.> Post a tweet");
		System.out.println("3.> View Post");
		System.out.println("4.> View All");
		System.out.println("5.> logout");
		int choices = Integer.parseInt(br.readLine());
		
		switch (choices) {
		case 1:
			changePassword(email);
			break;
		case 2:
			postTweet(email);
			break;
		case 3:
			viewTweet(email);
			break;
		case 4:
			viewAllTweet(email);
			break;
		case 5:
			logout(email);
		default:
			System.out.println("Not a Valid selection \n Press enter key return to Menu");
			br.readLine();
			loginMenu(email);
			break ;
		}
	}


	private void logout(String email) {
			if(dao.logout(email));
			System.out.println("You have loged Out");
			System.exit(0);
    	}

	private void viewAllTweet(String email) throws IOException {
		dao.viewAllTweet();
		System.out.println("Press enter key return to Menu");
		br.readLine();
		loginMenu(email);		
		}

	private void viewTweet(String email) throws IOException {
		
		dao.viewTweet(email);
		System.out.println("Press enter key return to Menu");
		br.readLine();
		loginMenu(email);
	}

	private void postTweet(String email) throws IOException {
		System.out.println("Post your tweet");
		String tweet = br.readLine();
		dao.postTweet(email ,tweet);
		System.out.println("Press enter key return to Menu");
		br.readLine();
		loginMenu(email);
		
	}

	public void changePassword(String email) throws IOException {
		System.out.println("Enter Your Current Password:");
		String curr_Pass = br.readLine();
		System.out.println("Enter Your New Password: ");
		String new_Pass = br.readLine();
		System.out.println("Confirm new Password: ");
		String con_Pass = br.readLine();
		
		if(curr_Pass.isEmpty()||new_Pass.isEmpty()||con_Pass.isEmpty()) {
			System.out.println("Please fill all the details");
		}else if (curr_Pass.equals(new_Pass)){
			System.out.println("Old password and New Password cannot be same");
			}
		else if(!new_Pass.equals(con_Pass)) {
			System.out.println("Current password and New password mismatch");
		}else {
			dao.changePassword(email,curr_Pass,new_Pass);
		}
		System.out.println("Press enter key return to Menu");
		br.readLine();
		loginMenu(email);
	}
}
