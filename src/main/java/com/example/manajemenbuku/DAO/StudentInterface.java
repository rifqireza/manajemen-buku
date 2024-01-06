package com.example.manajemenbuku.DAO;

import javafx.collections.ObservableList;

import java.util.List;

public interface StudentInterface<E> {
    public int addData(E data);
    public int delData(E data);
    public int updateData(E data);
    public ObservableList<E> showData();
    public E getDetail(String id);
}
