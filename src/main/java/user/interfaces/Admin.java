package user.interfaces;

import java.sql.SQLException;

public interface Admin {
    boolean lockAccount(int userID) throws SQLException;
}
