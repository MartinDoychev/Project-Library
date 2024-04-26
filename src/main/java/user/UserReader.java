package user;

import book.Book;
import book.IBookRepository;
import enums.Role;
import library.Library;
import enums.BookAccess;
import user.interfaces.Reader;
import user.repository.IUserRepository;

import java.sql.SQLException;
import java.util.ArrayList;

public class UserReader extends User implements Reader {
    private final IBookRepository bookRepository;
    private Library library;


    public UserReader(int userID, IUserRepository userRepository, IBookRepository bookRepository) throws SQLException {
        super(userID, Role.READER, userRepository);
//        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.library = userRepository.getUserLibrary(userID);
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
    public boolean addToLibrary(String bookName) {
        boolean result = false;
        if (bookRepository.bookExistsInGeneralLibrary(bookName)) {
            ArrayList<Book> books = bookRepository.searchBookByName(bookName);
            for (Book book: books) {
                if (book.getAccess() == BookAccess.AVAILABLE) {
                    this.library.addBookToLibrary(book.getTitle());
                    result = true;
                }
            }
        }
        return result;
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
