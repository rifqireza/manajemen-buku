package com.example.manajemenbuku.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnection {
    public static Connection getConnection() {
        Connection conn = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/db_perpustakaan?serverTimezone=Asia/Jakarta",
                    "root",
                    "reza53//"
            );
        } catch (ClassNotFoundException | SQLException e){
            System.out.println(e.getMessage());
        }

        return conn;
    }
}
