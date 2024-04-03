package user;

import book.BookRepository;
import book.Book;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;

public class UserReaderTest {
    UserReader user;
    BookRepository bookRepository;

    @Before
    public void initialSetUp() throws SQLException {
        bookRepository = new BookRepository();
        user = new UserReader(bookRepository);
    }

    @Test
    public void testGetBookByName() throws SQLException {
        ArrayList<Book> books = user.searchBookByName("Ulysses");

        System.out.println(books.size());
        for (Book book : books) {
            System.out.println(book.toString());
        }
    }

    @Test
    public void testGetBookByAuthor() {
        ArrayList<Book> books = user.searchBookByAuthor("Arthur Conan Doyle");

        System.out.println(books.size());
        for (Book book : books) {
            System.out.println(book.toString());
        }
    }


}
