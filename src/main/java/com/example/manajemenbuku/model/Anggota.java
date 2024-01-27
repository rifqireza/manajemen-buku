package com.example.manajemenbuku.model;

public class Anggota {
    private int id;
    private String name;
    private String NIM;
    private String prodi;
    private String noTelp;

    public Anggota(int id, String name, String nim, String prodi, String noTelp) {
        this.id = id;
        this.name = name;
        this.NIM = nim;
        this.prodi = prodi;
        this.noTelp = noTelp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNIM() {
        return NIM;
    }

    public void setNIM(String NIM) {
        this.NIM = NIM;
    }

    public String getProdi() {
        return prodi;
    }

    public void setProdi(String prodi) {
        this.prodi = prodi;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }
}
