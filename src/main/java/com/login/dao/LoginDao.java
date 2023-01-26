package com.login.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginDao {

	public static Connection getConnection() {
		Connection connection = null;
		String url = "jdbc:mysql://localhost:3306/demo";
		String userName = "root";
		String password = "root";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, userName, password);
		} catch (Exception e) {
			System.out.println(e);
		}
		return connection;
	}

	public static boolean validateUser(String userName, String password) {

		String query = "select * from login where userName=? AND userPassword=?";

		try {
			Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query);

			preparedStatement.setString(1, userName);
			preparedStatement.setString(2, password);

			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				return true;
			}
			connection.close();
		} catch (Exception ex) {
			ex.printStackTrace();

		}

		return false;

	}

}
