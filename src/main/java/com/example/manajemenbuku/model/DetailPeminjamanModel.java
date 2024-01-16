package com.example.manajemenbuku.model;

import javafx.collections.ObservableList;

public class DetailPeminjamanModel {
    private PeminjamanModel peminjamanModel;
    private ObservableList<BookModel> bookModel;

    public DetailPeminjamanModel(PeminjamanModel peminjamanModel, ObservableList<BookModel> bookModel) {
        this.peminjamanModel = peminjamanModel;
        this.bookModel = bookModel;
    }

    public PeminjamanModel getPeminjamanModel() {
        return peminjamanModel;
    }

    public void setPeminjamanModel(PeminjamanModel peminjamanModel) {
        this.peminjamanModel = peminjamanModel;
    }

    public ObservableList<BookModel> getBookModel() {
        return bookModel;
    }

    public void setBookModel(ObservableList<BookModel> bookModel) {
        this.bookModel = bookModel;
    }
}
