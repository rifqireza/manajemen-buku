package com.example.manajemenbuku.controller;

import com.example.manajemenbuku.DAO.PeminjamanDAO;
import com.example.manajemenbuku.Main;
import com.example.manajemenbuku.model.PeminjamanModel;
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
    private TableView<PeminjamanModel> tablePengembalian;
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
    private void onClickDelete(ActionEvent actionEvent) throws Exception {
        PeminjamanDAO peminjamanDAO = new PeminjamanDAO();
        PeminjamanModel pModel;
        pModel = (PeminjamanModel) tablePengembalian.getSelectionModel().getSelectedItem();
        peminjamanDAO.deletePengembalian(pModel.getId());

        ObservableList<PeminjamanModel> peminjamanModels = peminjamanDAO.showPeminjaman(true);
        tablePengembalian.setItems(peminjamanModels);
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
        ObservableList<PeminjamanModel> peminjamanModels = peminjamanDAO.showPeminjaman(true);

        tglPinjamColumn.setCellValueFactory(cellData -> cellData.getValue().tglPeminjamanProperty());
        tglKembaliColumn.setCellValueFactory(cellData -> cellData.getValue().tglPengembalianProperty());
        dendaColumn.setCellValueFactory(cellData -> cellData.getValue().dendaProperty());
        anggotaColumn.setCellValueFactory(cellData -> cellData.getValue().studentProperty());
        tablePengembalian.setItems(peminjamanModels);
    }
}
