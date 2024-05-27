package gradle.controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import gradle.config.DbConnect;
import gradle.models.User;

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

        public static User getUser(String email, String password){
        getConnection();
        query = "SELECT * FROM user WHERE email=? AND password=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nama = resultSet.getString("Nama");
                String email1 = resultSet.getString("email");
                String password1 = resultSet.getString("password");
                String profilPath = resultSet.getString("profil_path");
                User user = new User(id,nama, email1,password1,profilPath);

                resultSet.close();
                preparedStatement.close();
                return user;
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
