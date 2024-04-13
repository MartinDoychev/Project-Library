package book;

import enums.BookAccess;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IBookRepository {
    ArrayList<Book> searchBookByName(String bookName);

    ArrayList<Book> searchBookByAuthor(String authorName);

    String getAuthor(int authorID);

    int getAuthorId(String authorName);

    String getGenre(int genreID);

    String getLanguage(int languageID);

    void addBook();

    boolean bookExistsInGeneralLibrary(String bookName);

    String getBookNameByBookID(int bookID);

    String getAuthorByBookID(int bookID);

    String getISBNByBookID(int bookID);

    String getGenreByBookID(int bookID);

    String getLanguageByBookID(int bookID);

    Double getRatingByBookID(int bookID);

    BookAccess getAccessByBookID(int bookID);

    void rateBook(int bookID, double newRating) throws SQLException;

    Book getBookByID(int bookID) throws SQLException;

}
