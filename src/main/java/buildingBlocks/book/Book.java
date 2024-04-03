package buildingBlocks.book;

import buildingBlocks.enums.BookAccess;

public class Book {
    private int bookID;
    private String title;
    private String author;
    private String ISBN;
    private String genre;
    private String language;
    private double rating;
    private BookAccess access;
    private boolean isRead;

    public Book() {
        this.bookID = 0;
        this.title = "";
        this.author = "";
        this.ISBN = "";
        this.genre = "";
        this.language = "";
        this.rating = 0.0;
        this.access = BookAccess.ANNOUNCED;
        this.isRead = false;
    }

    public Book(int bookID, String title, String author, String ISBN, String genre, String language, double rating, BookAccess access, boolean isRead) {
        this.bookID = bookID;
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.genre = genre;
        this.language = language;
        this.rating = rating;
        this.access = access;
        this.isRead = isRead;
    }

    public double getRating() {
        return rating;
    }
    public void setRating(double rating) {
        this.rating = rating;
    }

    public boolean getIsRead() {
        return isRead;
    }

    public void setIsRead(boolean isRead) {
        this.isRead = isRead;
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("| ")
              .append(String.format("%-10s | %-40s | %-20s | %-30s | %-20s | %5.02f |", bookID, title, author, genre, language, rating));

        return result.toString();
    }

    public String getTitle() {
        return title;
    }

    public BookAccess getAccess() {
        return access;
    }
}