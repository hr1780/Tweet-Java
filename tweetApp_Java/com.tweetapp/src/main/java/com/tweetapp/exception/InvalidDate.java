package com.tweetapp.exception;

@SuppressWarnings("serial")
public class InvalidDate extends Exception {
	
	public InvalidDate(String message) {
		System.out.println(message);
		System.exit(0);
	}	
}
