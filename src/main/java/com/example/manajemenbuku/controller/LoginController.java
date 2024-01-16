package com.example.manajemenbuku.controller;

import com.example.manajemenbuku.DAO.LoginDAO;
import com.example.manajemenbuku.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private TextField username;
    @FXML
    private Label unameError;
    @FXML
    private TextField password;
    @FXML
    private Label pwError;
    @FXML
    private TextField name;
    @FXML
    private Label nameError;
    @FXML
    private Text registerLabel;
    @FXML
    private Text loginLabel;

    @FXML
    public void login(ActionEvent ae) throws Exception {
        String nme = name.getText();
        nameError.setText(isNotEmptyField(nme, "Nama"));
        String uname = username.getText();
        unameError.setText(isNotEmptyField(uname, "Username"));
        String pw = password.getText();
        pwError.setText(isNotEmptyField(pw, "Password"));

        if (unameError.getText().isEmpty() && pwError.getText().isEmpty()) {
            LoginDAO loginDAO = new LoginDAO();
            if (nme.isEmpty()) {
                boolean isRegistered = loginDAO.login(uname, pw);
                if (isRegistered) {
                    Main.showStudentPage();
                }
                pwError.setText("Harap periksa kembali.");
            } else {
                loginDAO.register(nme, uname, pw);
                Main.showStudentPage();
            }
        }
        username.setText("");
        password.setText("");
    }

    private String isNotEmptyField(String value, String label) {
        if (value.isEmpty()) {
            return label + " harus diisi.";
        }

        return "";
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LoginDAO loginDAO = new LoginDAO();
        if (loginDAO.isExistAdmin()) {
            name.setVisible(false);
            registerLabel.setVisible(false);
        } else {
            loginLabel.setVisible(false);
        }
    }
}