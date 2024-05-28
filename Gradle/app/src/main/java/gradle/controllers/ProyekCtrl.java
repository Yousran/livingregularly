package gradle.controllers;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import gradle.config.DbConnect;
import gradle.models.Proyek;

public class ProyekCtrl extends DbConnect {
    public static boolean addProjek(String namaProjek, String tanggal, int userId) {
        query = "INSERT INTO projek (nama_projek, tanggal, user_id) VALUES (?, ?, ?)";
        try {
            getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, namaProjek);
            preparedStatement.setString(2, tanggal);
            preparedStatement.setInt(3, userId);
            preparedStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static List<Proyek> getAllProjek() {
        List<Proyek> proyeks = new ArrayList<>();
        query = "SELECT * FROM projek";
        try {
            getConnection();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            if (!resultSet.isBeforeFirst()) {
                System.out.println("No data found in the database.");
            }
            while (resultSet.next()) {
                int projekId = resultSet.getInt("projek_id");
                int userId = resultSet.getInt("user_id");
                String namaProjek = resultSet.getString("nama_projek");
                String tanggal = resultSet.getString("tanggal");
                String myTeam = resultSet.getString("team");
                int pengeluaran = resultSet.getInt("anggaran");
                System.out.println("Project found: " + resultSet.getString("nama_projek"));
                Proyek proyek = new Proyek(projekId, userId, namaProjek, tanggal, myTeam, pengeluaran);
                proyeks.add(proyek);
            }
        } catch (Exception e) {
            System.out.println("Error fetching projects: " + e.getMessage());
            e.printStackTrace();
        }
        return proyeks;
    }

    public static Proyek getProjek(int user_id, int projek_id) {
        getConnection();
        query = "SELECT * FROM projek WHERE user_id=? AND projek_id=?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, user_id);
            preparedStatement.setInt(2, projek_id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int userId = resultSet.getInt("user_id");
                    int projekId = resultSet.getInt("projek_id");
                    String namaProjek = resultSet.getString("nama_projek");
                    String myTeam = resultSet.getString("team");
                    String tanggal = resultSet.getString("tanggal");
                    int pengeluaran = resultSet.getInt("anggaran");
                    Proyek proyek = new Proyek(userId, projekId, namaProjek, myTeam, tanggal, pengeluaran);
                    return proyek;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean deleteProjek(String namaProjek) {
        query = "DELETE FROM projek WHERE nama_projek=?";
        try {
            getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, namaProjek);
            preparedStatement.executeUpdate();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}