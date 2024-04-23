package user;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

public class UserAdminTest {
    UserAdmin userAdmin;

    @Before
    public void userSetUp() throws SQLException {
        userAdmin = new UserAdmin();
    }

    @Test
    public void testLockAccount() {
        Assert.assertTrue(userAdmin.lockAccount(21));
    }
}
