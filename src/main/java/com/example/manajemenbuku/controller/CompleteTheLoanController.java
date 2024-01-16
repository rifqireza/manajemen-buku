package com.example.manajemenbuku.controller;

import com.example.manajemenbuku.DAO.PeminjamanDAO;
import com.example.manajemenbuku.Main;
import com.example.manajemenbuku.model.BookModel;
import com.example.manajemenbuku.model.DetailPeminjamanModel;
import com.example.manajemenbuku.model.PeminjamanModel;
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
    private TableView<BookModel> tableBooks;
    @FXML
    private TableColumn<BookModel, String> titleColumn;
    @FXML
    private TableColumn<BookModel, String> authorColumn;
    @FXML
    private TableColumn<BookModel, String> publisherColumn;
    @FXML
    private TableColumn<BookModel, String> publishYearColumn;
    @FXML
    private TableColumn<BookModel, String> stockColumn;

    private int Id;

    public void getId(int id) {
        this.Id = id;
        System.out.println(id);
        PeminjamanDAO peminjamanDAO = new PeminjamanDAO();
        DetailPeminjamanModel dpModel = peminjamanDAO.getDetailPeminjaman(id);

        PeminjamanModel pModel = dpModel.getPeminjamanModel();
        tglPeminjaman.setText(pModel.getTglPeminjaman());
        tglPengembalian.setText(pModel.getTglPengembalian());
        denda.setText(pModel.getDenda());
        nama.setText(pModel.getStudent());

        titleColumn.setCellValueFactory(cellData -> cellData.getValue().getTitle());
        authorColumn.setCellValueFactory(cellData -> cellData.getValue().getAuthor());
        publisherColumn.setCellValueFactory(cellData -> cellData.getValue().getPublisher());
        publishYearColumn.setCellValueFactory(cellData -> cellData.getValue().getPublishYear());
        stockColumn.setCellValueFactory(cellData -> cellData.getValue().getStock());
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
