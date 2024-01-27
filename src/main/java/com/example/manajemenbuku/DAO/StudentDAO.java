package com.example.manajemenbuku.DAO;

import com.example.manajemenbuku.model.Anggota;
import com.example.manajemenbuku.utility.JDBCConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentDAO implements StudentInterface<Anggota> {
    @Override
    public int addData(Anggota data) {
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
    public int delData(Anggota data) {
        int result = 0;
        try {
            String query = "delete from peminjam where id=?";
            PreparedStatement ps;
            ps = JDBCConnection.getConnection().prepareStatement(query);
            ps.setInt(1, data.getId());

            result = ps.executeUpdate();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public int updateData(Anggota data) {
        int result = 0;
        try {
            String query = "UPDATE peminjam SET nim = ?, nama = ?, prodi = ?, no_telp = ? WHERE id = ?;";
            PreparedStatement ps;
            ps = JDBCConnection.getConnection().prepareStatement(query);
            ps.setString(1, data.getNIM());
            ps.setString(2, data.getName());
            ps.setString(3, data.getProdi());
            ps.setString(4, data.getNoTelp());
            ps.setInt(5, data.getId());

            result = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return result;
    }

    @Override
    public ObservableList<Anggota> showData() {
        ObservableList<Anggota> sList = FXCollections.observableArrayList();
        try {
            String query = "select * from peminjam;";
            PreparedStatement ps;
            ps = JDBCConnection.getConnection().prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String nim = rs.getString("nim");
                String name = rs.getString("nama");
                String prodi = rs.getString("prodi");
                String noTelp = rs.getString("no_telp");

                Anggota s = new Anggota(id, name, nim, prodi, noTelp);
                sList.add(s);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return sList;
    }

    @Override
    public Anggota getDetail(int id) {
        Anggota anggota = null;
        try {
            String query = "select * from peminjam where id=?;";
            PreparedStatement ps;
            ps = JDBCConnection.getConnection().prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            int Id = rs.getInt("id");
            String nim = rs.getString("nim");
            String name = rs.getString("nama");
            String prodi = rs.getString("prodi");
            String noTelp = rs.getString("no_telp");
            anggota = new Anggota(Id, nim, name, prodi, noTelp);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return anggota;
    }
}
