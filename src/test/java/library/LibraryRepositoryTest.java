package library;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

public class LibraryRepositoryTest {
    LibraryRepository libraryRepository;

    @Before
    public void setUp() throws SQLException {
        libraryRepository = new LibraryRepository();
    }

    @Test
    public void testGetAvailableID() {
        int id = libraryRepository.getAvailableLibraryID();

        System.out.println(id);
        Assert.assertEquals(37, id);
    }
}
