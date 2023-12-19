package com.example.manajemenbuku.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class BookModel {
    private final SimpleStringProperty id;
    private String name;
    private final SimpleStringProperty description;
    private final SimpleIntegerProperty stock;

    @Override
    public String toString() {
        return "ini buku " + this.name;
    }

    public BookModel(String id, String name, String description, int stock) {
        this.id = new SimpleStringProperty(id);
        this.name = name;
        this.description = new SimpleStringProperty(description);
        this.stock = new SimpleIntegerProperty(stock);
    }

    public String getId() {
        return id.get();
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description.get();
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public int getStock() {
        return stock.get();
    }

    public void setStock(int stock) {
        this.stock.set(stock);
    }
}
