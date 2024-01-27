package com.example.manajemenbuku.controller;

import com.example.manajemenbuku.DAO.BooksDAO;
import com.example.manajemenbuku.Main;
import com.example.manajemenbuku.model.Buku;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.UUID;

public class BookController {
    @FXML
    private TextField title;
    @FXML
    private Label errorTitle;
    @FXML
    private TextField author;
    @FXML
    private Label errorAuthor;
    @FXML
    private TextField publisher;
    @FXML
    private Label errorPublisher;
    @FXML
    private TextField publishYear;
    @FXML
    private Label errorPublishYear;
    @FXML
    private TextField stock;
    @FXML
    private Label errorStock;
    @FXML
    private Button saveButton;
    @FXML
    private Button updateButton;

    private int Id;

    public void getId(int id) {
        this.Id = id;
        BooksDAO booksDAO = new BooksDAO();
        Buku buku = booksDAO.getDetail(id);
        if (buku.getId() > 1) {
            saveButton.setVisible(false);
            updateButton.setVisible(true);
            title.setText(buku.getTitle());
            author.setText(buku.getAuthor());
            publisher.setText(buku.getPublisher());
            publishYear.setText(String.valueOf(buku.getPublishYear()));
            stock.setText(String.valueOf(buku.getStock()));
        }
    }

    @FXML
    private void onClickUpdate() throws Exception {
        String titleText = title.getText();
        String authorText = author.getText();
        String publisherText = publisher.getText();
        String publishYearText = publishYear.getText();
        String stockText = stock.getText();
        if (isValidField(titleText, authorText, publisherText, publishYearText, stockText)) {
            Buku buku = new Buku(
                    this.Id,
                    titleText,
                    authorText,
                    publisherText,
                    Integer.parseInt(publishYearText),
                    Integer.parseInt(stockText)
            );

            BooksDAO booksDAO = new BooksDAO();
            booksDAO.updateData(buku);
            Main.showListBooksPage();
        }
    }

    @FXML
    private void onClickCancel() throws Exception {
        Main.showListBooksPage();
    }

    @FXML
    private void onClickSave() throws Exception {
        String titleText = title.getText();
        String authorText = author.getText();
        String publisherText = publisher.getText();
        String publishYearText = publishYear.getText();
        String stockText = stock.getText();
        if (isValidField(titleText, authorText, publisherText, publishYearText, stockText)) {
            Buku buku = new Buku(
                    1,
                    titleText,
                    authorText,
                    publisherText,
                    Integer.parseInt(publishYearText),
                    Integer.parseInt(stockText)
            );

            BooksDAO booksDAO = new BooksDAO();
            booksDAO.addData(buku);
            Main.showListBooksPage();
        }

    }

    private boolean isValidField(String titleText, String authorText, String publisherText, String publishYearText, String stockText) {
        errorTitle.setText(isNotEmptyField(titleText, "Judul buku"));
        errorAuthor.setText(isNotEmptyField(authorText, "Penulis buku"));
        errorPublisher.setText(isNotEmptyField(publisherText, "Penerbit buku"));
        errorPublishYear.setText(validateFieldNumber(publishYearText, "Tahun terbit"));
        errorStock.setText(validateFieldNumber(stockText, "Stok"));

        return errorTitle.getText().isEmpty()
                && errorAuthor.getText().isEmpty()
                && errorPublisher.getText().isEmpty()
                && errorPublishYear.getText().isEmpty()
                && errorStock.getText().isEmpty();
    }

    private String validateFieldNumber(String value, String label) {
        if (value.isEmpty()) {
            return label + " harus diisi.";
        }

        if (value.matches("^[^0-9]*$")) {
            return label + " harus berupa angka.";
        }

        return "";
    }

    private String isNotEmptyField(String value, String label) {
        if (value.isEmpty()) {
            return label + " harus diisi.";
        }

        return "";
    }
}
