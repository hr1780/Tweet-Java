package com.tweetapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.tweetapp.util.JDBCconnection;

public class LoginDAO {

	public void changePassword(String email, String curr_pass, String new_pass) {
		Connection con = null;
		PreparedStatement pst = null;
		String query = "select * from registeruser where email=? and password=?";
		try {
			con = JDBCconnection.getJdbcConnection();
			pst = con.prepareStatement(query);
			pst.setString(1, email);
			pst.setString(2, curr_pass);
			ResultSet result = pst.executeQuery();
			if (result.next()) {
				String query1 = "UPDATE tweet.registeruser SET password=? WHERE (email=?);";
				PreparedStatement preparedStatement = con.prepareStatement(query1);
				preparedStatement.setString(1, new_pass);
				preparedStatement.setString(2, email);
				if (preparedStatement.executeUpdate() != 0) {
					System.out.println("Password Change Sucessfully");
				}
			} else {
				System.out.println("Invalid Current Password");
			}
		} catch (SQLException e) {
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public boolean postTweet(String email, String tweet) {
		Connection con = null;
		PreparedStatement pst = null;
		String query = "insert into posttweet (email , tweet) values (?,?)";
		try {
			con = JDBCconnection.getJdbcConnection();
			pst = con.prepareStatement("CREATE TABLE IF NOT EXISTS posttweet (email varchar(255), tweet varchar(255)) ");
			pst.execute();
			pst = con.prepareStatement(query);
			pst.setString(1, email);
			pst.setString(2, tweet);
			int x = pst.executeUpdate();
			if (x > 0) {
				return true;
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public void viewTweet(String email) {
		Connection con = null;
		PreparedStatement pst = null;
		String query = "select * from posttweet where email = ?";
		try {
			con = JDBCconnection.getJdbcConnection();
			pst = con.prepareStatement(query);
			pst.setString(1, email);
			System.out.println("All Tweet's From : " + email);
			ResultSet result = pst.executeQuery();
			while (result.next()) {
				System.out.println(result.getString(1));
				System.out.println("Tweet :" + result.getString("tweet"));
			}
		} catch (SQLException e1) {

			e1.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void viewAllTweet() {
		Connection con = null;
		Statement selectStmt = null;
		String query = "SELECT * FROM tweet.posttweet";

		try {
			con = JDBCconnection.getJdbcConnection();
			selectStmt = con.createStatement();
			ResultSet result = selectStmt.executeQuery(query);
			while (result.next()) {
				System.out.println("User Name : " + result.getString(1));
				System.out.println("Tweet :" + result.getString("tweet"));
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public boolean logout(String email) {
		Connection con = null;
		PreparedStatement preparedStatement = null;
		String query1 = "UPDATE tweet.registeruser SET status = FALSE where email =?";
		try {
			con = JDBCconnection.getJdbcConnection();
			preparedStatement = con.prepareStatement(query1);
			preparedStatement.setString(1, email);
			if (preparedStatement.executeUpdate() != 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

}
