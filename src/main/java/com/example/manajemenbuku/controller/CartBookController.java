package com.example.manajemenbuku.controller;

import com.example.manajemenbuku.DAO.CartDAO;
import com.example.manajemenbuku.Main;
import com.example.manajemenbuku.model.Buku;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class CartBookController implements Initializable {
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

    @FXML
    private void onClickSubmit() throws Exception {
        Main.showAddPeminjaman();
    }

    @FXML
    private void onClickDelete(ActionEvent actionEvent) {
        Buku buku;
        buku = (Buku) tableBooks.getSelectionModel().getSelectedItem();
        CartDAO cartDAO = new CartDAO();
        int result = cartDAO.delData(buku);
        if (result != 0) {
            System.out.println("Delete Berhasil");
        }
        ObservableList<Buku> listBukus = cartDAO.getCartBook();
        tableBooks.setItems(listBukus);
    }

    @FXML
    private void onClickCancel() throws Exception {
        Main.showListBooksPage();
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        CartDAO cartDAO = new CartDAO();
        ObservableList<Buku> listBukus = cartDAO.getCartBook();

        titleColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitle()));
        authorColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAuthor()));
        publisherColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPublisher()));
        publishYearColumn.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getPublishYear())));
        stockColumn.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getStock())));
        tableBooks.setItems(listBukus);
    }
}
