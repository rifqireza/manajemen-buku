package com.example.manajemenbuku.DAO;

import java.util.List;

public interface BooksInterface<E> {
    public int addData(E data);
    public int delData(E data);
    public int updateData(E data);
    public List<E> showData();
    public E getDetail(String id);
}
