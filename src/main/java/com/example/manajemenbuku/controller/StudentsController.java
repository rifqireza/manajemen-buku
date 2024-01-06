package com.example.manajemenbuku.controller;

import com.example.manajemenbuku.DAO.BooksDAO;
import com.example.manajemenbuku.DAO.StudentDAO;
import com.example.manajemenbuku.Main;
import com.example.manajemenbuku.model.BookModel;
import com.example.manajemenbuku.model.StudentModel;
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
    private TreeView<String> sidebarTree;

    @FXML
    private void onClickAdd() throws Exception {
        Main.showAddStudentPage();
    }

    @FXML
    private void onClickDelete(ActionEvent actionEvent) {
        StudentModel studentModel;
        studentModel = (StudentModel) tableStudents.getSelectionModel().getSelectedItem();
        StudentDAO studentDAO = new StudentDAO();
        int result = studentDAO.delData(studentModel);
        if (result != 0) {
            System.out.println("Delete Berhasil");
        }
        ObservableList<StudentModel> listtudents = studentDAO.showData();
        tableStudents.setItems(listtudents);
    }

    @FXML
    private void onClickUpdate(ActionEvent actionEvent) throws Exception {
        StudentModel studentModel;
        studentModel = (StudentModel) tableStudents.getSelectionModel().getSelectedItem();

        FXMLLoader loader = new FXMLLoader(Main.class.getResource("AddStudent.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        StudentController studentController = loader.getController();
        studentController.getId(studentModel.getId());

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
        ObservableList<StudentModel> listStudents = studentDAO.showData();

        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        nimColumn.setCellValueFactory(cellData -> cellData.getValue().NIMProperty());
        prodiColumn.setCellValueFactory(cellData -> cellData.getValue().prodiProperty());
        noTelpColumn.setCellValueFactory(cellData -> cellData.getValue().noTelpProperty());
        tableStudents.setItems(listStudents);
    }
}
