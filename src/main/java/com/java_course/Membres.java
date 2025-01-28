package com.java_course;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Membres {
    private String memberId;
    private String email;
    private Date dateInscription;
    private String name;
    private List<String> borrowedBooks;

    public Membres(String memberId, String email, String name) {
        this.memberId = memberId;
        this.email = email;
        this.name = name;
        this.borrowedBooks = new ArrayList<>();
        this.dateInscription = new Date(); // Initialize with current date
    }

    public String getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    public List<String> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void borrowBook(String bookTitle) {
        borrowedBooks.add(bookTitle);
    }

    public void returnBook(String bookTitle) {
        borrowedBooks.remove(bookTitle);
    }

    public String getEmail() {
        return email;
    }

    public Date getDateInscription() {
        return dateInscription;
    }
}
