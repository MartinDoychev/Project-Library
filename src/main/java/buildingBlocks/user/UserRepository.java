package buildingBlocks.user;

import buildingBlocks.dbConnection.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;

import static java.lang.String.valueOf;

public class UserRepository implements IUserRepository {
    private final Connection connection;

    public UserRepository() throws SQLException {
        DBConnectionManager instance = DBConnectionManager.getInstance();
        this.connection = instance.getConnection();
    }
}