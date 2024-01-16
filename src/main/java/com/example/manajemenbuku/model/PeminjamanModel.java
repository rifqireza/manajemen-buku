package com.example.manajemenbuku.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PeminjamanModel {
    private int id;
    private StringProperty tglPeminjaman;
    private StringProperty tglPengembalian;
    private StringProperty denda;
    private StringProperty student;

    public PeminjamanModel(int id, String tglPeminjaman, String tglPengembalian, String denda, String student) {
        this.id = id;
        this.tglPeminjaman = new SimpleStringProperty(tglPeminjaman);
        this.tglPengembalian = new SimpleStringProperty(tglPengembalian);
        this.denda = new SimpleStringProperty(denda);
        this.student = new SimpleStringProperty(student);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTglPeminjaman() {
        return tglPeminjaman.get();
    }

    public StringProperty tglPeminjamanProperty() {
        return tglPeminjaman;
    }

    public void setTglPeminjaman(String tglPeminjaman) {
        this.tglPeminjaman.set(tglPeminjaman);
    }

    public String getTglPengembalian() {
        return tglPengembalian.get();
    }

    public StringProperty tglPengembalianProperty() {
        return tglPengembalian;
    }

    public void setTglPengembalian(String tglPengembalian) {
        this.tglPengembalian.set(tglPengembalian);
    }

    public String getDenda() {
        return denda.get();
    }

    public StringProperty dendaProperty() {
        return denda;
    }

    public void setDenda(String denda) {
        this.denda.set(denda);
    }

    public String getStudent() {
        return student.get();
    }

    public StringProperty studentProperty() {
        return student;
    }

    public void setStudent(String student) {
        this.student.set(student);
    }
}
