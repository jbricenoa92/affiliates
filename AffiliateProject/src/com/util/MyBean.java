package com.util;

import java.sql.Connection;
import com.config.DatabaseConnection;

public class MyBean {

	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Connection connectToDatabase() {
		Connection connection = null;
		try {
			connection = DatabaseConnection.getConnection();
			message = "conexion exitosa a la base de datos.";
		} catch (Exception e) {
			message = "Error: " + e.getMessage();
		}
		return connection;
	}

}
