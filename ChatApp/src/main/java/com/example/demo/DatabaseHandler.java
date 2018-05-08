package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

public class DatabaseHandler {

	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	public void Connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/mymessenger?" + "user=Bobby&password=darkoqnko");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void InsertUser(String Username, String Password) {
		try {
			statement = connect.createStatement();
			preparedStatement = connect.prepareStatement("Insert Into user (Username,Password) Value (?,?)");
			preparedStatement.setString(1, Username);
			preparedStatement.setString(2, Password);
			preparedStatement.executeUpdate();
		} catch (Exception e) {
		}
	}

	public List<String> AllUsers() {
		List<String> Users = new ArrayList<>();
		try {
			statement = connect.createStatement();
			preparedStatement = connect.prepareStatement("Select Username From user");
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Users.add(resultSet.getString(1));

			}
		} catch (Exception e) {
		}
		return Users;
	}
	
	public boolean Authenticate(String username, String password) {
		boolean isAuthenticated = false;
		try {
			statement = connect.createStatement();
			preparedStatement = connect.prepareStatement("Select * from user where Username = ? and Password = ?");
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next())
			{
				isAuthenticated=true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isAuthenticated;
	}
	public void send(String message) {
	
			try {
				statement = connect.createStatement();
				preparedStatement = connect.prepareStatement("Insert Into message (message) Value (?)");
				preparedStatement.setString(1, message);
				preparedStatement.executeUpdate();
			} catch (Exception e) {
			}
		
	} 
	
	public List<String> recieve() {
		List<String> Messages = new ArrayList<>();
		try {
			statement = connect.createStatement();
			preparedStatement = connect.prepareStatement("Select message from message");
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Messages.add(resultSet.getString(1));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Messages;
	
	}
	
}

