package dbConnection_Test;

import buildingBlocks.dbConnection.DBConnectionManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;

public class DBConnection_Test {

    DBConnectionManager instance;
    @Before
    public void instanceSetUp() {
        instance = DBConnectionManager.getInstance();
    }

    @Test
    public void testGetInstance() {
        //instance = DBConnectionManager.getInstance();
        Assert.assertNotEquals(null, instance);
    }

    @Test
    public void testGetConnection() throws SQLException {
        Connection connection = instance.getConnection();
        Assert.assertNotEquals(null, connection);
    }


}
