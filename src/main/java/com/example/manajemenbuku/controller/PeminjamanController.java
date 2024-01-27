package com.example.manajemenbuku.controller;

import com.example.manajemenbuku.DAO.CartDAO;
import com.example.manajemenbuku.DAO.PeminjamanDAO;
import com.example.manajemenbuku.DAO.StudentDAO;
import com.example.manajemenbuku.Main;
import com.example.manajemenbuku.model.Buku;
import com.example.manajemenbuku.model.Peminjaman;
import com.example.manajemenbuku.model.Anggota;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class PeminjamanController implements Initializable {
    @FXML
    private TableView<Anggota> tableStudents;
    @FXML
    private TableColumn<Anggota, String> nimColumn;
    @FXML
    private TableColumn<Anggota, String> nameColumn;
    @FXML
    private TableColumn<Anggota, String> prodiColumn;
    @FXML
    private TableColumn<Anggota, String> noTelpColumn;
    @FXML
    private DatePicker tanggalPeminjaman;
    @FXML
    private DatePicker tanggalPengembalian;
    @FXML
    private TextField denda;
    @FXML
    private Label errorPeminjaman;
    @FXML
    private Label errorPengembalian;
    @FXML
    private Label errorDenda;
    @FXML
    private Label errorAnggota;

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    @FXML
    private void onClickCancel() throws Exception {
        Main.showCartPage();
    }

    @FXML
    private void onClickSave() throws Exception {
        String tglPeminjaman = "";
        if (tanggalPeminjaman.getValue() != null) {
            tglPeminjaman = tanggalPeminjaman.getValue().toString();
        }
        errorPeminjaman.setText(isNotEmptyField(tglPeminjaman, "Tanggal Peminjaman"));

        String tglPengembalian = "";
        if (tanggalPengembalian.getValue() != null) {
            tglPengembalian = tanggalPengembalian.getValue().toString();
        }
        errorPengembalian.setText(isNotEmptyField(tglPengembalian, "Tanggal Pengembalian"));

        String dendaText = denda.getText();
        errorDenda.setText(isNotEmptyField(dendaText, "Denda"));

        Anggota anggota = null;
        if (tableStudents.getSelectionModel().getSelectedItem() != null) {
            anggota = (Anggota) tableStudents.getSelectionModel().getSelectedItem();
            errorAnggota.setText("");
        } else {
            errorAnggota.setText(isNotEmptyField("", "Peminjam"));
        }

        if (stringToDate(tglPeminjaman).isAfter(stringToDate(tglPengembalian))) {
            errorPengembalian.setText("Tanggal Pengembalian harus lebih dari tanggal pinjam");
        }

        if (
                errorPeminjaman.getText().isEmpty()
                && errorDenda.getText().isEmpty()
                && errorAnggota.getText().isEmpty()
                && errorPengembalian.getText().isEmpty()
        ) {
            Peminjaman peminjaman = new Peminjaman(
                        1,
                    tglPeminjaman,
                    tglPengembalian,
                    dendaText,
                    String.valueOf(anggota.getId())
            );

            CartDAO cartDAO = new CartDAO();
            ObservableList<Buku> listBukus = cartDAO.getCartBook();

            PeminjamanDAO peminjamanDAO = new PeminjamanDAO();
            peminjamanDAO.addPeminjaman(peminjaman, listBukus);
            Main.showListBooksPage();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        StudentDAO studentDAO = new StudentDAO();
        ObservableList<Anggota> listStudents = studentDAO.showData();

        nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        nimColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNIM()));
        prodiColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getProdi()));
        noTelpColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNoTelp()));
        tableStudents.setItems(listStudents);
    }

    private LocalDateTime stringToDate(String dateString) {
        String timeString = "00:00:00";
        String dateTimeString = dateString + " " + timeString;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse(dateTimeString, formatter);

        return localDateTime;
    }

    private String isNotEmptyField(String value, String label) {
        if (value.isEmpty()) {
            return label + " harus diisi.";
        }

        return "";
    }
}
