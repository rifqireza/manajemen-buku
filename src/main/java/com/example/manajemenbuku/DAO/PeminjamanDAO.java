package com.example.manajemenbuku.DAO;

import com.example.manajemenbuku.model.Buku;
import com.example.manajemenbuku.model.DetailPeminjaman;
import com.example.manajemenbuku.model.Peminjaman;
import com.example.manajemenbuku.utility.JDBCConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class PeminjamanDAO implements PeminjamanInterface<Peminjaman> {
    @Override
    public int addPeminjaman(Peminjaman data, ObservableList<Buku> bukus) {
        int result = 1;
        try {
            Connection conn = JDBCConnection.getConnection();
            try {
                conn.setAutoCommit(false);
                String query1 = "insert into peminjaman (tanggal_pinjam, tanggal_kembali, denda, id_anggota) values(?, ?, ?, ?);";
                PreparedStatement ps1;
                ps1 = conn.prepareStatement(query1, PreparedStatement.RETURN_GENERATED_KEYS);
                ps1.setString(1, data.getTglPeminjaman());
                ps1.setString(2, data.getTglPengembalian());
                ps1.setInt(3, Integer.parseInt(data.getDenda()));
                ps1.setInt(4, Integer.parseInt(data.getStudent()));
                result = ps1.executeUpdate();
                if (result > 0) {
                    ResultSet generatedKeys = ps1.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        int generatedId = generatedKeys.getInt(1);
                        for (Buku bm : bukus) {
                            String query2 = "insert into detail_peminjaman values(?, ?, ?);";
                            PreparedStatement ps2;
                            ps2 = conn.prepareStatement(query2);
                            ps2.setInt(1, bm.getId());
                            ps2.setInt(2, generatedId);
                            ps2.setInt(3, bm.getStock());
                            result = ps2.executeUpdate();
                            if (result > 0) {
                                String query3 = "delete from keranjang;";
                                PreparedStatement ps3;
                                ps3 = conn.prepareStatement(query3);
                                result = ps3.executeUpdate();
                            } else {
                                System.err.println("Gagal memasukkan data.");
                            }
                        }
                    } else {
                        System.err.println("Gagal mendapatkan ID");
                    }
                } else {
                    System.err.println("Gagal memasukkan data.");
                }
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
    public ObservableList<Peminjaman> showPeminjaman(boolean selesai) {
        ObservableList<Peminjaman> pList = FXCollections.observableArrayList();
        try {
            String query = "select * from peminjaman join peminjam on(peminjam.id = peminjaman.id_anggota) where selesai = ?;";
            PreparedStatement ps;
            ps = JDBCConnection.getConnection().prepareStatement(query);
            ps.setBoolean(1, selesai);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String tanggalPinjam = rs.getString("tanggal_pinjam");
                String tanggalKembali = rs.getString("tanggal_kembali");
                String denda = rs.getString("denda");
                String anggota = rs.getString("nama");

                Peminjaman p = new Peminjaman(id, tanggalPinjam, tanggalKembali, denda, anggota);
                pList.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return pList;
    }

    @Override
    public DetailPeminjaman getDetailPeminjaman(int id) {
        DetailPeminjaman detailPeminjaman = null;
        try {
            String query = "select * from peminjaman join peminjam on(peminjaman.id_anggota = peminjam.id) where peminjaman.id=?;";
            PreparedStatement ps;
            ps = JDBCConnection.getConnection().prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            int Id = rs.getInt("id");
            String tanggalPinjam = rs.getString("tanggal_pinjam");
            String tanggalKembali = rs.getString("tanggal_kembali");
            String denda = rs.getString("denda");
            String anggota = rs.getString("nama");

            String dendaTerlambat = "0";

            LocalDateTime currentDateTime = LocalDateTime.now(ZoneId.of("UTC"));
            System.out.println("currentTime "+currentDateTime);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            if (currentDateTime.isAfter(LocalDateTime.parse(tanggalKembali, formatter))) {
                dendaTerlambat = denda;
            }
            Peminjaman p = new Peminjaman(id, tanggalPinjam, tanggalKembali, dendaTerlambat, anggota);

            ObservableList<Buku> bukus = FXCollections.observableArrayList();
            String query2 = "select buku.id as id, judul_buku, penulis_buku, penerbit_buku, " +
                    "tahun_terbit, stock from buku join detail_peminjaman on(detail_peminjaman.id_buku " +
                    "= buku.id) left join peminjaman on(detail_peminjaman.id_peminjaman = peminjaman.id) " +
                    "where peminjaman.id = ?;";

            PreparedStatement ps2;
            ps2 = JDBCConnection.getConnection().prepareStatement(query2);
            ps2.setInt(1, id);
            ResultSet rs2 = ps2.executeQuery();
            while (rs2.next()) {
                int idBuku = rs2.getInt("id");
                String title = rs2.getString("judul_buku");
                String author = rs2.getString("penulis_buku");
                String publisher = rs2.getString("penerbit_buku");
                int publishYear = rs2.getInt("tahun_terbit");
                int stock = rs2.getInt("stock");

                Buku b = new Buku(idBuku, title, author, publisher, publishYear, stock);
                bukus.add(b);
            }
            detailPeminjaman = new DetailPeminjaman(p, bukus);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(detailPeminjaman);
        return detailPeminjaman;
    }

    @Override
    public int completeTheLoan(int id, ObservableList<Buku> bukus) {
        int result = 0;
        try {
            Connection conn = JDBCConnection.getConnection();
            try {
                conn.setAutoCommit(false);
                String query2 = "update peminjaman set selesai = true where id =?;";
                PreparedStatement ps2;
                ps2 = JDBCConnection.getConnection().prepareStatement(query2);
                ps2.setInt(1, id);
                result = ps2.executeUpdate();

                String query = "update buku set stok = stok + ? where id = ?;";
                for (Buku bm: bukus) {
                    PreparedStatement ps;
                    ps = conn.prepareStatement(query);
                    ps.setInt(1, bm.getStock());
                    ps.setInt(2, bm.getId());
                    ps.executeUpdate();
                }

                String query1 = "delete from detail_peminjaman where id_peminjaman=?;";
                PreparedStatement ps1;
                ps1 = conn.prepareStatement(query1);
                ps1.setInt(1, id);
                ps1.executeUpdate();

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
    public int deletePengembalian(int id) {
        int result = 0;
        try {
            String query2 = "delete from peminjaman where id=?;";
            PreparedStatement ps2;
            ps2 = JDBCConnection.getConnection().prepareStatement(query2);
            ps2.setInt(1, id);
            result = ps2.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }
}
