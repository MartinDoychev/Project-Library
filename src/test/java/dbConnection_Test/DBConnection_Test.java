package dbConnection_Test;

import buildingBlocks.dbConnection.DBConnectionManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnection_Test {

    DBConnectionManager instance;

    @Before
    public void instanceSetUp() {
        instance = DBConnectionManager.getInstance();
    }

    @Test
    public void testGetInstance() {
//        instance = DBConnectionManager.getInstance();
        Assert.assertNotEquals(null, instance);
    }

    @Test
    public void testGetConnection() throws SQLException {
        Connection connection = instance.getConnection();
        Assert.assertNotEquals(null, connection);
    }

    @Test
    public void testInsertQuery() throws SQLException {
        Connection connection = instance.getConnection();

        String insertQuery = "INSERT INTO role(userRoleID, userRole) VALUES(?, ?)";
        PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
        insertStatement.setString( 1,"5");
        insertStatement.setString( 2,"Test1");
        int rowsInserted = insertStatement.executeUpdate();
        System.out.println(rowsInserted + " row(s) inserted.");
    }

    @Test
    public void testSelectQuery() throws SQLException {
        Connection connection = instance.getConnection();

        String selectQuery = "SELECT * FROM role where userRole like ?";
        PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
        selectStatement.setString(1, "ADMIN");
        ResultSet resultSet = selectStatement.executeQuery();
        while (resultSet.next()) {
            // Process each row
            String column1Value = resultSet.getString("userRoleID");
            String columnRole = resultSet.getString("userRole");
//            System.out.println("UserRoleID: " + column1Value + " | Role: " + columnRole);
            System.out.println(column1Value + " | " + columnRole);
        }
    }
}
