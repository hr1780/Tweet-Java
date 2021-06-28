package com.tweetapp.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCconnection {

    public static Connection getJdbcConnection() {
    	Properties props = new Properties();
    	try {
			FileInputStream in = new FileInputStream("C:\\Users\\User\\eclipse-workspace\\com.tweetapp\\src\\main\\resources\\db.properties");
			props.load(in);
			in.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        Connection con = null;
        try {
            Class.forName(props.getProperty("jdbc.driver"));
            String url = props.getProperty("jdbc.url");
            String username = props.getProperty("jdbc.username");
            String password = props.getProperty("jdbc.password");
            con = DriverManager.getConnection(url,username,password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

}