package com.example.manajemenbuku.controller;

import com.example.manajemenbuku.DAO.PeminjamanDAO;
import com.example.manajemenbuku.Main;
import com.example.manajemenbuku.model.Buku;
import com.example.manajemenbuku.model.DetailPeminjaman;
import com.example.manajemenbuku.model.Peminjaman;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


public class CompleteTheLoanController {
    @FXML
    private Label tglPeminjaman;
    @FXML
    private Label tglPengembalian;
    @FXML
    private Label denda;
    @FXML
    private Label nama;
    @FXML
    private TableView<Buku> tableBooks;
    @FXML
    private TableColumn<Buku, String> titleColumn;
    @FXML
    private TableColumn<Buku, String> authorColumn;
    @FXML
    private TableColumn<Buku, String> publisherColumn;
    @FXML
    private TableColumn<Buku, String> publishYearColumn;
    @FXML
    private TableColumn<Buku, String> stockColumn;

    private int Id;

    public void getId(int id) {
        this.Id = id;
        System.out.println(id);
        PeminjamanDAO peminjamanDAO = new PeminjamanDAO();
        DetailPeminjaman dpModel = peminjamanDAO.getDetailPeminjaman(id);

        Peminjaman pModel = dpModel.getPeminjamanModel();
        tglPeminjaman.setText(pModel.getTglPeminjaman());
        tglPengembalian.setText(pModel.getTglPengembalian());
        denda.setText(pModel.getDenda());
        nama.setText(pModel.getStudent());

        titleColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitle()));
        authorColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAuthor()));
        publisherColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPublisher()));
        publishYearColumn.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getPublishYear())));
        stockColumn.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getStock())));
        tableBooks.setItems(dpModel.getBookModel());
    }

    @FXML
    private void onClickCancel() throws Exception {
        Main.showListPeminjaman();
    }

    @FXML
    private void onClickSave() throws Exception {
        PeminjamanDAO peminjamanDAO = new PeminjamanDAO();
        peminjamanDAO.completeTheLoan(Id, tableBooks.getItems());
        Main.showListPeminjaman();
    }
}
