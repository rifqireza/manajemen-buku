package com.example.manajemenbuku.DAO;

import com.example.manajemenbuku.model.BookModel;
import com.example.manajemenbuku.model.DetailPeminjamanModel;
import javafx.collections.ObservableList;

public interface PeminjamanInterface<E> {
    public int addPeminjaman(E data, ObservableList<BookModel> bookModels);
    public ObservableList<E> showPeminjaman(boolean selesai);
    public DetailPeminjamanModel getDetailPeminjaman(int id);
    public int completeTheLoan(int id, ObservableList<BookModel> bookModels);
    public int deletePengembalian(int id);
}
