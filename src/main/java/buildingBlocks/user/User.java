package buildingBlocks.user;

import java.util.Scanner;

public class User {
    private String firstName, lastName, email, userName, password;
    private Address address;
    private long phoneNumber;
    private Role role;
//    private IUserRepository userRepository;
    public enum Role {
        READER,
        AUTHOR,
        ADMIN
    }

    //голяма тройка - default, with parameters and copy constr
    public User() {
        this("", "", "", new Address(), 0,"", Role.READER);
    }

    public User(String fn, String ln, String mail, Address address1, long pn, String password, Role role) {
        this.firstName = fn;
        this.lastName = ln;
        this.email = mail;
        this.address = address1;
        this.phoneNumber = pn;
        this.userName = (this.firstName != null ? this.firstName : "") + ' ' + (this.lastName != null ? this.lastName : "");
        this.password = password;
        this.role = role;
    }

    public User(User other) {
        this.firstName = other.firstName;
        this.lastName = other.lastName;
        this.email = other.email;
        this.address = other.address;
        this.phoneNumber = other.phoneNumber;
        this.role = other.role;
    }

    private String capitalLetter(String str) {
        //Проверява се дали низът str е null или празен. Ако е така, се извежда съобщение за грешка и се връща същият низ без промяна.
        if (str == null || str.isEmpty()) {
            System.out.println(" ------ error [empty string] ------\n");
            return str;
        }

        //Взема се първият символ от низа str и се капитализира с помощта на метода Character.toUpperCase(str.charAt(0)).
        char firstChar = Character.toUpperCase(str.charAt(0));

        //Създава се обект от клас StringBuilder с първата капитализирана буква.
        StringBuilder result = new StringBuilder().append(firstChar);
        //Използва се променлива makeNextCapital, която се използва за определяне дали следващата буква трябва да бъде капитализирана.
        //По подразбиране тя е зададена на false.
        boolean makeNextCapital = false;

        //Следва цикъл, който минава през всички символи на низа str, започвайки от втория символ (i = 1). За всеки символ се прави следното:
        for (int i = 1; i < str.length(); i++) {
            char currentChar = str.charAt(i);

            //Ако символът е буква, се проверява дали трябва да бъде капитализиран. Ако makeNextCapital е true, това означава, че предишният символ е бил интервал, и следващият символ трябва да бъде голяма буква. В този случай текущата буква се капитализира чрез Character.toUpperCase(currentChar),
            //след което makeNextCapital се задава на false, за да не се повтори капитализацията на следващата буква.
            if (Character.isLetter(currentChar)) {
                if (makeNextCapital) {
                    result.append(Character.toUpperCase(currentChar));
                    makeNextCapital = false;
                }

                //Ако makeNextCapital е false, това означава, че не трябва да се капитализира текущата буква, така че тя се добавя към резултатния низ като малка буква,
                //използвайки Character.toLowerCase(currentChar).
                else
                    result.append(Character.toLowerCase(currentChar));

            }

            //Ако символът не е буква, се проверява дали е интервал. Ако е така, следващата буква трябва да бъде капитализирана,
            //затова makeNextCapital се задава на true.
            else if (currentChar == ' ')
                makeNextCapital = true;
        }

        //връща крайният резултат като низ, извлечен от StringBuilder.
        return result.toString();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getUserName() {
        return userName;
    }

    public Address getAddress() {
        return address;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public Role getRole() {
        return role;
    }

    public User createAccount(Scanner scanner) {
        System.out.println("Please, insert the following information:");
        System.out.print("-> First Name: ");
        String name = scanner.nextLine();

        System.out.print("-> Last Name: ");
        String famName = scanner.nextLine();

        System.out.print("-> Email: ");
        String mail = scanner.nextLine();

        System.out.print("-> Phone Number: ");
        long num = Long.parseLong(scanner.nextLine());

        //да го изведа в отделен метод и да направя валидации
        System.out.println("Address Information:");
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

        Address address = new Address();
        address.setStreet(street);
        address.setCity(city);
        address.setState(state);
        address.setCountry(country);
        address.setPostalCode(postalCode);

        System.out.print("-> Password: ");
        String pass = scanner.next();
        //да си го изведа в отделен метод и while цикъл да проверява паролата

        System.out.println("Select the role:");
        System.out.println("1. Reader");
        System.out.println("2. Author");
        System.out.println("3. Admin");
        System.out.print("Your choice: ");
        int choice = Integer.parseInt(scanner.nextLine());
        switch (choice) {
            case 1:
                this.role = Role.READER;
                break;
            case 2:
                this.role = Role.AUTHOR;
                break;
            case 3:
                this.role = Role.ADMIN;
                break;
            default:
                System.out.println("Invalid choice. Setting role to Reader.");
                this.role = Role.READER;
                break;
        }
        User user = new User(name, famName,mail,address, num,pass,role);
        //insertUser(user);
        return user;
    }

//    public boolean insertUser(User user){
//        if(user.existsInDB()){
//            return false;
//        }
//        else{
//            user.insertIntoUser();
//            user.insertIntoCredentials();
//            return true;
//        }
//    }
    public void printUserData() {
        System.out.println("User Information:");
        System.out.println("First Name: " + firstName);
        System.out.println("Last Name: " + lastName);
        System.out.println("Email: " + email);
        System.out.println("User Name: " + userName);
        System.out.println("Phone Number: " + phoneNumber);
        System.out.println("Role: " + role);
        System.out.println("Address: " + address.getStreet() + ", " + address.getCity() + ", " + address.getState() + ", " + address.getCountry() + ", " + address.getPostalCode());
    }
}
