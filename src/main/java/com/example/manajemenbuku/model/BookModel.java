package com.example.manajemenbuku.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class BookModel {
    private String id;
    private StringProperty title;
    private StringProperty author;
    private StringProperty publisher;
    private StringProperty publishYear;
    private StringProperty stock;

    public BookModel(String id, String title, String author, String publisher, int publishYear, int stock) {
        this.id = id;
        this.title = new SimpleStringProperty(title);
        this.author = new SimpleStringProperty(author);
        this.publisher = new SimpleStringProperty(publisher);
        this.publishYear = new SimpleStringProperty(String.valueOf(publishYear));
        this.stock = new SimpleStringProperty(String.valueOf(stock));
    }

    @Override
    public String toString() {
        return this.title.getName() + " memiliki stock: " + this.stock.getName();
    }


    public StringProperty getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher.set(publisher);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public StringProperty getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public StringProperty getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author.set(author);
    }

    public StringProperty getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(int publishYear) {
        this.publishYear.set(String.valueOf(publishYear));
    }

    public StringProperty getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock.set(String.valueOf(stock));
    }
}
