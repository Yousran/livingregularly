package gradle.controllers;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import java.sql.Statement;

import gradle.config.DbConnect;
import gradle.models.Proyek;

public class ProyekCtrl extends DbConnect {
    // public static boolean addProjek(String namaProjek, String tanggal, int userId,int anggaran) {
    //     query = "INSERT INTO projek (nama_projek, tanggal, user_id, anggaran) VALUES (?, ?, ?, ?)";
    //     try {
    //         getConnection();
    //         preparedStatement = connection.prepareStatement(query);
    //         preparedStatement.setString(1, namaProjek);
    //         preparedStatement.setString(2, tanggal);
    //         preparedStatement.setInt(3, userId);
    //         preparedStatement.setInt(4, anggaran);
    //         preparedStatement.executeUpdate();
    //         return true;
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }
    //     return false;
    // }

    public static int addProjek(String namaProjek, String tanggal, int userId, int anggaran) {
        String query = "INSERT INTO projek (nama_projek, tanggal, user_id, anggaran) VALUES (?, ?, ?, ?)";
        int projekId = 0;
        try {
            getConnection();
            preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, namaProjek);
            preparedStatement.setString(2, tanggal);
            preparedStatement.setInt(3, userId);
            preparedStatement.setInt(4, anggaran);
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                projekId = generatedKeys.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return projekId;
    }
    

    public static List<Proyek> getAllProjek(int UserId) {
        List<Proyek> proyeks = new ArrayList<>();
        query = "SELECT * FROM projek WHERE user_id=?";
        try {
            getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, UserId);
            resultSet = preparedStatement.executeQuery();
            if (!resultSet.isBeforeFirst()) {
                System.out.println("No data found in the database.");
            }
            while (resultSet.next()) {
                int projekId = resultSet.getInt("projek_id");
                int userId = resultSet.getInt("user_id");
                String namaProjek = resultSet.getString("nama_projek");
                String tanggal = resultSet.getString("tanggal");
                int pengeluaran = resultSet.getInt("anggaran");
                System.out.println("Project found: " + resultSet.getString("nama_projek"));
                Proyek proyek = new Proyek(projekId, userId, namaProjek, tanggal, pengeluaran);
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
                    String tanggal = resultSet.getString("tanggal");
                    int pengeluaran = resultSet.getInt("anggaran");
                    Proyek proyek = new Proyek(userId, projekId, namaProjek, tanggal, pengeluaran);
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