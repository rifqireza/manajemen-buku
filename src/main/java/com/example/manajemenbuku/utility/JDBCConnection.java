package com.example.manajemenbuku.utility;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBCConnection {
    public static Connection getConnection() {
        Connection conn;
        conn = DriverManager.getConnection()
        return conn;
    }
}
