package user;

import enums.Role;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;

public class UserTest {
    @Test
    public void testCheckCredentials() {
        String pass = "bookLover1)";
        Assert.assertTrue(User.checkCredentials("aMckinney", pass));
    }

    @Test
    public void testGetUserID() throws SQLException {
        Assert.assertEquals(11, User.getUserIDFromDB("DanAli44"));
    }

    @Test
    public void testUserToStringAndGetUserFromDB() throws SQLException {
        User user = User.getUserFromDB("DinBuz34", "5572fbceb2107621794337fc09f12de0");
        System.out.println(user.toString());
    }

    @Test
    public void testGetRoleFromDB() throws SQLException {
        Role role = User.getRoleFromDB(17);
        Assert.assertEquals(Role.AUTHOR, role);
    }

    @Test
    public void testGetRole() {
        Role role = User.getRole(3);
        Assert.assertEquals(Role.ADMIN, role);
    }

    @Test
    public void testGetUserIDFromDB() throws SQLException {
        int id = User.getUserIDFromDB("cNieves");
        Assert.assertEquals(1, id);
    }

    @Test
    public void testGetUserFromDB() throws SQLException {
        User user = User.getUserFromDB("ArtCon37", "887e2a1473435a436ea407e4db58834c");
        System.out.println(user.toString());
    }

}
