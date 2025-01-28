package com.java_course;

import com.java_course.DatabaseConnection;
import java.util.Date;

public class Emprunt {
    private Livre borrowedBook;
    private Membres member;
    private Date dueDate;
    private Date returnDate;
    private static final double PENALTY_PER_DAY = 1.0; // Example penalty rate

    public Emprunt(Livre borrowedBook, Membres member, Date dueDate) {
        this.borrowedBook = borrowedBook;
        this.member = member;
        this.dueDate = dueDate;
        this.returnDate = null;
    }

    public Livre getBorrowedBook() {
        return borrowedBook;
    }

    public Membres getMember() {
        return member;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void returnBook() {
        this.returnDate = new Date();
        borrowedBook.setAvailable(true);
        member.returnBook(borrowedBook.getTitle());
    }

    public double calculatePenalty() {
        if (returnDate == null) {
            return 0;
        }
        long delay = returnDate.getTime() - dueDate.getTime();
        long daysOverdue = delay / (1000 * 60 * 60 * 24);
        return daysOverdue > 0 ? daysOverdue * PENALTY_PER_DAY : 0;
    }
}
