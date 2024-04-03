package buildingBlocks.library;

import buildingBlocks.book.Book;

import java.sql.SQLException;
import java.util.HashMap;
import java.time.*;

public class Library {
    private int libraryID;
    private String libraryName;
    private final LibraryRepository libraryRepository;

    private HashMap<Book, LocalDate> books;


    public Library() throws SQLException {
        this.libraryRepository = new LibraryRepository();
        this.libraryID = libraryRepository.getAvailableLibraryID();
        this.libraryName = "New Library";
        this.books = new HashMap<>();
    }

    public Library(int libraryID, String libraryName, LibraryRepository libraryRepository){
        this.libraryID = libraryID;
        this.libraryName = libraryName;
        this.books = new HashMap<>();
        this.libraryRepository = libraryRepository;
    }

    public void addBookToLibrary(Book book) {
        books.put(book, LocalDate.now());
    }

    public int getLibraryID() {
        return libraryID;
    }

    public void setLibraryID(int libraryID) {
        this.libraryID = libraryID;
    }

    public String getLibraryName() {
        return libraryName;
    }

    public void setLibraryName(String libraryName) {
        this.libraryName = libraryName;
    }

    public HashMap<Book, LocalDate> getBooks() {
        return books;
    }

    public void setBooks(HashMap<Book, LocalDate> books) {
        this.books = books;
    }
}
