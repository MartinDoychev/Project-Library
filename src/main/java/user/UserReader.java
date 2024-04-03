package user;

import book.Book;
import book.IBookRepository;
import library.Library;
import enums.BookAccess;
import user.interfaces.Reader;

import java.sql.SQLException;
import java.util.ArrayList;

public class UserReader extends User implements Reader {

    private Library library;
    private final IBookRepository bookRepository;

    public UserReader(IBookRepository bookRepository) throws SQLException {
        this.bookRepository = bookRepository;
        this.library = new Library();
    }


    @Override
    public ArrayList<Book> searchBookByName(String bookName) throws SQLException {
        ArrayList<Book> books = new ArrayList<>();
        ArrayList<Book> collectedBooks = bookRepository.searchBookByName(bookName);

        if (!collectedBooks.isEmpty()) {
            for (Book book : collectedBooks) {
                if (book.getAccess() == BookAccess.AVAILABLE) {
                    books.add(book);
                }
            }
        }
        return books;
    }

    @Override
    public ArrayList<Book> searchBookByAuthor(String authorName) {
        ArrayList<Book> books = new ArrayList<>();
        ArrayList<Book> collectedBooks = bookRepository.searchBookByAuthor(authorName);

        if (!collectedBooks.isEmpty()) {
            for (Book book : collectedBooks) {
                if (book.getAccess() == BookAccess.AVAILABLE) {
                    books.add(book);
                }
            }
        }
        return books;
    }

    @Override
    public boolean addToLibrary(Book book) {
        return false;
    }

    @Override
    public void sortLibraryByAuthor(Library library) {

    }

    @Override
    public void sortLibraryByName(Library library) {

    }

    @Override
    public void sortLibraryByGenre(Library library) {

    }

    @Override
    public void rateBook(String title) {

    }

    @Override
    public void showLastRead() {

    }
}
