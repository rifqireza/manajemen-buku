package com.example.manajemenbuku.DAO;

import com.example.manajemenbuku.model.StudentModel;
import com.example.manajemenbuku.utility.JDBCConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentDAO implements StudentInterface<StudentModel> {
    @Override
    public int addData(StudentModel data) {
        int result = 0;
        try {
            String query = "INSERT INTO peminjam (nim, nama, prodi, no_telp) VALUES( ?, ?, ?, ?);";
            PreparedStatement ps;
            ps = JDBCConnection.getConnection().prepareStatement(query);
            ps.setString(1, data.getNIM());
            ps.setString(2, data.getName());
            ps.setString(3, data.getProdi());
            ps.setString(4, data.getNoTelp());

            result = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return result;
    }

    @Override
    public int delData(StudentModel data) {
        int result = 0;
        try {
            String query = "delete from peminjam where id=?";
            PreparedStatement ps;
            ps = JDBCConnection.getConnection().prepareStatement(query);
            ps.setInt(1, Integer.parseInt(data.getId()));

            result = ps.executeUpdate();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public int updateData(StudentModel data) {
        int result = 0;
        try {
            String query = "UPDATE peminjam SET nim = ?, nama = ?, prodi = ?, no_telp = ? WHERE id = ?;";
            PreparedStatement ps;
            ps = JDBCConnection.getConnection().prepareStatement(query);
            ps.setString(1, data.getNIM());
            ps.setString(2, data.getName());
            ps.setString(3, data.getProdi());
            ps.setString(4, data.getNoTelp());
            ps.setInt(5, Integer.parseInt(data.getId()));

            result = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return result;
    }

    @Override
    public ObservableList<StudentModel> showData() {
        ObservableList<StudentModel> sList = FXCollections.observableArrayList();
        try {
            String query = "select * from peminjam;";
            PreparedStatement ps;
            ps = JDBCConnection.getConnection().prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String id = rs.getString("id");
                String nim = rs.getString("nim");
                String name = rs.getString("nama");
                String prodi = rs.getString("prodi");
                String noTelp = rs.getString("no_telp");

                StudentModel s = new StudentModel(id, nim, name, prodi, noTelp);
                sList.add(s);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return sList;
    }

    @Override
    public StudentModel getDetail(String id) {
        StudentModel studentModel = null;
        try {
            String query = "select * from peminjam where id=?;";
            PreparedStatement ps;
            ps = JDBCConnection.getConnection().prepareStatement(query);
            ps.setInt(1, Integer.parseInt(id));
            ResultSet rs = ps.executeQuery();
            rs.next();
            String Id = rs.getString("id");
            String nim = rs.getString("nim");
            String name = rs.getString("nama");
            String prodi = rs.getString("prodi");
            String noTelp = rs.getString("no_telp");

            studentModel = new StudentModel(Id, nim, name, prodi, noTelp);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return studentModel;
    }
}
