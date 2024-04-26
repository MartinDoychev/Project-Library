package user;
import user.address.Address;
import user.repository.IUserRepository;
import enums.Role;
import user.repository.UserRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class User {
    private int userID;
    private String firstName, lastName, email, userName, password;
    private String phoneNumber;
    private Role role;
    private IUserRepository userRepository;


    public User() {
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
    public int getUserID() { return userID; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getPassword() { return password; }
    public Role getRole() { return role; }

    private static String capitalLetter(String str) {
        if (str == null || str.isEmpty()) {
            System.out.println(" ------ error [empty string] ------\n");
            return str;
        }
        char firstChar = Character.toUpperCase(str.charAt(0));
        StringBuilder result = new StringBuilder().append(firstChar);
        boolean makeNextCapital = false;
        for (int i = 1; i < str.length(); i++) {
            char currentChar = str.charAt(i);
            if (Character.isLetter(currentChar)) {
                if (makeNextCapital) {
                    result.append(Character.toUpperCase(currentChar));
                    makeNextCapital = false;
                } else {
                    result.append(Character.toLowerCase(currentChar));
                }
            } else if (currentChar == ' ') {
                makeNextCapital = true;
            }
        }
        return result.toString();
    }

    private static boolean isValidName(String name) {
        return name != null && !name.isEmpty() && name.matches("[A-Za-z]+");
    }

    private static String initializeName(Scanner scanner) {
        System.out.print("-> Enter Name: ");
        String name = scanner.nextLine();
        while (!isValidName(name)) {
            System.out.print("Invalid name, please enter again: ");
            name = scanner.nextLine();
        }
        return capitalLetter(name);
    }

    private static boolean isValidAddress(String street, String city, String state, String country, short postalCode) {
        return !(street.isEmpty() || city.isEmpty() || state.isEmpty() || country.isEmpty() || postalCode <= 0);
    }

    private static Address initializeAddress(Scanner scanner) {
        System.out.print("-> Street: ");
        String street = scanner.nextLine();
        System.out.print("-> City: ");
        String city = scanner.nextLine();
        System.out.print("-> State: ");
        String state = scanner.nextLine();
        System.out.print("-> Country: ");
        String country = scanner.nextLine();
        System.out.print("-> Postal Code: ");
        short postalCode = Short.parseShort(scanner.nextLine());

        while (!isValidAddress(street, city, state, country, postalCode)) {
            System.out.println("Invalid address, please enter again:");
            street = scanner.nextLine();
            city = scanner.nextLine();
            state = scanner.nextLine();
            country = scanner.nextLine();
            postalCode = Short.parseShort(scanner.nextLine());
        }

        return new Address(street, city, state, country, postalCode);
    }

    private static boolean isValidPassword(String password) {
        return password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,}$");
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    private static String initializePassword(Scanner scanner) {
        System.out.print("-> Password: ");
        String password = scanner.next();
        scanner.nextLine();
        while (!isValidPassword(password)) {
            System.out.print("Invalid password, please enter again: ");
            password = scanner.next();
            scanner.nextLine();
        }
        return password;
    }

    public static User createAccount(Scanner scanner) throws SQLException {
        User user = new User();
        System.out.println("Please, insert the following information:");
        String firstName = initializeName(scanner);
        String lastName = initializeName(scanner);
        System.out.print("-> Email: ");
        String email = scanner.nextLine();
        System.out.print("-> Phone Number: ");
        String phoneNumber = scanner.nextLine();
        String password = initializePassword(scanner);

        System.out.println("Select the role:");
        System.out.println("1. Reader");
        System.out.println("2. Author");
        System.out.println("3. Admin");
        System.out.print("Your choice: ");
        int choice = Integer.parseInt(scanner.nextLine());

        Role role;
        switch (choice) {
            case 1:
                role = Role.READER;
                break;
            case 2:
                role = Role.AUTHOR;
                break;
            case 3:
                role = Role.ADMIN;
                break;
            default:
                System.out.println("Invalid choice. Setting role to Reader.");
                role = Role.READER;
                break;
        }

        IUserRepository userRepository = new UserRepository();
        user = new User(0, firstName, lastName, email, phoneNumber, password, role, userRepository);
        int userID = getUserIDFromDB(user);
        if(!user.userRepository.userExistsInGeneralDB(userID)) {
            user.insertUser();
            user.insertCredentials();
            user.insertIntoLibrary(user.getFirstName() + user.getLastName());
            user.insertUserLibrary(getUserIDFromDB(user), user.getLibraryIDFromDB(user.getFirstName() + user.getLastName()));
        }
        return user;
    }

    public boolean userExistsInGeneralDB(int userID) {
        int userCount = 0;
        try {
            String selectQuery = "SELECT count(*) FROM user WHERE UserID = ?";
            PreparedStatement selectStatement = userRepository.getConnection().prepareStatement(selectQuery);
            selectStatement.setInt(1, userID);
            ResultSet resultSet = selectStatement.executeQuery();
            if (resultSet.next()) {
                userCount = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            System.out.printf("Error message: %s, cause: %s%n", e.getMessage(), e.getCause());
        }
        return userCount == 1;
    }

    public boolean insertUser() {
        if (userExistsInGeneralDB(getUserID())) {
            System.out.println("User already exists.");
            return false;
        }
            try {
                String insertQuery = "INSERT INTO user (FirstName, LastName, Email, PhoneNumber, isLocked) VALUES ( ?, ?, ?, ?, ?)";
            PreparedStatement insertStatement = userRepository.getConnection().prepareStatement(insertQuery);
            insertStatement.setString(1, getFirstName());
            insertStatement.setString(2, getLastName());
            insertStatement.setString(3, getEmail());
            insertStatement.setString(4, getPhoneNumber());
            insertStatement.setString(5, "0");
            insertStatement.executeUpdate();
            setUserID(getUserIDFromDB(this));

            String insertQueryRole = "INSERT INTO academy.userRole (UserID, UserRoleID) VALUES(?, ?)" ;
            PreparedStatement insertStatementRole = userRepository.getConnection().prepareStatement(insertQueryRole);
            insertStatementRole.setString(1, String.valueOf(getUserID()));
            insertStatementRole.setString(2, String.valueOf(getUserRole(getRole())));
            insertStatementRole.executeUpdate();

            //insertIntoLibrary (s Substring да си направя къстом нейм)
            //изпълнявам getLibraryIDFROMDB

            int rowsAffected = insertStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("User successfully inserted.");
                return true;
            }
        } catch (SQLException e) {
            System.out.printf("Error message: %s, cause: %s%n", e.getMessage(), e.getCause());
        }
        return false;
    }
    public void insertCredentials() throws SQLException {
        String insertQuery = "INSERT INTO credentials (UserID, Username, Password) VALUES (?, ?, ?)";
        try (PreparedStatement insertStatement = userRepository.getConnection().prepareStatement(insertQuery)) {
            insertStatement.setInt(1, getUserIDFromDB(this));
            insertStatement.setString(2, getFirstName() + getLastName());
            insertStatement.setString(3, UserRepository.encryptPassword(getPassword()));
            insertStatement.executeUpdate();
        }
        catch (SQLException e) {
            System.out.printf("Error message: %s, cause: %s%n", e.getMessage(), e.getCause());
            throw e;
        }
    }
    public void insertIntoLibrary(String libraryName) throws SQLException {
            String insertLibrary = "INSERT INTO library (LibraryName) VALUES (?)";
            try {
                PreparedStatement insertStatement = userRepository.getConnection().prepareStatement(insertLibrary);
                insertStatement.setString(1, libraryName);
                insertStatement.executeUpdate();
            }
            catch (SQLException e) {
                System.out.printf("Error message: %s, cause: %s%n", e.getMessage(), e.getCause());
                throw e;
            }

    }
    public void insertUserLibrary(int userID, int libraryID) throws SQLException {
        String insertQuery = "INSERT INTO academy.userLibrary (LibraryID, UserID) VALUES (?, ?)";
        try (PreparedStatement insertStatement = userRepository.getConnection().prepareStatement(insertQuery)) {
            insertStatement.setInt(1, libraryID);
            insertStatement.setInt(2, userID);
            insertStatement.executeUpdate();
        }
        catch (SQLException e) {
            System.out.printf("Error message: %s, cause: %s%n", e.getMessage(), e.getCause());
            throw e;
        }
    }

    public static int getUserIDFromDB(User user) {
        int ID = 0;
        try {
            String selectQuery = "select userID from user u where u.FirstName = ? and u.LastName = ? and u.email = ?";
            PreparedStatement selectStatement = user.userRepository.getConnection().prepareStatement(selectQuery);
            selectStatement.setString(1, user.getFirstName());
            selectStatement.setString(2, user.getLastName());
            selectStatement.setString(3, user.getEmail());

            ResultSet resultSet = selectStatement.executeQuery();
            if (resultSet.next()) {
                ID = resultSet.getInt("userID");
            }
        }
        catch (SQLException e) {
            System.out.printf("Error message: %s, cause: %s%n", e.getMessage(), e.getCause());
        }
        return ID;

    }
    public int getLibraryIDFromDB(String LibraryName) {
        int ID = 0;
        try {
            String selectQuery = "select LibraryID from library l where l.LibraryName = ?";
            PreparedStatement selectStatement = userRepository.getConnection().prepareStatement(selectQuery);
            selectStatement.setString(1, LibraryName);

            ResultSet resultSet = selectStatement.executeQuery();
            if (resultSet.next()) {
                ID = resultSet.getInt("LibraryID");
            }
        }
        catch (SQLException e) {
            System.out.printf("Error message: %s, cause: %s%n", e.getMessage(), e.getCause());
        }
        return ID;
    }

    private int getUserRole(Role role) {
        switch (role){
            case READER:
                return 1;
            case AUTHOR:
                return 2;
            case ADMIN:
                return 3;
        }
        return 0;
    }
    //трябва да попълня 2.Library (Name) , 1.Credentials (UserID,Username, password - UserRepository.encryptPassword(user.getPassword()), UserLibrary (INSERT INTO academy.userLibrary
    //(LibraryID, UserID)
    //VALUES(?, ?,);)

    public void printUserData() {
        System.out.println("User Information:");
        System.out.println("First Name: " + firstName);
        System.out.println("Last Name: " + lastName);
        System.out.println("Email: " + email);
        System.out.println("User Name: " + userName);
        System.out.println("Phone Number: " + phoneNumber);
        System.out.println("Role: " + role);
    }
}