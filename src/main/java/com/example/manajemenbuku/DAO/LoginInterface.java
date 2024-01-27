package com.example.manajemenbuku.DAO;

import com.example.manajemenbuku.model.Admin;

public interface LoginInterface<E> {
    public boolean login(E admin);
    public boolean isExistAdmin();
    public int register(E admin);
}
