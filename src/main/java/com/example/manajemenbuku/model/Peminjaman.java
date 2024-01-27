package com.example.manajemenbuku.model;

public class Peminjaman {
    private int id;
    private String tglPeminjaman;
    private String tglPengembalian;
    private String denda;
    private String student;

    public Peminjaman(int id, String tglPeminjaman, String tglPengembalian, String denda, String student) {
        this.id = id;
        this.tglPeminjaman = tglPeminjaman;
        this.tglPengembalian = tglPengembalian;
        this.denda = denda;
        this.student = student;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTglPeminjaman() {
        return tglPeminjaman;
    }

    public void setTglPeminjaman(String tglPeminjaman) {
        this.tglPeminjaman = tglPeminjaman;
    }

    public String getTglPengembalian() {
        return tglPengembalian;
    }

    public void setTglPengembalian(String tglPengembalian) {
        this.tglPengembalian = tglPengembalian;
    }

    public String getDenda() {
        return denda;
    }

    public void setDenda(String denda) {
        this.denda = denda;
    }

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }
}
