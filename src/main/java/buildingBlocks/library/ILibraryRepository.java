package buildingBlocks.library;

import buildingBlocks.dbConnection.DBConnectionManager;

import java.sql.Connection;
import java.sql.SQLException;

public interface ILibraryRepository {
    void sortLibraryByAuthor();
    void sortLibraryByGenre();
    void sortLibraryByName();
    int getAvailableLibraryID();
}
