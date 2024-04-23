package user;

import dbConnection.DBConnectionManager;
import enums.Role;
import library.Library;
import user.repository.IUserRepository;
import user.repository.UserRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
    private int userID;
    private String firstName;
    private String lastName;
    private String email;
    private String userName;
    private String password;
    private String phoneNumber;
    private Role role;
    private IUserRepository userRepository;

    public User() {
//        this.userRepository = new UserRepository();
    }

    public User(int userID, String fn, String ln, String mail, String pn, String password, Role role, IUserRepository userRepository) {
        this.userID = userID;
        this.firstName = fn;
        this.lastName = ln;
        this.email = mail;
        this.phoneNumber = pn;
        this.userName = (this.firstName != null ? this.firstName : "") + ' ' + (this.lastName != null ? this.lastName : "");
        this.password = password;
        this.role = role;
        this.userRepository = userRepository;
    }

    public User(int userID, String fn, String ln, String mail, String pn, String userName, String password, Role role, IUserRepository userRepository) {
        this.userID = userID;
        this.firstName = fn;
        this.lastName = ln;
        this.email = mail;
        this.phoneNumber = pn;
        this.userName = userName;
        this.password = password;
        this.role = role;
        this.userRepository = userRepository;
    }

    public User(User other) {
        this.userID = other.userID;
        this.firstName = other.firstName;
        this.lastName = other.lastName;
        this.email = other.email;
        this.phoneNumber = other.phoneNumber;
        this.role = other.role;
        this.userRepository = other.userRepository;

        this.userName = other.userName;
        this.password = other.password;
    }

    public User(int userID, Role role, IUserRepository userRepository) {
        this.userID = userID;
        this.firstName = "";
        this.lastName = "";
        this.email = "";
        this.phoneNumber = "";
        this.userName = "";
        this.password = "";
        this.role = role;
        this.userRepository = userRepository;
    }

    public static User getUserFromDB(String userName, String encPassword) throws SQLException {
        int userID = getUserIDFromDB(userName);
        String firstName = "";
        String lastName = "";
        String email = "";
        String phoneNumber = "";
        Role role = getRoleFromDB(userID);

        IUserRepository userRepository = new UserRepository();

        try {
            String selectQuery = "select * from user where UserID = ?";
            PreparedStatement selectStatement = userRepository.getConnection().prepareStatement(selectQuery);
            selectStatement.setString(1, String.valueOf(userID));
            ResultSet resultSet = selectStatement.executeQuery();

            if (!resultSet.next()) {
                return new User();
            } else {
                firstName = resultSet.getString("FirstName");
                lastName = resultSet.getString("LastName");
                email = resultSet.getString("email");
                phoneNumber = resultSet.getString("phoneNumber");
            }

        } catch (SQLException e) {
            System.out.printf("Error message: %s, cause: %s%n", e.getMessage(), e.getCause());
        }

        return new User(userID, firstName, lastName, email, phoneNumber, userName, encPassword, role, userRepository);

    }

    public static Role getRoleFromDB(int userID) throws SQLException {
        int roleID = 0;
        IUserRepository userRepository = new UserRepository();

        try {
            String selectQuery = "select userRoleID from userRole ur where UserID = ?";
            PreparedStatement selectStatement = userRepository.getConnection().prepareStatement(selectQuery);
            selectStatement.setString(1, String.valueOf(userID));
            ResultSet resultSet = selectStatement.executeQuery();

            if (resultSet.next()) {
                roleID = resultSet.getInt("userRoleID");
            }
        } catch (SQLException e) {
            System.out.printf("Error message: %s, cause: %s%n", e.getMessage(), e.getCause());
        }

        return getRole(roleID);
    }


    public static Role getRole(int roleID) {
        Role role = Role.READER;

        switch (roleID) {
            case 1:
                break;
            case 2:
                role = Role.AUTHOR;
                break;
            case 3:
                role = Role.ADMIN;
                break;
        }

        return role;
    }

    public static int getUserIDFromDB(String userName) throws SQLException {
        int userID = -1;
        IUserRepository userRepository = new UserRepository();

        try {
            String selectQuery = "SELECT userID from credentials c where username = ?";
            PreparedStatement selectStatement = userRepository.getConnection().prepareStatement(selectQuery);
            selectStatement.setString(1, userName);
            ResultSet resultSet = selectStatement.executeQuery();

            if (!resultSet.next()) {
                return -1;
            } else {
                userID = resultSet.getInt("userID");
            }
        } catch (SQLException e) {
            System.out.printf("Error message: %s, cause: %s%n", e.getMessage(), e.getCause());
        }

        return userID;
    }

    public int getUserID() {
        return userID;
    }

    public IUserRepository getUserRepository() {
        return userRepository;
    }

    public static boolean checkCredentials(String userName, String pass) {
        DBConnectionManager connection = DBConnectionManager.getInstance();
        String encryptedPassword = UserRepository.encryptPassword(pass);
        String dbPassword = null;
        boolean result = false;

        try {
            String selectQuery = "select password from credentials c where username = ? ";
            PreparedStatement selectStatement = connection.getConnection().prepareStatement(selectQuery);
            selectStatement.setString(1, userName);
            ResultSet resultSet = selectStatement.executeQuery();

            if (!resultSet.next()) {
                return false;
            } else {
                dbPassword = resultSet.getString("password");
            }

            result = encryptedPassword.equals(dbPassword);

        } catch (SQLException e) {
            System.out.printf("Error message: %s, cause: %s%n", e.getMessage(), e.getCause());
        }

        return result;
    }

    public String toString() {
        StringBuilder result = new StringBuilder();

        result.append("| ")
                .append(String.format("%-10d | %-20s | %-15s | %-15s | %-20s | %-30s | %-10s |"
                        , userID, firstName.concat(" " + lastName), email, phoneNumber, userName, password, role));

        return result.toString();
    }

    public Role getRole() {
        return this.role;
    }

    protected Library getUserLibrary() throws SQLException {
        return userRepository.getUserLibrary(userID);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}


