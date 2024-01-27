package com.example.manajemenbuku.DAO;

import com.example.manajemenbuku.model.Buku;
import com.example.manajemenbuku.utility.JDBCConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BooksDAO implements BooksInterface<Buku> {
    @Override
    public int addData(Buku data) {
        int result = 0;
        try {
            String query = "INSERT INTO buku (judul_buku, penulis_buku, penerbit_buku, tahun_terbit, stok) VALUES(?, ?, ?, ?, ?);";
            PreparedStatement ps;
            ps = JDBCConnection.getConnection().prepareStatement(query);
            ps.setString(1, data.getTitle());
            ps.setString(2, data.getAuthor());
            ps.setString(3, data.getPublisher());
            ps.setInt(4, data.getPublishYear());
            ps.setInt(5, data.getStock());

            result = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return result;
    }

    @Override
    public int delData(Buku data) {
        int result = 0;
        try {
            String query = "delete from buku where id=?";
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
    public int updateData(Buku data) {
        int result = 0;
        try {
            String query = "UPDATE buku SET judul_buku = ?, penulis_buku = ?, penerbit_buku = ?, tahun_terbit = ?, stok = ? WHERE id = ?;";
            PreparedStatement ps;
            ps = JDBCConnection.getConnection().prepareStatement(query);
            ps.setString(1, data.getTitle());
            ps.setString(2, data.getAuthor());
            ps.setString(3, data.getPublisher());
            ps.setInt(4, data.getPublishYear());
            ps.setInt(5, data.getStock());
            ps.setInt(6, data.getId());

            result = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return result;
    }

    @Override
    public ObservableList<Buku> showData() {
        ObservableList<Buku> bList = FXCollections.observableArrayList();
        try {
            String query = "select * from buku;";
            PreparedStatement ps;
            ps = JDBCConnection.getConnection().prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("judul_buku");
                String author = rs.getString("penulis_buku");
                String publisher = rs.getString("penerbit_buku");
                int publishYear = rs.getInt("tahun_terbit");
                int stock = rs.getInt("stok");

                Buku b = new Buku(id, title, author, publisher, publishYear, stock);
                bList.add(b);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return bList;
    }

    @Override
    public Buku getDetail(int id) {
        Buku buku = null;
        try {
            String query = "select * from buku where id=?;";
            PreparedStatement ps;
            ps = JDBCConnection.getConnection().prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            int Id = rs.getInt("id");
            String title = rs.getString("judul_buku");
            String author = rs.getString("penulis_buku");
            String publisher = rs.getString("penerbit_buku");
            int publishYear = rs.getInt("tahun_terbit");
            int stock = rs.getInt("stok");

            buku = new Buku(Id, title, author, publisher, publishYear, stock);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return buku;
    }

    @Override
    public int countCart() {
        int result = 0;
        try {
            String query = "SELECT COUNT(distinct id_buku) AS total FROM keranjang;";
            PreparedStatement ps;
            ps = JDBCConnection.getConnection().prepareStatement(query);

            ResultSet rs = ps.executeQuery();
            rs.next();
            System.out.println(rs.getInt("total"));
            result = rs.getInt("total");
        } catch (SQLException E) {
            System.out.println(E.getMessage());
        }
        return result;
    }
}