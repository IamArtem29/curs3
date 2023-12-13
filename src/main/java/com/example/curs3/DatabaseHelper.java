package com.example.curs3;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

class DatabaseHelper {
    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "123";

    public int getResultFromDB(int fibnum, String type) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)) {
            String query = "SELECT result FROM history WHERE fibnum = ? AND type = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, fibnum);
                statement.setString(2, type);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getInt("result");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // Если результат не найден
    }

    public void saveResultToDB(int fibnum, int result, String type) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)) {
            String query = "INSERT INTO history (fibnum, result, type) VALUES (?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, fibnum);
                statement.setInt(2, result);
                statement.setString(3, type);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<HistoryEntry> getAllHistoryEntries() {
        List<HistoryEntry> entries = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)) {
            String query = "SELECT * FROM history";
            try (PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int fibnum = resultSet.getInt("fibnum");
                    int result = resultSet.getInt("result");
                    String type = resultSet.getString("type");

                    HistoryEntry entry = new HistoryEntry(fibnum, result, type);
                    entries.add(entry);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return entries;
    }
}
