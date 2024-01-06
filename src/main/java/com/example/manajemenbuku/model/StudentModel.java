package com.example.manajemenbuku.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class StudentModel {
    private String id;
    private StringProperty name;
    private StringProperty NIM;
    private StringProperty prodi;
    private StringProperty noTelp;

    public StudentModel(String id, String name, String nim, String prodi, String noTelp) {
        this.id = id;
        this.name = new SimpleStringProperty(name);
        this.NIM = new SimpleStringProperty(nim);
        this.prodi = new SimpleStringProperty(prodi);
        this.noTelp = new SimpleStringProperty(noTelp);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNIM() {
        return NIM.get();
    }

    public StringProperty NIMProperty() {
        return NIM;
    }

    public void setNIM(String NIM) {
        this.NIM.set(NIM);
    }

    public String getProdi() {
        return prodi.get();
    }

    public StringProperty prodiProperty() {
        return prodi;
    }

    public void setProdi(String prodi) {
        this.prodi.set(prodi);
    }

    public String getNoTelp() {
        return noTelp.get();
    }

    public StringProperty noTelpProperty() {
        return noTelp;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp.set(noTelp);
    }
}
