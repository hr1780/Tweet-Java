package com.tweetapp.service;

import java.util.regex.Pattern;

import com.tweetapp.dao.RegisterDAO;
import com.tweetapp.entity.RegisterEntity;
import com.tweetapp.exception.InvalidDate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class RegisterService {
	
	static RegisterDAO dao = new RegisterDAO();
	LoginService loginService = new LoginService();
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));  
	public void registerUser() throws IOException {
		LocalDate localDate = null;
		String first_name;
		String email;
		String gender;
		String password;
		System.out.println("ENTER YOUR DETAIL:");
		do{System.out.println("Enter your First Name");
		first_name= br.readLine();}
		while(first_name.isEmpty());
		System.out.println("Enter your Last Name");
		String last_name  = br.readLine();
		do {System.out.println("Enter Your Gender:");
		gender = br.readLine();}
		while (gender.isEmpty());
		System.out.println("Enter Date Of Birth(dd-MM-yyyy)");
		String date = br.readLine();
		try {		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		localDate = LocalDate.parse(date, formatter);
		}catch (DateTimeParseException e ) {
			 new InvalidDate("Invalid Date");
		}
		do {System.out.println("Enter your EmailId:");
		email = br.readLine();}
		while(!validateEmail(email)) ;
		do{System.out.println("Enter your password:");
		 password= br.readLine();}
		while(password.isEmpty());
		
		RegisterEntity registerEntity = new RegisterEntity(first_name, last_name, gender, localDate , email, password);
		if( dao.registerUser(registerEntity)){
			System.out.println("Registered Scucessfully");
			System.out.println("Press Enter to got menu");
			br.readLine();
		}
		else {
			System.out.println("Registeration Fail :");
			System.out.println("Press Enter to got menu");
			br.readLine();
		}
	}

	public void loginUser() throws IOException {
		System.out.println("Enter Your Email Id");
		String email = br.readLine();
		System.out.println("Enter Your Password");
		String password = br.readLine();
		
		if(dao.loginUser(email, password)) {
			loginService.loginMenu(email);
		}
		else {
			System.out.println("Worng Credentails");
		}
	}
	
	public boolean validateEmail(String email) 
    { 
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ 
                            "[a-zA-Z0-9_+&*-]+)*@" + 
                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
                            "A-Z]{2,7}$"; 
                              
        Pattern pat = Pattern.compile(emailRegex); 
        if (email == null) {
        	System.out.println("Not a Valid  null Email");
        	return false; 
        }
        else if (pat.matcher(email).matches() && dao.isExistingEmail(email)) {
			return true;}
        else if (pat.matcher(email).matches() && !dao.isExistingEmail(email)) {
        	System.out.println("Email already exits");
			return false;}
		 else {
			System.out.println("Not a Valid Email");
        	return false; 
		}
    }

	public void forgotPassord() throws IOException {
		LocalDate localDate = null;
		System.out.println("Enter Your First Name :");
		String fname = br.readLine();
		System.out.println("Enter Your Last Name");
		String lname = br.readLine();
		System.out.println("Enter Your Email");
		String email = br.readLine();
		System.out.println("Enter Your DOB");
		String date = br.readLine();
		try {		
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			localDate = LocalDate.parse(date, formatter);
			}catch (DateTimeParseException e ) {
				 new InvalidDate("Invalid Date");
			}
        if(dao.forgotPassword(fname , lname,email, localDate)){
        	br.readLine();
        }else {
			System.out.println("No Such User ");
			System.out.println("Press Enter to got menu");
			br.readLine();
		}
	}

	public static void resetPassword(String email) throws IOException {
		System.out.println("ENter New Password");
		String new_pass = br.readLine();
		if(dao.restPassword(new_pass ,email)) {
			System.out.println("Password Changed ");
		}
	}

}