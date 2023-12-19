package com.example.manajemenbuku.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneController {
    private Parent root;
    private Stage stage;
    private Scene scene;

    public void toLoginScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("LoginMenu.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
//        stage.setTitle("Manajemen Buku");
        stage.setScene(scene);
        stage.show();
    }
}
