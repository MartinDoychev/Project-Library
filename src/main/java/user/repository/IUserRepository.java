package user.repository;

import book.Book;
import library.Library;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IUserRepository {
    boolean userExistsInGeneralDB(int userID);
    int getUserLibraryID(int userID);

    Library getUserLibrary(int userID) throws SQLException;
    ArrayList<Book> getUserLibraryListSortedBy(int userID, String sortBy);
}
