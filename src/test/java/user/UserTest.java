package user;

import enums.Role;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import user.repository.IUserRepository;
import user.repository.UserRepository;

import java.sql.SQLException;
import java.util.Arrays;

public class UserTest {
    private User user;
    private IUserRepository userRepository;
    @Before
    public void userSetup() throws SQLException {
        userRepository = new UserRepository();
        user = new User(0,"Martin", "Doychev","email@gmail.com","+123467890", "neznam", Role.READER, userRepository);
    }
    @Test
    public void testGetUserID() throws SQLException {
        Assert.assertEquals(11, User.getUserIDFromDB(user));
    }
    @Test
    public void testInsertUser() {
        user.insertUser();
    }
    @Test
    public void testInsertIntoLibrary() throws SQLException {
        user.insertIntoLibrary("test");
    }
    @Test
    public void testInsertUserLibrary() throws SQLException{
        user.insertUserLibrary(37,77);
    }
    @Test
    public void testInsertCredentials () throws SQLException{
        user.insertCredentials();
    }
}