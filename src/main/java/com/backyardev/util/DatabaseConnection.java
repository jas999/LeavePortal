package com.backyardev.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {

    private static DatabaseConnection instance;
    private Connection connection;
    private String url = "";
    private String username = "";
    private String password = "";
    private String className = "";

    private DatabaseConnection() throws SQLException {
        try {
        	Properties props = Props.getProps();
        	url = props.getProperty("SQL_URL");
        	username =  props.getProperty("SQL_USER");
        	password = props.getProperty("SQL_PASS");
        	className = props.getProperty("SQL_CLASS_NAME");
            Class.forName(className);
            this.connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException ex) {
            System.out.println("Database Connection Creation Failed : " + ex.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static DatabaseConnection getInstance() throws SQLException {
        if (instance == null) {
            instance = new DatabaseConnection();
        } else if (instance.getConnection().isClosed()) {
            instance = new DatabaseConnection();
        }

        return instance;
    }
}