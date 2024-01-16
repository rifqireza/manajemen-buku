package com.example.manajemenbuku.controller;

import com.example.manajemenbuku.DAO.PeminjamanDAO;
import com.example.manajemenbuku.Main;
import com.example.manajemenbuku.model.PeminjamanModel;
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

public class ListPinjamController implements Initializable {
    @FXML
    private TableView<PeminjamanModel> tablePeminjaman;
    @FXML
    private TableColumn<PeminjamanModel, String> tglPinjamColumn;
    @FXML
    private TableColumn<PeminjamanModel, String> tglKembaliColumn;
    @FXML
    private TableColumn<PeminjamanModel, String> dendaColumn;
    @FXML
    private TableColumn<PeminjamanModel, String> anggotaColumn;
    @FXML
    private TreeView<String> sidebarTree;

    @FXML
    private void onClickAdd() throws Exception {
        Main.showListBooksPage();
    }

    @FXML
    private void onClickLogout(ActionEvent actionEvent) throws Exception {
        Main.showLoginPage();
    }

    @FXML
    private void onClickDone(ActionEvent actionEvent) throws Exception {
        PeminjamanModel pModel;
        pModel = (PeminjamanModel) tablePeminjaman.getSelectionModel().getSelectedItem();

        FXMLLoader loader = new FXMLLoader(Main.class.getResource("CompleteTheLoan.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        CompleteTheLoanController completeTheLoanController = loader.getController();
        completeTheLoanController.getId(pModel.getId());

        Main.showUpdateBookPage(scene);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SidebarController sidebarController = new SidebarController();
        sidebarController.initSidebarItem(sidebarTree);
        PeminjamanDAO peminjamanDAO = new PeminjamanDAO();
        ObservableList<PeminjamanModel> peminjamanModels;
        peminjamanModels = peminjamanDAO.showPeminjaman(false);

        tglPinjamColumn.setCellValueFactory(cellData -> cellData.getValue().tglPeminjamanProperty());
        tglKembaliColumn.setCellValueFactory(cellData -> cellData.getValue().tglPengembalianProperty());
        dendaColumn.setCellValueFactory(cellData -> cellData.getValue().dendaProperty());
        anggotaColumn.setCellValueFactory(cellData -> cellData.getValue().studentProperty());
        tablePeminjaman.setItems(peminjamanModels);
    }
}
