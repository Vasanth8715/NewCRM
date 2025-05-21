package com.comcast.HMS.generic.databaseUtility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class DataBaseUtility 
{
	Connection connection;
	public void getDbConnection() throws Throwable
	{
		try {
		Driver driver = new Driver();
		DriverManager.registerDriver(driver);
		
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projects", "root", "root");
		}catch (Exception e)
		{
			
		}	
	}
	
	public void closeDbConnection() throws Throwable
	{
		try {
			connection.close();
		} catch (Exception e) {
			
		}
	}
	
	public ResultSet executeSelectQuery(String query) throws Throwable
	{
		ResultSet  result = null;
		try {
		Statement state = connection.createStatement();
		result = state.executeQuery(query);
		} catch (Exception e)
		{
			
		}
		
		return result;
	}
	
	public int  executeNonSelectQuery(String query)
	{
		int result = 0;
		try 
		{
			Statement state = connection.createStatement();
			result = state.executeUpdate(query);
			} catch (Exception e)
			{
				
			}
			
			return result;
	}
}
