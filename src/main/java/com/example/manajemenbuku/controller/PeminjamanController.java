package com.example.manajemenbuku.controller;

import com.example.manajemenbuku.DAO.CartDAO;
import com.example.manajemenbuku.DAO.PeminjamanDAO;
import com.example.manajemenbuku.DAO.StudentDAO;
import com.example.manajemenbuku.Main;
import com.example.manajemenbuku.model.BookModel;
import com.example.manajemenbuku.model.PeminjamanModel;
import com.example.manajemenbuku.model.StudentModel;
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
    private TableView<StudentModel> tableStudents;
    @FXML
    private TableColumn<StudentModel, String> nimColumn;
    @FXML
    private TableColumn<StudentModel, String> nameColumn;
    @FXML
    private TableColumn<StudentModel, String> prodiColumn;
    @FXML
    private TableColumn<StudentModel, String> noTelpColumn;
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

        StudentModel studentModel = null;
        if (tableStudents.getSelectionModel().getSelectedItem() != null) {
            studentModel = (StudentModel) tableStudents.getSelectionModel().getSelectedItem();
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
            PeminjamanModel peminjamanModel = new PeminjamanModel(
                        1,
                    tglPeminjaman,
                    tglPengembalian,
                    dendaText,
                    studentModel.getId()
            );

            CartDAO cartDAO = new CartDAO();
            ObservableList<BookModel> listBooks = cartDAO.getCartBook();

            PeminjamanDAO peminjamanDAO = new PeminjamanDAO();
            peminjamanDAO.addPeminjaman(peminjamanModel, listBooks);
            Main.showListBooksPage();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        StudentDAO studentDAO = new StudentDAO();
        ObservableList<StudentModel> listStudents = studentDAO.showData();

        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        nimColumn.setCellValueFactory(cellData -> cellData.getValue().NIMProperty());
        prodiColumn.setCellValueFactory(cellData -> cellData.getValue().prodiProperty());
        noTelpColumn.setCellValueFactory(cellData -> cellData.getValue().noTelpProperty());
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
