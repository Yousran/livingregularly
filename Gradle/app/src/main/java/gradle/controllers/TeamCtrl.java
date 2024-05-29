package gradle.controllers;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import gradle.config.DbConnect;
import gradle.models.User;

public class TeamCtrl extends DbConnect {

    public static boolean addTeam(String nama, int projekId) {
        String query = "INSERT INTO team (projek_id ,nama) VALUES (?, ?)";
        try {
            getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, projekId);
            preparedStatement.setString(2, nama);
            preparedStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static List<User> getTeamMembers(int projek_id) {
        List<User> members = new ArrayList<>();
        String query = "SELECT user_id FROM team WHERE projek_id=?";
        
        try {
            getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, projek_id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                List<Integer> userIds = new ArrayList<>();
                while (resultSet.next()) {
                    userIds.add(resultSet.getInt("user_id"));
                }

                for (int userId : userIds) {
                    User user = getUserById(userId);
                    if (user != null) {
                        members.add(user);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return members;
    }

    private static User getUserById(int userId) {
        String query = "SELECT * FROM user WHERE id=?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String nama = resultSet.getString("Nama");
                    String email = resultSet.getString("email");
                    String password = resultSet.getString("password");
                    String profil_path = resultSet.getString("profil_path");

                    return new User(id, nama, email, password, profil_path);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}