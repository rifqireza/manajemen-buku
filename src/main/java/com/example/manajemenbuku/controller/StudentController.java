package com.example.manajemenbuku.controller;

import com.example.manajemenbuku.DAO.StudentDAO;
import com.example.manajemenbuku.Main;
import com.example.manajemenbuku.model.Anggota;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.UUID;

public class StudentController {
    @FXML
    private TextField NIM;
    @FXML
    private Label errorNIM;
    @FXML
    private TextField name;
    @FXML
    private Label errorName;
    @FXML
    private TextField prodi;
    @FXML
    private Label errorProdi;
    @FXML
    private TextField noTelp;
    @FXML
    private Label errorNoTelp;
    @FXML
    private Button saveButton;
    @FXML
    private Button updateButton;

    private int Id;

    public void getId(int id) {
        this.Id = id;
        StudentDAO studentDAO = new StudentDAO();
        Anggota anggota = studentDAO.getDetail(id);
        if (anggota.getId() > 0) {
            saveButton.setVisible(false);
            updateButton.setVisible(true);
            NIM.setText(anggota.getNIM());
            name.setText(anggota.getName());
            prodi.setText(anggota.getProdi());
            noTelp.setText(anggota.getNoTelp());
        }
    }

    @FXML
    private void onClickUpdate() throws Exception {
        String nimText = NIM.getText();
        String nameText = name.getText();
        String prodiText = prodi.getText();
        String noTelpText = noTelp.getText();
        if (isValidField(nimText, nameText, prodiText, noTelpText)) {
            Anggota anggota = new Anggota(
                    this.Id,
                    nimText,
                    nameText,
                    prodiText,
                    noTelpText
            );

            StudentDAO studentDAO = new StudentDAO();
            studentDAO.updateData(anggota);
            Main.showStudentPage();
        }
    }

    @FXML
    private void onClickCancel() throws Exception {
        Main.showStudentPage();
    }

    @FXML
    private void onClickSave() throws Exception {
        String nimText = NIM.getText();
        String nameText = name.getText();
        String prodiText = prodi.getText();
        String noTelpText = noTelp.getText();
        if (isValidField(nimText, nameText, prodiText, noTelpText)) {
            Anggota anggota = new Anggota(
                    1,
                    nameText,
                    nimText,
                    prodiText,
                    noTelpText
            );

            StudentDAO studentDAO = new StudentDAO();
            studentDAO.addData(anggota);
            Main.showStudentPage();
        }

    }

    private boolean isValidField(String nimText, String nameText, String prodiText, String noTelpText) {
        errorNIM.setText(isNotEmptyField(nimText, "Judul buku"));
        errorName.setText(isNotEmptyField(nameText, "Penulis buku"));
        errorProdi.setText(isNotEmptyField(prodiText, "Penerbit buku"));
        errorNoTelp.setText(isNotEmptyField(noTelpText, "Tahun terbit"));

        return errorNIM.getText().isEmpty()
                && errorName.getText().isEmpty()
                && errorProdi.getText().isEmpty()
                && errorNoTelp.getText().isEmpty();
    }

    private String isNotEmptyField(String value, String label) {
        if (value.isEmpty()) {
            return label + " harus diisi.";
        }

        return "";
    }
}
