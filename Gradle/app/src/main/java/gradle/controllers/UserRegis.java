package gradle.controllers;

import java.sql.ResultSet;
import java.sql.SQLException;

import gradle.config.DbConnect;

public class UserRegis extends DbConnect {

    public static boolean registerUser(String nama, String email, String password) {
        getConnection();
        query = "INSERT INTO user(nama,email,password) VALUES(?, ?, ?)";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, nama);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, password);

            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
        public static boolean cekEmail(String email){
        getConnection();
        query = "SELECT email FROM user WHERE email=?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
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
