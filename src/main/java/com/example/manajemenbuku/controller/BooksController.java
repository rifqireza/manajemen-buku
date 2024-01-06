package com.example.manajemenbuku.controller;

import com.example.manajemenbuku.DAO.BooksDAO;
import com.example.manajemenbuku.Main;
import com.example.manajemenbuku.model.BookModel;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class BooksController implements Initializable {
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
    private TreeView<String> sidebarTree;

    @FXML
    private void onClickAdd() throws Exception {
        Main.showAddBookPage();
    }

    @FXML
    private void onClickDelete(ActionEvent actionEvent) {
        BookModel bookModel;
        bookModel = (BookModel) tableBooks.getSelectionModel().getSelectedItem();
        BooksDAO booksDAO = new BooksDAO();
        int result = booksDAO.delData(bookModel);
        if (result != 0) {
            System.out.println("Delete Berhasil");
        }
        ObservableList<BookModel> listBooks = booksDAO.showData();
        tableBooks.setItems(listBooks);
    }

    @FXML
    private void onClickUpdate(ActionEvent actionEvent) throws Exception {
        BookModel bookModel;
        bookModel = (BookModel) tableBooks.getSelectionModel().getSelectedItem();

        FXMLLoader loader = new FXMLLoader(Main.class.getResource("AddBook.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        BookController addBookControllers = loader.getController();
        addBookControllers.getId(bookModel.getId());

        Main.showUpdateBookPage(scene);
    }

    @FXML
    private void onClickLogout(ActionEvent actionEvent) throws Exception {
        Main.showLoginPage();
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        SidebarController sidebarController = new SidebarController();
        sidebarController.initSidebarItem(sidebarTree);
        BooksDAO booksDAO = new BooksDAO();
        ObservableList<BookModel> listBooks = booksDAO.showData();

        titleColumn.setCellValueFactory(cellData -> cellData.getValue().getTitle());
        authorColumn.setCellValueFactory(cellData -> cellData.getValue().getAuthor());
        publisherColumn.setCellValueFactory(cellData -> cellData.getValue().getPublisher());
        publishYearColumn.setCellValueFactory(cellData -> cellData.getValue().getPublishYear());
        stockColumn.setCellValueFactory(cellData -> cellData.getValue().getStock());
        tableBooks.setItems(listBooks);
    }


}
