package gradle.controllers;

import java.sql.ResultSet;
import java.sql.SQLException;

import gradle.config.DbConnect;

public class UserLogin extends DbConnect {

    public static boolean validasiLogin(String email, String password) {
        getConnection();
        query = "SELECT * FROM user WHERE email=? AND password=?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                boolean valid = resultSet.next();
                return valid;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
