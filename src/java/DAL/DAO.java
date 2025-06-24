package DAL;

import Models.User;
import java.sql.*;
import java.util.Vector;

public class DAO {
    private Connection conn;
    private String status = "OK";
    private Vector<User> users = new Vector<>();
    public static DAO ins = new DAO();
    public DAO() {
        try {
            conn = new DBContext().getConnection();
        } catch (Exception e) {
            status = "Connection failed: " + e.getMessage();
        }
    }


    public User login(String username, String password) {
        String sql = "select * from [user] where [user]=? and password = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new User(
                        rs.getString("user"),
                        rs.getString("password"),
                        rs.getInt("roll"),
                        rs.getString("address")
                    );
                }
            }
        } catch (SQLException e) {
            status = "Login error: " + e.getMessage();
        }
        return null;
    }

    public void loadUsers() {
        users.clear();
        String sql = "select * from [user]";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                users.add(new User(
                    rs.getString("user"),
                    rs.getString("password"),
                    rs.getInt("roll"),
                        rs.getString("address")
                ));
            }
        } catch (SQLException e) {
            status = "Load error: " + e.getMessage();
        }
    }

    // Getters
    public Vector<User> getUsers() {
        return users;
    }

    public String getStatus() {
        return status;
    }

    public static void main(String[] args) {
        DAO dao = new DAO();
        dao.loadUsers();
        for (User u : dao.getUsers()) {
            System.out.println(u.getUser() + " - Role: " + u.getRoll());
        }
        System.out.println("Status: " + dao.getStatus());
    }

}
