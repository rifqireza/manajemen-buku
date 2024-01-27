package com.example.manajemenbuku.model;

import javafx.collections.ObservableList;

public class DetailPeminjaman {
    private Peminjaman peminjaman;
    private ObservableList<Buku> buku;

    public DetailPeminjaman(Peminjaman peminjaman, ObservableList<Buku> buku) {
        this.peminjaman = peminjaman;
        this.buku = buku;
    }

    public Peminjaman getPeminjamanModel() {
        return peminjaman;
    }

    public void setPeminjamanModel(Peminjaman peminjaman) {
        this.peminjaman = peminjaman;
    }

    public ObservableList<Buku> getBookModel() {
        return buku;
    }

    public void setBookModel(ObservableList<Buku> buku) {
        this.buku = buku;
    }
}
