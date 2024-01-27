package com.example.manajemenbuku.DAO;

import com.example.manajemenbuku.model.Buku;
import com.example.manajemenbuku.model.DetailPeminjaman;
import javafx.collections.ObservableList;

public interface PeminjamanInterface<E> {
    public int addPeminjaman(E data, ObservableList<Buku> bukus);
    public ObservableList<E> showPeminjaman(boolean selesai);
    public DetailPeminjaman getDetailPeminjaman(int id);
    public int completeTheLoan(int id, ObservableList<Buku> bukus);
    public int deletePengembalian(int id);
}
