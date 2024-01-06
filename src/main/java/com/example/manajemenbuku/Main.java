package com.example.manajemenbuku;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private static Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Main.primaryStage = primaryStage;
        showStudentPage();
    }

    public static void showDashboard() throws Exception {
        Parent root = FXMLLoader.load(Main.class.getResource("Dashboard.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Dashboard");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void showStudentPage() throws Exception {
        Parent root = FXMLLoader.load(Main.class.getResource("ListStudent.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("List Students");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void showAddStudentPage() throws Exception {
        Parent root = FXMLLoader.load(Main.class.getResource("AddStudent.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Add Student");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void showLoginPage() throws Exception {
        Parent root = FXMLLoader.load(Main.class.getResource("LoginMenu.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Login Page");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void showListBooksPage() throws Exception {
        Parent root = FXMLLoader.load(Main.class.getResource("ListBooks.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("List Books");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void showAddBookPage() throws Exception {
        Parent root = FXMLLoader.load(Main.class.getResource("AddBook.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Add Book");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void showUpdateBookPage(Scene scene) throws Exception {
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}