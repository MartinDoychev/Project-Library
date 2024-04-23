package user;

import user.interfaces.Admin;
import user.repository.IUserRepository;
import user.repository.UserRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static java.lang.String.valueOf;

public class UserAdmin extends User implements Admin {
    private final IUserRepository userRepository;

    public UserAdmin() throws SQLException {
        this.userRepository = new UserRepository();
    }

    @Override
    public IUserRepository getUserRepository() {
        return userRepository;
    }

    @Override
    public boolean lockAccount(int userID) {
        boolean result = false;

        try {
            if (!this.userRepository.userExistsInGeneralDB(userID)) {
                return false;
            } else {
                boolean isLocked;

                String selectQuery = "select isLocked from user where userID = ?";
                PreparedStatement selectStatement = userRepository.getConnection().prepareStatement(selectQuery);
                selectStatement.setString(1, valueOf(userID));
                ResultSet resultSet = selectStatement.executeQuery();

                if (resultSet.next()) {
                    isLocked = resultSet.getBoolean("isLocked");
                    if (isLocked) {
                        return true;
                    } else {
                        result = lock(userID);
                    }
                } else return false;
            }
        } catch (SQLException e) {
            System.out.printf("Error message: %s, cause: %s%n", e.getMessage(), e.getCause());
        }
        return result;
    }

    private boolean lock(int userID) {
        boolean result = false;

        try {
                String updateQuery = "update user set isLocked = 1 where userID = ?";
                PreparedStatement updateStatement = userRepository.getConnection().prepareStatement(updateQuery);
                updateStatement.setString(1, valueOf(userID));
                updateStatement.executeUpdate();
                result = true;

        } catch (SQLException e) {
            System.out.printf("Error message: %s, cause: %s%n", e.getMessage(), e.getCause());
        }
        return result;
    }
}
