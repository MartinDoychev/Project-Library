package book;

import dbConnection.DBConnectionManager;
import enums.BookAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookRepository implements IBookRepository {
    private final Connection connection;

    public BookRepository() throws SQLException {
        DBConnectionManager instance = DBConnectionManager.getInstance();
        this.connection = instance.getConnection();
    }

    @Override
    public ArrayList<Book> searchBookByName(String bookName) {
        ArrayList<Book> result = new ArrayList<>();
        int bookID;
        String title;
        String author;
        String ISBN;
        String genre;
        String language;
        double rating;
        BookAccess access;

        try {
            String selectQuery = "SELECT * FROM book where Title like ?";
            PreparedStatement selectStatement = this.connection.prepareStatement(selectQuery);
            selectStatement.setString(1, bookName);
            ResultSet resultSet = selectStatement.executeQuery();

            while (resultSet.next()) {
                bookID = resultSet.getInt("BookID");
                title = resultSet.getString("Title");
                author = getAuthor(resultSet.getInt("AuthorID"));
                ISBN = resultSet.getString("ISBN");
                genre = getGenre(resultSet.getInt("GenreID"));
                language = getLanguage(resultSet.getInt("LanguageID"));
                rating = resultSet.getDouble("rating");
                access = getBookAccess(resultSet.getInt("AccessID"));

                Book book = new Book(bookID, title, author, ISBN, genre, language, rating, access, false);
                result.add(book);
            }

        } catch (SQLException e) {
            System.out.printf("Error message: %s, cause: %s%n", e.getMessage(), e.getCause());
        }
        return result;
    }

    @Override
    public ArrayList<Book> searchBookByAuthor(String authorName) {
        ArrayList<Book> result = new ArrayList<>();
        int bookID;
        String title;
        String author;
        String ISBN;
        String genre;
        String language;
        double rating;
        BookAccess access;

        try {
            String selectQuery = "select * from book b where b.AuthorID = ?";
            PreparedStatement selectStatement = this.connection.prepareStatement(selectQuery);
            int authorID = getAuthorId(authorName);
            selectStatement.setString(1, String.valueOf(authorID));
            ResultSet resultSet = selectStatement.executeQuery();

            while (resultSet.next()) {
                bookID = resultSet.getInt("BookID");
                title = resultSet.getString("Title");
                author = getAuthor(resultSet.getInt("AuthorID"));
                ISBN = resultSet.getString("ISBN");
                genre = getGenre(resultSet.getInt("GenreID"));
                language = getLanguage(resultSet.getInt("LanguageID"));
                rating = resultSet.getDouble("rating");
                access = getBookAccess(resultSet.getInt("AccessID"));

                Book book = new Book(bookID, title, author, ISBN, genre, language, rating, access, false);
                result.add(book);
            }

        } catch (SQLException e) {
            System.out.printf("Error message: %s, cause: %s%n", e.getMessage(), e.getCause());
        }
        return result;
    }

    private BookAccess getBookAccess(int access) {
        BookAccess bookAccess = BookAccess.AVAILABLE;
        switch (access) {
            case 2:
                bookAccess = BookAccess.STAGED;
                break;
            case 3:
                bookAccess = BookAccess.ANNOUNCED;
                break;
        }
        return bookAccess;
    }

    @Override
    public String getAuthor(int authorID) {
        String authorName = "";

        try {
            String selectQuery = "select u.FirstName , u.LastName from user u join author a on u.UserID = a.UserID where a.authorID = ?";
            PreparedStatement selectStatement = this.connection.prepareStatement(selectQuery);
            selectStatement.setString(1, String.valueOf(authorID));
            ResultSet resultSet = selectStatement.executeQuery();

            if (resultSet.next()) {
                authorName = resultSet.getString("FirstName") + " " + resultSet.getString("LastName");
            }
        } catch (SQLException e) {
            System.out.printf("Error message: %s, cause: %s%n", e.getMessage(), e.getCause());
        }

        return authorName;
    }

    @Override
    public int getAuthorId(String authorName) {
        int authorID = 0;

        try {
            String selectQuery = "select a.authorID  from author a join user u on a.UserID = u.UserID where CONCAT(u.FirstName, \" \", u.LastName)  like ?";
            PreparedStatement selectStatement = this.connection.prepareStatement(selectQuery);
            selectStatement.setString(1, authorName);
            ResultSet resultSet = selectStatement.executeQuery();

            if (resultSet.next()) {
                authorID = resultSet.getInt("authorID");
            }
        } catch (SQLException e) {
            System.out.printf("Error message: %s, cause: %s%n", e.getMessage(), e.getCause());
        }

        return authorID;
    }

    @Override
    public String getGenre(int genreID) {
        String genreName = "";

        try {
            String selectQuery = "select g.genreName from genre g where genreID = ?";
            PreparedStatement selectStatement = this.connection.prepareStatement(selectQuery);
            selectStatement.setString(1, String.valueOf(genreID));
            ResultSet resultSet = selectStatement.executeQuery();

            if (resultSet.next()) {
                genreName = resultSet.getString("genreName");
            }
        } catch (SQLException e) {
            System.out.printf("Error message: %s, cause: %s%n", e.getMessage(), e.getCause());
        }

        return genreName;
    }

    @Override
    public String getLanguage(int languageID) {
        String languageName = "";

        try {
            String selectQuery = "select l.languageName from language l where l.languageID = ?";
            PreparedStatement selectStatement = this.connection.prepareStatement(selectQuery);
            selectStatement.setString(1, String.valueOf(languageID));
            ResultSet resultSet = selectStatement.executeQuery();

            if (resultSet.next()) {
                languageName = resultSet.getString("languageName");
            }
        } catch (SQLException e) {
            System.out.printf("Error message: %s, cause: %s%n", e.getMessage(), e.getCause());
        }

        return languageName;
    }

    @Override
    public void addBook() {

    }

    @Override
    public boolean bookExistsInGeneralLibrary(String bookName) {
        int bookCount = 0;

        try {
            String selectQuery = "select count(*) from book b where Title like ?";
            PreparedStatement selectStatement = this.connection.prepareStatement(selectQuery);
            selectStatement.setString(1, String.valueOf(bookName));
            ResultSet resultSet = selectStatement.executeQuery();

            if (resultSet.next()) {
                bookCount = resultSet.getInt("count(*)");
            }
        } catch (SQLException e) {
            System.out.printf("Error message: %s, cause: %s%n", e.getMessage(), e.getCause());
        }

        return bookCount > 0;
    }

    public String getBookNameByBookID(int bookID) {
        String bookName = "";

        try {
            String selectQuery = "select Title from book b where BookID = ?";
            PreparedStatement selectStatement = this.connection.prepareStatement(selectQuery);
            selectStatement.setString(1, String.valueOf(bookID));
            ResultSet resultSet = selectStatement.executeQuery();

            if (resultSet.next()) {
                bookName = resultSet.getString("Title");
            }
        } catch (SQLException e) {
            System.out.printf("Error message: %s, cause: %s%n", e.getMessage(), e.getCause());
        }

        return bookName;
    }

    public String getAuthorByBookID(int bookID) {
        String authorName = "";

        try {
            String selectQuery = "select AuthorID from book b where BookID = ?";
            PreparedStatement selectStatement = this.connection.prepareStatement(selectQuery);
            selectStatement.setString(1, String.valueOf(bookID));
            ResultSet resultSet = selectStatement.executeQuery();

            if (resultSet.next()) {
               int authorID = resultSet.getInt("AuthorID");
               authorName = getAuthor(authorID);
            }
        } catch (SQLException e) {
            System.out.printf("Error message: %s, cause: %s%n", e.getMessage(), e.getCause());
        }

        return authorName;
    }

    public String getISBNByBookID(int bookID) {
        String bookISBN = "";

        try {
            String selectQuery = "select ISBN from book b where BookID = ?";
            PreparedStatement selectStatement = this.connection.prepareStatement(selectQuery);
            selectStatement.setString(1, String.valueOf(bookID));
            ResultSet resultSet = selectStatement.executeQuery();

            if (resultSet.next()) {
                bookISBN = resultSet.getString("ISBN");
            }
        } catch (SQLException e) {
            System.out.printf("Error message: %s, cause: %s%n", e.getMessage(), e.getCause());
        }

        return bookISBN;
    }

    public String getGenreByBookID(int bookID) {
        String bookGenre = "";

        try {
            String selectQuery = "select GenreID from book b where BookID = ?";
            PreparedStatement selectStatement = this.connection.prepareStatement(selectQuery);
            selectStatement.setString(1, String.valueOf(bookID));
            ResultSet resultSet = selectStatement.executeQuery();

            if (resultSet.next()) {
                int genreID = resultSet.getInt("GenreID");
                bookGenre = getGenre(genreID);
            }
        } catch (SQLException e) {
            System.out.printf("Error message: %s, cause: %s%n", e.getMessage(), e.getCause());
        }

        return bookGenre;
    }

    public String getLanguageByBookID(int bookID) {
        String bookLanguage = "";

        try {
            String selectQuery = "select LanguageID from book b where BookID = ?";
            PreparedStatement selectStatement = this.connection.prepareStatement(selectQuery);
            selectStatement.setString(1, String.valueOf(bookID));
            ResultSet resultSet = selectStatement.executeQuery();

            if (resultSet.next()) {
                int languageID = resultSet.getInt("LanguageID");
                bookLanguage = getGenre(languageID);
            }
        } catch (SQLException e) {
            System.out.printf("Error message: %s, cause: %s%n", e.getMessage(), e.getCause());
        }

        return bookLanguage;
    }

    public Double getRatingByBookID(int bookID) {
        Double bookRating = 0.0;

        try {
            String selectQuery = "select rating from book b where BookID = ?";
            PreparedStatement selectStatement = this.connection.prepareStatement(selectQuery);
            selectStatement.setString(1, String.valueOf(bookID));
            ResultSet resultSet = selectStatement.executeQuery();

            if (resultSet.next()) {
                bookRating = resultSet.getDouble("rating");
            }
        } catch (SQLException e) {
            System.out.printf("Error message: %s, cause: %s%n", e.getMessage(), e.getCause());
        }

        return bookRating;
    }

    public BookAccess getAccessByBookID(int bookID) {
        BookAccess bookAccess = BookAccess.AVAILABLE;

        try {
            String selectQuery = "select AccessID from book b where BookID = ?";
            PreparedStatement selectStatement = this.connection.prepareStatement(selectQuery);
            selectStatement.setString(1, String.valueOf(bookID));
            ResultSet resultSet = selectStatement.executeQuery();

            if (resultSet.next()) {
                int accessID = resultSet.getInt("AccessID");
                bookAccess = getBookAccess(accessID);
            }
        } catch (SQLException e) {
            System.out.printf("Error message: %s, cause: %s%n", e.getMessage(), e.getCause());
        }

        return bookAccess;
    }

}
