package com.example.manajemenbuku.controller;

import com.example.manajemenbuku.DAO.CartDAO;
import com.example.manajemenbuku.Main;
import com.example.manajemenbuku.model.BookModel;
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

    @FXML
    private void onClickSubmit() throws Exception {
        Main.showAddPeminjaman();
    }

    @FXML
    private void onClickDelete(ActionEvent actionEvent) {
        BookModel bookModel;
        bookModel = (BookModel) tableBooks.getSelectionModel().getSelectedItem();
        CartDAO cartDAO = new CartDAO();
        int result = cartDAO.delData(bookModel);
        if (result != 0) {
            System.out.println("Delete Berhasil");
        }
        ObservableList<BookModel> listBooks = cartDAO.getCartBook();
        tableBooks.setItems(listBooks);
    }

    @FXML
    private void onClickCancel() throws Exception {
        Main.showListBooksPage();
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        CartDAO cartDAO = new CartDAO();
        ObservableList<BookModel> listBooks = cartDAO.getCartBook();

        titleColumn.setCellValueFactory(cellData -> cellData.getValue().getTitle());
        authorColumn.setCellValueFactory(cellData -> cellData.getValue().getAuthor());
        publisherColumn.setCellValueFactory(cellData -> cellData.getValue().getPublisher());
        publishYearColumn.setCellValueFactory(cellData -> cellData.getValue().getPublishYear());
        stockColumn.setCellValueFactory(cellData -> cellData.getValue().getStock());
        tableBooks.setItems(listBooks);
    }
}
