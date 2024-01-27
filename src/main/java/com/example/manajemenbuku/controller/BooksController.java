package com.example.manajemenbuku.controller;

import com.example.manajemenbuku.DAO.BooksDAO;
import com.example.manajemenbuku.DAO.CartDAO;
import com.example.manajemenbuku.Main;
import com.example.manajemenbuku.model.Buku;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class BooksController implements Initializable {
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
    private TableColumn<Buku, Node> action;
    @FXML
    private TreeView<String> sidebarTree;
    @FXML
    private Label countLabel;

    @FXML
    private void onClickCart() throws Exception {
        Main.showCartPage();
    }

    @FXML
    private void onClickAdd() throws Exception {
        Main.showAddBookPage();
    }

    @FXML
    private void onClickDelete(ActionEvent actionEvent) {
        Buku buku;
        buku = (Buku) tableBooks.getSelectionModel().getSelectedItem();
        BooksDAO booksDAO = new BooksDAO();
        int result = booksDAO.delData(buku);
        if (result != 0) {
            System.out.println("Delete Berhasil");
        }
        init();
    }

    @FXML
    private void onClickUpdate(ActionEvent actionEvent) throws Exception {
        Buku buku;
        buku = (Buku) tableBooks.getSelectionModel().getSelectedItem();

        FXMLLoader loader = new FXMLLoader(Main.class.getResource("AddBook.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        BookController addBookControllers = loader.getController();
        addBookControllers.getId(buku.getId());

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

        ObservableList<Buku> listBukus = booksDAO.showData();
        countLabel.setText(String.valueOf(booksDAO.countCart()));

        titleColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitle()));
        authorColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAuthor()));
        publisherColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPublisher()));
        publishYearColumn.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getPublishYear())));
        stockColumn.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getStock())));
        action.setCellFactory(param -> {
            TableCell<Buku, Node> cell = new TableCell<Buku, Node>() {
                private final Button button = new Button("Tambah Keranjang");
                private Button minButton = new Button("-");
                private Label label = new Label("");
                private Button plusButton = new Button("+");

                {
                    button.setStyle("-fx-background-color: #0589ff; -fx-text-fill: white;");
                    button.setOnAction(event -> {
                        CartDAO cartDAO = new CartDAO();
                        Buku buku = (Buku) getTableView().getItems().get(getIndex());
                        addToCart(buku.getId());
                    });

                    minButton.setStyle("-fx-background-color: red; -fx-text-fill: white;");
                    minButton.setOnAction(event -> {
                        CartDAO cartDAO = new CartDAO();
                        Buku buku = (Buku) getTableView().getItems().get(getIndex());
                        cartDAO.reduceCart(buku.getId());
                        init();
                    });

                    plusButton.setStyle("-fx-background-color: #0589ff; -fx-text-fill: white;");
                    plusButton.setOnAction(event -> {
                        CartDAO cartDAO = new CartDAO();
                        Buku buku = (Buku) getTableView().getItems().get(getIndex());
                        if (buku.getStock() > 0) {
                            addToCart(buku.getId());
                        }
                    });
                }

                @Override
                protected void updateItem(Node item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        Buku buku = (Buku) getTableView().getItems().get(getIndex());
                        CartDAO cartDAO = new CartDAO();
                        int total = cartDAO.getCountById(buku.getId());
                        if (total > 0) {
                            label.setText(String.valueOf(total));
                            HBox hBox = new HBox(minButton, label, plusButton);
                            hBox.setSpacing(30.0);
                            setGraphic(hBox);
                        } else {
                            setGraphic(button);
                        }

                    }
                }
            };
            return cell;
        });
        tableBooks.setItems(listBukus);
    }

    private void addToCart(int id) {
        CartDAO cartDAO = new CartDAO();
        cartDAO.addCart(id);
        init();
    }

    private void init() {
        BooksDAO booksDAO = new BooksDAO();
        countLabel.setText(String.valueOf(booksDAO.countCart()));
        ObservableList<Buku> listBukus = booksDAO.showData();
        tableBooks.setItems(listBukus);
    }
}
