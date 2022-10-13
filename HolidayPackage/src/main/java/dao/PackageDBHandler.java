package dao;

import java.io.*;
import java.sql.*;
import java.util.*;

public class PackageDBHandler {
	
	public static Connection connectToHolidayDB() {
		Connection conn = null;
		try {	
			FileInputStream fin =  new FileInputStream("C:\\Users\\ashar292\\Desktop\\Training\\Files\\JAVA\\Holiday_Package\\db.properties");
			
			Properties p = new Properties();
			p.load(fin);
			
			String driver = p.getProperty("driver");
			String url = p.getProperty("url");
			String userName = p.getProperty("username");
			String password = p.getProperty("password");
			
			//Establishing Connection
			Class.forName(driver);
			conn = DriverManager.getConnection(url,userName,password);
			
		}catch(Exception e){
			System.out.println(e);
		}
		return conn;
	}

}
