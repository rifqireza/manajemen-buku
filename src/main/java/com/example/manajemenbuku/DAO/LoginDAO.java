package com.example.manajemenbuku.DAO;

import com.example.manajemenbuku.utility.JDBCConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO implements LoginInterface{
    @Override
    public boolean login(String uname, String pw) {
        boolean result = false;
        try{
            String query = "SELECT * FROM admin WHERE username = ? and password = ?";
            PreparedStatement ps;
            ps = JDBCConnection.getConnection().prepareStatement(query);
            ps.setString(1, uname);
            ps.setString(2, pw);
            ResultSet rs = ps.executeQuery();
            result = rs.next();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return result;
    }

    @Override
    public boolean isExistAdmin() {
        boolean result = false;
        try{
            String query = "SELECT * FROM admin;";
            PreparedStatement ps;
            ps = JDBCConnection.getConnection().prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            result = rs.next();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return result;
    }

    @Override
    public int register(String name, String uname, String password) {
        int result = 0;
        try{
            String query = "INSERT INTO admin (nama, username, password) values(?, ?, ?);";
            PreparedStatement ps;
            ps = JDBCConnection.getConnection().prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, uname);
            ps.setString(3, password);
            result = ps.executeUpdate();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }


}
