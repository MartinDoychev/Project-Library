package book;

import buildingBlocks.book.Book;
import buildingBlocks.book.BookRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;


public class BookRepositoryTest{
    BookRepository bookRepository;

    @Before
    public void setUp() throws SQLException {
        bookRepository = new BookRepository();
    }

    @Test
    public void testSearchBookByNameEmpty() {
        ArrayList<Book> books = bookRepository.searchBookByName("");
        Assert.assertNotNull(books);
    }

    @Test
    public void testSearchBookByName() {
        ArrayList<Book> books = bookRepository.searchBookByName("Ulysses");
        System.out.println(books.get(0).toString());
        Assert.assertEquals("Ulysses", books.get(0).getTitle());
    }

    @Test
    public void testGetBookByAuthorEmpty() {
        ArrayList<Book> books = bookRepository.searchBookByAuthor("");
        Assert.assertNotNull(books);
    }

    @Test
    public void testGetBookByAuthor() {
        ArrayList<Book> books = bookRepository.searchBookByAuthor("Charles Dickens");
        for (Book book : books) {
            System.out.println(book.toString());
        }
    }

    @Test
    public void testGetAuthorById() throws SQLException {
        String authorName = bookRepository.getAuthor(10);

        System.out.println(authorName);
        Assert.assertEquals("Ernest Hemingway", authorName);
    }

    @Test
    public void testGetAuthorID() throws SQLException {
        int id = bookRepository.getAuthorId("George Orwell");

        System.out.println(id);
        Assert.assertEquals(6, id);
    }

    @Test
    public void testGetGenreById() throws SQLException {
        String genreName = bookRepository.getGenre(3);

        System.out.println(genreName);
        Assert.assertEquals("Children's", genreName);
    }

    @Test
    public void testGetLanguageById() throws SQLException {
        String languageName = bookRepository.getLanguage(4);

        System.out.println(languageName);
        Assert.assertEquals("Bulgarian", languageName);
    }

    public void testAddBook() {
    }
}