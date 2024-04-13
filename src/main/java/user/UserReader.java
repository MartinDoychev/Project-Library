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
//    private final IUserRepository userRepository;
    private final IBookRepository bookRepository;
    private Library library;


    public UserReader(int userID, IUserRepository userRepository, IBookRepository bookRepository) throws SQLException {
        super(userID, Role.READER, userRepository);
//        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.library = userRepository.getUserLibrary(userID);
    }

    @Override
    public ArrayList<Book> searchBookByName(String bookName) {
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
            for (Book book : books) {
                if (book.getAccess() == BookAccess.AVAILABLE) {
                    this.library.addBookToLibrary(book.getTitle());
                    result = true;
                }
            }
        }
        return result;
    }


    @Override
    public void sortLibraryByAuthor() {
        int userID = super.getUserID();
        ArrayList<Book> books = super.getUserRepository().getUserLibraryListSortedBy(userID, "AuthorName");
        for (Book book : books) {
            System.out.println(book.toString());
        }
    }

    @Override
    public void sortLibraryByGenre() {
        int userID = super.getUserID();
        ArrayList<Book> books = super.getUserRepository().getUserLibraryListSortedBy(userID, "g.genreName");
        for (Book book : books) {
            System.out.println(book.toString());
        }
    }

    @Override
    public void sortLibraryByTitle() {
        int userID = super.getUserID();
        ArrayList<Book> books = super.getUserRepository().getUserLibraryListSortedBy(userID, "b.Title");
        for (Book book : books) {
            System.out.println(book.toString());
        }
    }


    @Override
    public void rateBook(int bookID, double rating) {
        try {
            Book book = bookRepository.getBookByID(bookID);
            if (book.getAccess() == BookAccess.AVAILABLE) {
                bookRepository.rateBook(bookID, rating);
            }
        } catch (SQLException e) {
            System.out.printf("Error message: %s, cause: %s%n", e.getMessage(), e.getCause());
        }
    }

    @Override
    public void showLastRead() {
        int userID = super.getUserID();
        ArrayList<Book> books = super.getUserRepository().getUserLibraryListSortedBy(userID, "bl.DateRead DESC LIMIT 5");
        for (Book book : books) {
            if (book.getIsRead()) {
                System.out.println(book.toString());
            }
        }
    }
}
