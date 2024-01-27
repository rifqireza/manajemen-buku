package com.example.manajemenbuku.DAO;

import com.example.manajemenbuku.model.Admin;
import com.example.manajemenbuku.utility.JDBCConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO implements LoginInterface<Admin>{
    @Override
    public boolean login(Admin admin) {
        boolean result = false;
        try{
            String query = "SELECT * FROM admin WHERE username = ? and password = ?";
            PreparedStatement ps;
            ps = JDBCConnection.getConnection().prepareStatement(query);
            ps.setString(1, admin.getUsername());
            ps.setString(2, admin.getPassword());
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
    public int register(Admin admin) {
        int result = 0;
        try{
            String query = "INSERT INTO admin (nama, username, password) values(?, ?, ?);";
            PreparedStatement ps;
            ps = JDBCConnection.getConnection().prepareStatement(query);
            ps.setString(1, admin.getName());
            ps.setString(2, admin.getUsername());
            ps.setString(3, admin.getPassword());
            result = ps.executeUpdate();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }


}
