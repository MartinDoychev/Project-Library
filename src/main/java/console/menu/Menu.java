package console.menu;

import user.User;
import user.repository.UserRepository;

import java.sql.SQLException;
import java.util.Scanner;

public class Menu {
    public void logInMenu() {
        System.out.println("    )                                    ");
        System.out.println(" ( /(                               )    ");
        System.out.println(" )\\())     (  (  (  (      ) (   ( /(    ");
        System.out.println("((_)\\  (   )\\))( )\\))(  ( /( )(  )\\()|   ");
        System.out.println(" _((_) )\\ ((_))\\((_)()\\ )(_)|()\\(_))/)\\  ");
        System.out.println("| || |((_) (()(_)(()((_|(_)_ ((_) |_((_) ");
        System.out.println("| __ / _ \\/ _` |\\ V  V / _` | '_|  _(_-< ");
        System.out.println("|_||_\\___/\\__, | \\_/\\_/\\__,_|_|  \\__/__/ ");
        System.out.println("          |___/                          ");
        System.out.println("|                                       |");
        System.out.println("|          L  I  B  R  A  R  Y          |");
        System.out.println("*****************************************");
        System.out.println("|                                       |");
        System.out.println("|                 Log in                |");
        System.out.println("|             Create Account            |");
        System.out.println("|                                       |");
        System.out.println("*****************************************");
        System.out.println("|                                       |");
    }




//    public User readCredentials() throws SQLException {
//        Scanner scan = new Scanner(System.in);
//        printLogInHeader();
//        System.out.println();
//
//        System.out.printf("  User name: ");
//        String userName = scan.nextLine();
//
//        String password = PasswordField.readPassword("  Password:");;
//        System.out.println("The password entered is: "+password);
//
////        String userPass = convertCharArrayToString(password);
//        printLogInFooter();

//        if (User.checkCredentials(userName, userPass)){
//            System.out.println("Success");
//        } else {
//            System.out.println("Log in failed");
//        }
//        while (!User.checkCredentials(userName, pass)) {
//            printLogInHeader();
//            System.out.println();
//            userName = console.readLine("  User name: ");
//            pass = console.readPassword("  Password: ");
//            printLogInFooter();
//        }

//        return new User();
//    }


    // Using class Console and then Terminal
//    public User readCredentials() throws SQLException {
//        Console console = System.console();
//        printLogInHeader();
//        System.out.println();
//        String userName = console.readLine("  User name: ");
//        char[] pass = console.readPassword("  Password: ");
//        String userPass = convertCharArrayToString(pass);
//        printLogInFooter();
//
//        if (User.checkCredentials(userName, userPass)){
//            System.out.println("Success");
//        } else {
//            System.out.println("Log in failed");
//        }
////        while (!User.checkCredentials(userName, pass)) {
////            printLogInHeader();
////            System.out.println();
////            userName = console.readLine("  User name: ");
////            pass = console.readPassword("  Password: ");
////            printLogInFooter();
////        }
//
//        return new User();
//    }


    public User readCredentials() throws SQLException {
        Scanner scan = new Scanner(System.in);

        printLogInHeader();
        System.out.println();
        System.out.print("  UserName: ");
        String userName = scan.nextLine();
        System.out.print("  Password: ");
        String pass = scan.nextLine();
        System.out.println();
//        String userPass = convertCharArrayToString(pass);
        printLogInFooter();

        while (!User.checkCredentials(userName, pass)) {
            printLogInHeader();
            System.out.println();
            System.out.println("  Invalid userName or password!\n  Please try again!");
            System.out.print("  UserName: ");
            userName = scan.nextLine();
            System.out.print("  Password: ");
            pass = scan.nextLine();
            System.out.println();
        }

        return User.getUserFromDB(userName, UserRepository.encryptPassword(pass));
    }

    private String convertCharArrayToString(char[] pass) {
        return new String(pass);
    }

    public void printLogInHeader() {
        System.out.println("|                                       |");
        System.out.println("|               L O G I N               |");
        System.out.println("*****************************************");
        System.out.println("|                                       |");
    }


    public void printLogInFooter() {
        System.out.println("|                                       |");
        System.out.println("*****************************************");
        System.out.println("|                                       |");
    }

