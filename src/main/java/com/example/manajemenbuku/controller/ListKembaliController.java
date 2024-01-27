package com.example.manajemenbuku.controller;

import com.example.manajemenbuku.DAO.PeminjamanDAO;
import com.example.manajemenbuku.Main;
import com.example.manajemenbuku.model.Peminjaman;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeView;

import java.net.URL;
import java.util.ResourceBundle;

public class ListKembaliController implements Initializable {
    @FXML
    private TableView<Peminjaman> tablePengembalian;
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
    private void onClickDelete(ActionEvent actionEvent) throws Exception {
        PeminjamanDAO peminjamanDAO = new PeminjamanDAO();
        Peminjaman pModel;
        pModel = (Peminjaman) tablePengembalian.getSelectionModel().getSelectedItem();
        peminjamanDAO.deletePengembalian(pModel.getId());

        ObservableList<Peminjaman> peminjamen = peminjamanDAO.showPeminjaman(true);
        tablePengembalian.setItems(peminjamen);
    }

    @FXML
    private void onClickLogout(ActionEvent actionEvent) throws Exception {
        Main.showLoginPage();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SidebarController sidebarController = new SidebarController();
        sidebarController.initSidebarItem(sidebarTree);
        PeminjamanDAO peminjamanDAO = new PeminjamanDAO();
        ObservableList<Peminjaman> peminjamen = peminjamanDAO.showPeminjaman(true);

        tglPinjamColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTglPeminjaman()));
        tglKembaliColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTglPengembalian()));
        dendaColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDenda()));
        anggotaColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStudent()));
        tablePengembalian.setItems(peminjamen);
    }
}
