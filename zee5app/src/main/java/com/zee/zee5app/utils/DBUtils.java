
package com.zee.zee5app.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.mysql.cj.exceptions.UnableToConnectException;
import com.zee.zee5app.exceptions.UnableToGenerateIdException;

public class DBUtils {
	

	private DBUtils() {

	}

	private static DBUtils dbUtils;

	public static DBUtils getInstance() {
		if (dbUtils == null) {
			dbUtils = new DBUtils();
		}
		return dbUtils;

	}
	
	public Connection getConnection() {
		// to provide the connection.
		Properties properties = loadProperties();
		
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(properties.getProperty("db.url"),
					properties.getProperty("db.username"), 
					properties.getProperty("db.password"));
			return connection;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public void closeConnection(Connection connection) {
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private Properties loadProperties() {
		
		Properties properties = new Properties();
		InputStream inputStream = null;
		try {
			inputStream = DBUtils.class.getClassLoader().getResourceAsStream("application.properties");
			System.out.println(inputStream!=null);
			properties.load(inputStream);
			return properties;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//properties.load();
		return properties;
		
	}
	
	public String userIdGenerator(String firstName,String lastName) throws UnableToGenerateIdException {
		
		/// it is responsible to generate the userid for user entity
		
		// 1st retrieve the value (db stored value from idgen table)
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int id = 0 ;
		String query = "select id from useridgenerator";
		String updateIdStatement = "update useridgenerator set id=?";
		int updateResult = 0;
		String newId = null;
		
		connection = this.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				id = resultSet.getInt(1);
				id = ++id;
			newId = firstName.charAt(0)+""+lastName.charAt(0)+""+id;
				//newId= id+""+firstName.charAt(0)+""+lastName.charAt(0);
				System.out.println(newId);
				preparedStatement = connection.prepareStatement(updateIdStatement);
				preparedStatement.setInt(1, id);
				updateResult = preparedStatement.executeUpdate();
				if(updateResult==0)
				{
					throw new UnableToGenerateIdException("unable to generate id");
				}
				
				return newId;
			}
			// take 1st char from firstName and lastName
			
			// then increemnt the number (id which is retreived from DB)
			// then cancate and return 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new UnableToGenerateIdException("unable to generate id"+e.getMessage());
		}
		finally {
			this.closeConnection(connection);
		}
		return null;
		
		
		
	}
//	public static void main(String[] args) throws UnableToGenerateIdException {
//		String result =DBUtils.getInstance().userIdGenerator("abhi", "chivate");
//		System.out.println(result);
//	}

}
