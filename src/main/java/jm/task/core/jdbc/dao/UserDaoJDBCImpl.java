package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    Util util = new Util();

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        String sql = "CREATE TABLE `users_schema`.`new_table` (\n" +
                "  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,\n" +
                "  `name` VARCHAR(255) NOT NULL,\n" +
                "  `lastName` VARCHAR(255) NOT NULL,\n" +
                "  `age` TINYINT(3) NULL,\n" +
                "  PRIMARY KEY (`id`),\n" +
                "  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE);";
        try (PreparedStatement statement = util.getConnection().prepareStatement(sql)) {
            statement.execute();
            System.out.println("new_table created");
        } catch (SQLException e) {
            System.err.println("Не удалось создать таблицу.");
        }
    }

    public void dropUsersTable() {
        String sql =
                "DROP TABLE new_table;";
        try (PreparedStatement statement = util.getConnection().prepareStatement(sql)) {
            statement.execute();
            System.out.println("Table deleted!");
        } catch (SQLException e) {
            System.err.println("Table not found");
//            e.printStackTrace();
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO new_table (name, lastName, age) " +
                "values (" + "'" + name + "'" + ", " + "'" + lastName + "'" + ", " + age + ");";
        try (PreparedStatement statement = util.getConnection().prepareStatement(sql)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        String sql = "DELETE FROM new_table WHERE id=" + id + ";";
        try (PreparedStatement statement = util.getConnection().prepareStatement(sql)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        String sql = "SELECT * FROM new_table";
        List<User> list = new ArrayList<>();
        try (PreparedStatement statement = util.getConnection().prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String sName = resultSet.getString("name");
                String sLastName = resultSet.getString("lastName");
                byte age = resultSet.getByte("age");
                User user = new User(sName, sLastName, age);
                list.add(user);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public void cleanUsersTable() {
        String sql = "DELETE FROM new_table;";
        try (PreparedStatement statement = util.getConnection().prepareStatement(sql)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
