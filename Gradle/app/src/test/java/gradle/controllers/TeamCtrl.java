package gradle.controllers;

import java.sql.ResultSet;

import gradle.config.DbConnect;
import gradle.models.Team;

public class TeamCtrl extends DbConnect {
    public static boolean addTeam(String nama, int userId, int projekId) {
        query = "INSERT INTO team (user_id, projek_id ,nama) VALUES (? , ?, ?)";
        try {
            getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, projekId);
            preparedStatement.setString(3, nama);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static Team getTeam(int user_id, int projek_id) {
        query = "SELECT FROM anggaran WHERE user_id=? AND projek_id=?";
        try {
            getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, user_id);
            preparedStatement.setInt(2, projek_id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int userId = resultSet.getInt("user_id");
                    int projekId = resultSet.getInt("projek_id");
                    String teams = resultSet.getString("team");
                    Team team = new Team(userId, projekId, teams);
                    return team;

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }
}