    public void printReaderMenu() {
        System.out.println("|                                       |");
        System.out.println("|              R E A D E R              |");
        System.out.println("*****************************************");
        System.out.println("|                                       |");
        System.out.println("| 1. Search book                        |");
        System.out.println("| 2. Show last read                     |");
        System.out.println("| 3. Sort library                       |");
        System.out.println("| 4. Rate book                          |");
        System.out.println("| 5. Exit                               |");
        System.out.println("|                                       |");
        System.out.println("*****************************************");
        System.out.println("|                                       |");
    }

    public void printAuthorMenu() {
        System.out.println("|                                       |");
        System.out.println("|              A U T H O R              |");
        System.out.println("*****************************************");
        System.out.println("|                                       |");
        System.out.println("| 1. Upload book                        |");
        System.out.println("| 2. Show library                       |");
        System.out.println("| 3. Exit                               |");
        System.out.println("|                                       |");
        System.out.println("*****************************************");
        System.out.println("|                                       |");
    }

    public void printAdminMenu() {
        System.out.println("|                                       |");
        System.out.println("|               A D M I N               |");
        System.out.println("*****************************************");
        System.out.println("|                                       |");
        System.out.println("| 1. Lock account                       |");
        System.out.println("| 2. Unlock account                     |");
        System.out.println("| 3. Exit                               |");
        System.out.println("|                                       |");
        System.out.println("*****************************************");
        System.out.println("|                                       |");
    }

    public void printReaderSearchBookMenu() {
        System.out.println("|                                       |");
        System.out.println("|            SEARCH BOOK BY             |");
        System.out.println("*****************************************");
        System.out.println("|                                       |");
        System.out.println("| 1. Name                               |");
        System.out.println("| 2. Author                             |");
        System.out.println("| 3. Exit                               |");
        System.out.println("|                                       |");
        System.out.println("*****************************************");
        System.out.println("|                                       |");
    }

    public void printReaderSortLibraryMenu() {
        System.out.println("|                                       |");
        System.out.println("|            SORT LIBRARY BY            |");
        System.out.println("*****************************************");
        System.out.println("|                                       |");
        System.out.println("| 1. Author                             |");
        System.out.println("| 2. Title                              |");
        System.out.println("| 3. Genre                              |");
        System.out.println("| 4. Exit                               |");
        System.out.println("|                                       |");
        System.out.println("*****************************************");
        System.out.println("|                                       |");
    }

    public void printAddBookDetailsHeader() {
        System.out.println("|                                       |");
        System.out.println("|            ADD BOOK DETAILS           |");
        System.out.println("*****************************************");
        System.out.println("|                                       |");
    }

    public void printAccessOptions() {
        System.out.println("|                                       |");
        System.out.println("|             ACCESS OPTIONS            |");
        System.out.println("*****************************************");
        System.out.println("|                                       |");
        System.out.println("| Available                             |");
        System.out.println("| Staged                                |");
        System.out.println("| Announced                             |");
        System.out.println("|                                       |");
        System.out.println("*****************************************");
        System.out.println("|                                       |");
    }

    public void printAddBookDetailsFooter() {
        System.out.println("|                                       |");
        System.out.println("*****************************************");
        System.out.println("|                                       |");
    }

    public static void printAuthorLibraryHeader() {
        System.out.printf("| %-40s | %-40s | %-30s | %-20s | %10s | %10s | %10s |\n", "TITLE", "AUTHOR", "GENRE", "LANGUAGE", "RATING", "ACCESS", "#LISTING");
        System.out.println("**************************************************************************************************************************************************************************************");
    }

    public static void printAuthorLibraryFooter() {
        System.out.println("**************************************************************************************************************************************************************************************");
        System.out.println("|                                                                                                                                                                                    |");
    }

    public static void printReaderLibraryHeader() {
        System.out.printf("| %-40s | %-40s | %-30s | %-20s | %10s |\n", "TITLE", "AUTHOR", "GENRE", "LANGUAGE", "RATING");
        System.out.println("************************************************************************************************************************************************************");
    }

    public static void printReaderLibraryFooter() {
        System.out.println("************************************************************************************************************************************************************");
        System.out.println("|                                                                                                                                                          |");
    }

    public static void printReaderLastRead() {
        System.out.println("|                                                                    LAST READ                                                                             |");
        System.out.println("************************************************************************************************************************************************************");
        System.out.println("|                                                                                                                                                          |");
    }

    public static void printSortedBy(String sortedBy) {
        System.out.print("|                                                                  SORTED BY ");
        System.out.print(sortedBy + " ".repeat(78 - sortedBy.length()) + "|\n");
        System.out.println("************************************************************************************************************************************************************");
        System.out.println("|                                                                                                                                                          |");
    }
}
