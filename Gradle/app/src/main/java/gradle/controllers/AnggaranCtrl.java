package gradle.controllers;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import gradle.config.DbConnect;
import gradle.models.Anggaran;

public class AnggaranCtrl extends DbConnect {

    public static boolean addAnggaran(String item, int harga, int projekId) {
        query = "INSERT INTO anggaran (projek_id,item , harga) VALUES (? , ? , ?)";
        try {
            getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, projekId);
            preparedStatement.setString(2, item);
            preparedStatement.setInt(3, harga);
            preparedStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static List<Anggaran> getAllAnggaran(int projek_id) {
        List<Anggaran> anggarans = new ArrayList<>();
        query = "SELECT * FROM anggaran WHERE projek_id=?";
        try {
            getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, projek_id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int projekId = resultSet.getInt("projek_id");
                    String item = resultSet.getString("item");
                    int harga = resultSet.getInt("harga");
                    Anggaran anggaran = new Anggaran(projekId, item, harga);
                    anggarans.add(anggaran);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return anggarans;


    }
}
