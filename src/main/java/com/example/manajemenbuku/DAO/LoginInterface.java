package com.example.manajemenbuku.DAO;

public interface LoginInterface {
    public boolean login(String uname, String pw);
    public boolean isExistAdmin();
    public int register(String name, String uname, String password);
}
