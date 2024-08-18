package service.appDAO;

import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.stereotype.Component;
import users.User;

import java.sql.*;

@Component
public class UserDAO {

    public PGSimpleDataSource dataSource;

    public UserDAO(PGSimpleDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void save(User user) throws RuntimeException {
        String sql = "INSERT INTO User (id, name, creation_date) VALUES (?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);) {

            statement.setInt(1, user.getID());
            statement.setString(2, user.getUserName());
            statement.setDate(3, Date.valueOf(user.getCreationDate()));

            int count = statement.executeUpdate();

            System.out.println("Update executed");

            if (count > 0) {
                System.out.println("The User was successfully inserted");
            } else {
                System.out.println("The insertion failed");
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public User getByID(int id) {
        String sql = "SELECT * FROM User WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);) {

            statement.setInt(1, id);
            ResultSet results = statement.executeQuery();
            if (!results.first()) {
                return null;
            }

            User user = new User();
            user.setID(results.getInt(1));
            user.setUserName(results.getString(2));
            user.setCreationDate(results.getDate(3).toLocalDate());


            return user;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void delete(User user) {
        String sql = "DELETE User WHERE id=?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);) {

            statement.setInt(1, user.getID());

            int rowsCount = statement.executeUpdate();

            if(rowsCount > 0) {
                System.out.println("User and his/her tickets were successfully deleted");
            } else {
                System.out.println("Deletion failed");
            }
        } catch(SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}