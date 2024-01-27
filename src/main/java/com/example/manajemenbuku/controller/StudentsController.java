package com.example.manajemenbuku.controller;

import com.example.manajemenbuku.DAO.StudentDAO;
import com.example.manajemenbuku.Main;
import com.example.manajemenbuku.model.Anggota;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeView;

import java.net.URL;
import java.util.ResourceBundle;

public class StudentsController implements Initializable {
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
    private TreeView<String> sidebarTree;

    @FXML
    private void onClickAdd() throws Exception {
        Main.showAddStudentPage();
    }

    @FXML
    private void onClickDelete(ActionEvent actionEvent) {
        Anggota anggota;
        anggota = (Anggota) tableStudents.getSelectionModel().getSelectedItem();
        StudentDAO studentDAO = new StudentDAO();
        int result = studentDAO.delData(anggota);
        if (result != 0) {
            System.out.println("Delete Berhasil");
        }
        ObservableList<Anggota> listtudents = studentDAO.showData();
        tableStudents.setItems(listtudents);
    }

    @FXML
    private void onClickUpdate(ActionEvent actionEvent) throws Exception {
        Anggota anggota;
        anggota = (Anggota) tableStudents.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("AddStudent.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        StudentController studentController = loader.getController();
        studentController.getId(anggota.getId());

        Main.showUpdateBookPage(scene);
    }

    @FXML
    private void onClickLogout(ActionEvent actionEvent) throws Exception {
        Main.showLoginPage();
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        SidebarController sidebarController = new SidebarController();
        sidebarController.initSidebarItem(sidebarTree);
        StudentDAO studentDAO = new StudentDAO();
        ObservableList<Anggota> listStudents = studentDAO.showData();

        nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        nimColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNIM()));
        prodiColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getProdi()));
        noTelpColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNoTelp()));
        tableStudents.setItems(listStudents);
    }
}
