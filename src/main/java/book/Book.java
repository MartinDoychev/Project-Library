package book;

import enums.BookAccess;

import java.sql.SQLException;

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

    private IBookRepository bookRepository;

    public Book() throws SQLException {
        this.bookID = 0;
        this.title = "";
        this.author = "";
        this.ISBN = "";
        this.genre = "";
        this.language = "";
        this.rating = 0.0;
        this.access = BookAccess.ANNOUNCED;
        this.isRead = false;
        this.bookRepository = new BookRepository();
    }

    public Book(int bookID, String title, String author, String ISBN, String genre, String language, double rating, BookAccess access, boolean isRead) throws SQLException {
        this.bookID = bookID;
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.genre = genre;
        this.language = language;
        this.rating = rating;
        this.access = access;
        this.isRead = isRead;
        this.bookRepository = new BookRepository();
    }

    public Book (int bookID, boolean isRead) throws SQLException {
        this.bookID = bookID;
        this.bookRepository = new BookRepository();
        this.title = bookRepository.getBookNameByBookID(bookID);
        this.author = bookRepository.getAuthorByBookID(bookID);
        this.ISBN = bookRepository.getISBNByBookID(bookID);
        this.genre = bookRepository.getGenreByBookID(bookID);
        this.language = bookRepository.getLanguageByBookID(bookID);
        this.rating = bookRepository.getRatingByBookID(bookID);
        this.access = bookRepository.getAccessByBookID(bookID);
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

    public int getBookID() {
        return bookID;
    }
}