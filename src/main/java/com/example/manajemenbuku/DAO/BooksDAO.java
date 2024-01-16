package com.example.manajemenbuku.DAO;

import com.example.manajemenbuku.model.BookModel;
import com.example.manajemenbuku.utility.JDBCConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BooksDAO implements BooksInterface<BookModel> {
    @Override
    public int addData(BookModel data) {
        int result = 0;
        try {
            String query = "INSERT INTO buku (judul_buku, penulis_buku, penerbit_buku, tahun_terbit, stok) VALUES(?, ?, ?, ?, ?);";
            PreparedStatement ps;
            ps = JDBCConnection.getConnection().prepareStatement(query);
            ps.setString(1, data.getTitle().getValue());
            ps.setString(2, data.getAuthor().getValue());
            ps.setString(3, data.getPublisher().getValue());
            ps.setInt(4, Integer.parseInt(data.getPublishYear().getValue()));
            ps.setInt(5, Integer.parseInt(data.getStock().getValue()));

            result = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return result;
    }

    @Override
    public int delData(BookModel data) {
        int result = 0;
        try {
            String query = "delete from buku where id=?";
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
    public int updateData(BookModel data) {
        int result = 0;
        try {
            String query = "UPDATE buku SET judul_buku = ?, penulis_buku = ?, penerbit_buku = ?, tahun_terbit = ?, stok = ? WHERE id = ?;";
            PreparedStatement ps;
            ps = JDBCConnection.getConnection().prepareStatement(query);
            ps.setString(1, data.getTitle().getValue());
            ps.setString(2, data.getAuthor().getValue());
            ps.setString(3, data.getPublisher().getValue());
            ps.setInt(4, Integer.parseInt(data.getPublishYear().getValue()));
            ps.setInt(5, Integer.parseInt(data.getStock().getValue()));
            ps.setInt(6, Integer.parseInt(data.getId()));

            result = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return result;
    }

    @Override
    public ObservableList<BookModel> showData() {
        ObservableList<BookModel> bList = FXCollections.observableArrayList();
        try {
            String query = "select * from buku;";
            PreparedStatement ps;
            ps = JDBCConnection.getConnection().prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String id = rs.getString("id");
                String title = rs.getString("judul_buku");
                String author = rs.getString("penulis_buku");
                String publisher = rs.getString("penerbit_buku");
                int publishYear = rs.getInt("tahun_terbit");
                int stock = rs.getInt("stok");

                BookModel b = new BookModel(id, title, author, publisher, publishYear, stock);
                bList.add(b);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return bList;
    }

    @Override
    public BookModel getDetail(String id) {
        BookModel bookModel = null;
        try {
            String query = "select * from buku where id=?;";
            PreparedStatement ps;
            ps = JDBCConnection.getConnection().prepareStatement(query);
            ps.setInt(1, Integer.parseInt(id));
            ResultSet rs = ps.executeQuery();
            rs.next();
            String Id = rs.getString("id");
            String title = rs.getString("judul_buku");
            String author = rs.getString("penulis_buku");
            String publisher = rs.getString("penerbit_buku");
            int publishYear = rs.getInt("tahun_terbit");
            int stock = rs.getInt("stok");

            bookModel = new BookModel(Id, title, author, publisher, publishYear, stock);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return bookModel;
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