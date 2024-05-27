package gradle.controllers;

import java.sql.ResultSet;

import gradle.config.DbConnect;
import gradle.models.Anggaran;

public class AnggaranCtrl extends DbConnect {

    public static boolean addAnggaran(String item, int harga, int userId, int projekId) {
        query = "INSERT INTO anggaran (user_id, projek_id,item , harga) VALUES (? , ? , ? , ?)";
        try {
            getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, projekId);
            preparedStatement.setString(3, item);
            preparedStatement.setInt(4, harga);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static Anggaran getAnggaran(int user_id, int projek_id) {
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
                    String item = resultSet.getString("item");
                    int harga = resultSet.getInt("harga");
                    Anggaran anggaran = new Anggaran(userId, projekId, item, harga);
                    return anggaran;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
