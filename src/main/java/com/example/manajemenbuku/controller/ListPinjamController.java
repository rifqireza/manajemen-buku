package com.example.manajemenbuku.controller;

import com.example.manajemenbuku.DAO.PeminjamanDAO;
import com.example.manajemenbuku.Main;
import com.example.manajemenbuku.model.Peminjaman;
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

public class ListPinjamController implements Initializable {
    @FXML
    private TableView<Peminjaman> tablePeminjaman;
    @FXML
    private TableColumn<Peminjaman, String> tglPinjamColumn;
    @FXML
    private TableColumn<Peminjaman, String> tglKembaliColumn;
    @FXML
    private TableColumn<Peminjaman, String> dendaColumn;
    @FXML
    private TableColumn<Peminjaman, String> anggotaColumn;
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
        Peminjaman pModel;
        pModel = (Peminjaman) tablePeminjaman.getSelectionModel().getSelectedItem();

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
        ObservableList<Peminjaman> peminjamen;
        peminjamen = peminjamanDAO.showPeminjaman(false);

        tglPinjamColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTglPeminjaman()));
        tglKembaliColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTglPengembalian()));
        dendaColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDenda()));
        anggotaColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStudent()));
        tablePeminjaman.setItems(peminjamen);
    }
}
