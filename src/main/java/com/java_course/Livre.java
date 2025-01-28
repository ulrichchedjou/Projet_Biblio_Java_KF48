package com.java_course;

public class Livre {
    private final String title;
    private final String author;
    private final String isbn;
    private boolean isAvailable;
    private final String categorie;

    public Livre(String title, String author, String isbn, String categorie) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.isAvailable = true;
        this.categorie = categorie;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public String getCategorie() {
        return categorie;
    }
}
