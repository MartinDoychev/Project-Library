package book;

import java.util.ArrayList;

public interface IBookRepository {
    ArrayList<Book> searchBookByName(String bookName);

    ArrayList<Book> searchBookByAuthor(String authorName);

    String getAuthor(int authorID);
    int getAuthorId(String authorName);
    String getGenre(int genreID);
    String getLanguage(int languageID);
    void addBook();

}
