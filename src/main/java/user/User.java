package user;

import enums.Role;
import user.repository.IUserRepository;

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

    //голяма тройка - default, with parameters and copy constr
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

    public int getUserID() {
        return userID;
    }

    public IUserRepository getUserRepository() {
        return userRepository;
    }
}
