package com.tweetapp.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import com.tweetapp.entity.RegisterEntity;
import com.tweetapp.service.RegisterService;
import com.tweetapp.util.JDBCconnection;

public class RegisterDAO {

	public boolean registerUser(RegisterEntity registerEntity)  {
        Connection con = null;
        PreparedStatement pst = null;
        String query = "insert into registeruser (`first_name`, `last_name`, `gender`, `date_of_birth`, `email`, `password`, `status`) values(?,?,?,?,?,?,?)";

        try {
            con= JDBCconnection.getJdbcConnection();
            pst = con.prepareStatement("CREATE TABLE IF NOT EXISTS tweet.registeruser (first_name varchar(255), last_name varchar(255), gender varchar(255), date_of_birth Date, email varchar(255), password varchar(255), status TINYINT) ");
            pst.execute();
            pst = con.prepareStatement(query);
            pst.setString(1, registerEntity.getFirst_name());
            pst.setString(2, registerEntity.getLast_name());
            pst.setString(3, registerEntity.getGender());
            pst.setDate(4, Date.valueOf(registerEntity.getDate_of_birth()));
            pst.setString(5, registerEntity.getEmail());
            pst.setString(6, registerEntity.getPassword());
            pst.setBoolean(7, false);
            int x = pst.executeUpdate();
            if(x>0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
        return false;
    }
	
		public boolean loginUser(String email , String password) {
			Connection con = null;
	        PreparedStatement pst = null;
	        String query = "select * from registeruser where email=? and password=?";
	        try {
	            con= JDBCconnection.getJdbcConnection();
	            pst = con.prepareStatement(query);
	            pst.setString(1, email);
	            pst.setString(2, password);
	            ResultSet result=pst.executeQuery();
	            if (result.next()) {
	            	String query1 = "UPDATE tweet.registeruser SET status = TRUE where email =?"; 
	            	PreparedStatement preparedStatement = con.prepareStatement(query1);
	            	preparedStatement.setString(1, email);
	            	if(preparedStatement.executeUpdate()!=0) {
	                return true;
	            	}
	            }
	        }
	        catch (SQLException e) {
			}
	        finally {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return false;
	}
		
	public boolean isExistingEmail(String email) {
		Connection con = null;
        PreparedStatement pst = null;
        String query = "select * from registeruser where email=?";
        
        try {
            con = JDBCconnection.getJdbcConnection();
            pst = con.prepareStatement("CREATE TABLE IF NOT EXISTS tweet.registeruser (first_name varchar(255), last_name varchar(255), gender varchar(255), date_of_birth Date, email varchar(255), password varchar(255), status TINYINT) ");
            pst.execute();
			pst = con.prepareStatement(query);
            pst.setString(1, email);
            ResultSet resultSet = pst.executeQuery();
            if(resultSet.next()) {
            	return false;
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return true;
	}

	public boolean forgotPassword(String fname, String lname,String email ,LocalDate dateS) {
		Connection con = null;
        PreparedStatement pst = null;
        String query = "select * from registeruser where first_name = ? and last_name = ? and email = ? and date_of_birth = ?";
        try {
            con = JDBCconnection.getJdbcConnection();
			pst = con.prepareStatement(query);
			pst.setString(1, fname);
			pst.setString(2, lname );
			pst.setString(3, email);
			pst.setDate(4, Date.valueOf(dateS));
			ResultSet resultSet = pst.executeQuery();
			if(resultSet.next()) {
					 try {
						RegisterService.resetPassword(email);
					} catch (IOException e) {
						e.printStackTrace();
					} 
			}else {
				return false;
			}
        }catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return true;
	}

	public boolean restPassword(String new_pass, String email) {
		Connection con = null;
		PreparedStatement preparedStatement = null;
		String query1 = "UPDATE tweet.registeruser SET password=? WHERE (email=?);"; 
		try {
            con= JDBCconnection.getJdbcConnection();
			preparedStatement = con.prepareStatement(query1);
			preparedStatement.setString(1, new_pass);
	    	preparedStatement.setString(2, email);
	        if(preparedStatement.executeUpdate()!=0) {
	        	return true;
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return false;
	}
}