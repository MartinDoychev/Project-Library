package buildingBlocks.user.interfaces;

import buildingBlocks.book.Book;
import buildingBlocks.library.Library;

import java.sql.SQLException;
import java.util.ArrayList;

public interface Reader {
    ArrayList<Book> searchBookByName(String bookName) throws SQLException;

    ArrayList<Book> searchBookByAuthor(String authorName);

    boolean addToLibrary(Book book);

    void sortLibraryByAuthor(Library library);

    void sortLibraryByName(Library library);

    void sortLibraryByGenre(Library library);

    void rateBook(String title);

    void showLastRead();

}
