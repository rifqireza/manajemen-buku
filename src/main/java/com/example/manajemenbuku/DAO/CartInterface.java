package com.example.manajemenbuku.DAO;

import javafx.collections.ObservableList;

public interface CartInterface<E> {
    public ObservableList<E> getCartBook();
    public int delData(E data);
    public int addCart(int idBuku);
    public int getCountById(int idBuku);
    public int reduceCart(int idBuku);
}
