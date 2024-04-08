package library;

import book.Book;
import book.BookRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;

public class LibraryRepositoryTest {
    LibraryRepository libraryRepository;
    BookRepository bookRepository;

    @Before
    public void setUp() throws SQLException {
        libraryRepository = new LibraryRepository();
        bookRepository = new BookRepository();
    }

    @Test
    public void testGetAvailableID() {
        int id = libraryRepository.getAvailableLibraryID();

        System.out.println(id);
        Assert.assertEquals(37, id);
    }

    @Test
    public void testAddBookToLibrary() {
        ArrayList<Book> books = bookRepository.searchBookByName("The Count of Monte Cristo");
        Book book = books.get(0);
        Library library = new Library(6, "albertCam", libraryRepository);
        int rows = libraryRepository.addToLibrary(book, library);
        System.out.println(rows);
    }
}
