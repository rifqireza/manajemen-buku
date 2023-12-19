package com.example.manajemenbuku.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    @FXML
    private TextField username;
    @FXML
    private String password;
    @FXML
    private Button submitButton;
    @FXML
    private BorderPane loginPage;
    private Parent root;
    private Stage stage;
    private Scene scene;
    @FXML
    protected void onClickForgotPassword() {
        loginPage.setVisible(false);
    }

    @FXML
    public void toBooksScene() throws IOException {
        root = FXMLLoader.load(getClass().getResource("ListBooks.fxml"));
        stage = (Stage)submitButton.getScene().getWindow();
        stage.close();
//        scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
    }
}