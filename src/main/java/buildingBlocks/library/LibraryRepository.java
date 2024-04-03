package buildingBlocks.library;

import buildingBlocks.dbConnection.DBConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LibraryRepository implements ILibraryRepository {

    private final Connection connection;

    public LibraryRepository() throws SQLException {
        DBConnectionManager instance = DBConnectionManager.getInstance();
        this.connection = instance.getConnection();
    }
    @Override
    public void sortLibraryByAuthor() {

    }

    @Override
    public void sortLibraryByGenre() {

    }

    @Override
    public void sortLibraryByName() {

    }

    @Override
    public int getAvailableLibraryID() {
        int availableID = 0;

        try {
            String selectQuery = "select max(libraryID) from library";
            PreparedStatement selectStatement = this.connection.prepareStatement(selectQuery);
            ResultSet resultSet = selectStatement.executeQuery();
            if (resultSet.next()) {
                availableID = resultSet.getInt("max(libraryID)") + 1;
            }
        } catch (SQLException e) {
            System.out.printf("Error message: %s, cause: %s%n", e.getMessage(), e.getCause());
        }

        return availableID;
    }
}
