package com.tubes.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Konektor {
    private static final String URL = "jdbc:mysql://localhost:3306/vendingmachine";
    private static final String USERNAME ="root";
    private static final String PASSWORD ="";

    public static Connection connection() throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        connection.setAutoCommit(false);
        return connection;
    }
}
