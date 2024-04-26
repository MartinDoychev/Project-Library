package user.repository;

import library.Library;
import user.User;

import java.sql.Connection;
import java.sql.SQLException;

public interface IUserRepository {
    boolean userExistsInGeneralDB(int userID);
    int getUserLibraryID(int userID);
    Connection getConnection ();
    Library getUserLibrary(int userID) throws SQLException;

}
