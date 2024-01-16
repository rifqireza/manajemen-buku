package com.example.manajemenbuku.DAO;

import com.example.manajemenbuku.model.BookModel;
import com.example.manajemenbuku.utility.JDBCConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CartDAO implements CartInterface<BookModel> {
    @Override
    public ObservableList<BookModel> getCartBook() {
        ObservableList<BookModel> bList = FXCollections.observableArrayList();
        try {
            String query = "select id_buku, judul_buku, penulis_buku, penerbit_buku, tahun_terbit, count(*) as total from keranjang join buku on (keranjang.id_buku = buku.id) group by buku.id;";
            PreparedStatement ps;
            ps = JDBCConnection.getConnection().prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String id = rs.getString("id_buku");
                String title = rs.getString("judul_buku");
                String author = rs.getString("penulis_buku");
                String publisher = rs.getString("penerbit_buku");
                int publishYear = rs.getInt("tahun_terbit");
                int stock = rs.getInt("total");

                BookModel b = new BookModel(id, title, author, publisher, publishYear, stock);
                bList.add(b);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return bList;
    }

    @Override
    public int delData(BookModel bookModel) {
        int result = 0;
        try {
            Connection conn = JDBCConnection.getConnection();
            try {
                conn.setAutoCommit(false);
                String query1 = "delete from keranjang where id_buku=?";
                PreparedStatement ps1;
                ps1 = conn.prepareStatement(query1);
                ps1.setInt(1, Integer.parseInt(bookModel.getId()));
                result = ps1.executeUpdate();

                String query2 = "update buku set stok = stok + ? where id = ?;";
                PreparedStatement ps2;
                ps2 = conn.prepareStatement(query2);
                ps2.setInt(1, Integer.parseInt(bookModel.getStock().getValue()));
                ps2.setInt(2, Integer.parseInt(bookModel.getId()));
                result = ps2.executeUpdate();
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                System.out.println(e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public int addCart(int idBuku) {
        int result = 0;
        try {
            Connection conn = JDBCConnection.getConnection();
            try {
                conn.setAutoCommit(false);
                String query1 = "insert into keranjang (id_buku) values (?);";
                PreparedStatement ps1;
                ps1 = conn.prepareStatement(query1);
                ps1.setInt(1, idBuku);
                result = ps1.executeUpdate();

                String query2 = "update buku set stok = stok - 1 where id = ? and stok > 0;";
                PreparedStatement ps2;
                ps2 = conn.prepareStatement(query2);
                ps2.setInt(1, idBuku);
                result = ps2.executeUpdate();
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                System.out.println(e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public int getCountById(int idBuku) {
        int result = 0;

            try {
                String query = "select count(*) as total from keranjang where id_buku = ?;";
                PreparedStatement ps = JDBCConnection.getConnection().prepareStatement(query);
                ps.setInt(1, idBuku);
                ResultSet rs = ps.executeQuery();
                rs.next();
                result = rs.getInt("total");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        return result;
    }

    @Override
    public int reduceCart(int idBuku) {
        int result = 0;
        try {
            Connection conn = JDBCConnection.getConnection();
            try {
                conn.setAutoCommit(false);

                String query = "delete from keranjang where id_buku = ? order by id limit 1;";
                PreparedStatement ps = JDBCConnection.getConnection().prepareStatement(query);
                ps.setInt(1, idBuku);
                result = ps.executeUpdate();


                String query2 = "update buku set stok = stok + 1 where id = ?;";
                PreparedStatement ps2;
                ps2 = conn.prepareStatement(query2);
                ps2.setInt(1, idBuku);
                result = ps2.executeUpdate();

                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                System.out.println(e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return result;
    }
}
