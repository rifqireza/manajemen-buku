package com.example.manajemenbuku.controller;

import com.example.manajemenbuku.model.BookModel;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class BooksController implements Initializable {
    @FXML
    private ListView<BookModel> listBooks;
//    @FXML
//    private ComboBox<BookModel> comboBooks;
    @FXML
    private TableView<BookModel> tableBooks;
    @FXML
    TableColumn<BookModel, String> firstColumns;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<BookModel> listBook;
        listBook = FXCollections.observableArrayList(
                new BookModel("1",  "Bumi Manusia", "Ini Buku", 4),
                new BookModel("2", "Bumi Manusia 2", "Ini Buku 2", 3),
                new BookModel("3", "Bumi Manusia 3", "Ini Buku 3", 7)
        );

//        listBooks.setItems(listBook);
//        firstColumns.setCellValueFactory(
//                new PropertyValueFactory<BookModel, String>("description")
//        );
        tableBooks.setItems(listBook);
    }
}
