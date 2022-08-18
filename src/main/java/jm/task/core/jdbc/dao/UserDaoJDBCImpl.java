package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    Util util = new Util();
    User user = new User();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String sqlUpdate = "CREATE TABLE `users_schema`.`new_table` (\n" +
                "  `id` BIGINT(8) NOT NULL AUTO_INCREMENT,\n" +
                "  `name` VARCHAR(255) NOT NULL,\n" +
                "  `lastName` VARCHAR(255) NOT NULL,\n" +
                "  `age` TINYINT(3) NULL,\n" +
                "  PRIMARY KEY (`id`),\n" +
                "  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE);";
        try (Connection connection = util.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(sqlUpdate);
            System.out.println("new_table created");
        } catch (SQLException e) {
            System.err.println("Не удалось создать таблицу.");
        }

    }

    public void dropUsersTable() {
        String sqlDelete =
                "DROP TABLE new_table;";

        try (Connection connection = util.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(sqlDelete);
            System.out.println("Table deleted!");
        } catch (SQLException e) {
            System.err.println("Table not found");
//            e.printStackTrace();
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        String sqlUpdate = "INSERT INTO new_table (name, lastName, age) " +
                "values (" + "'" + name + "'" + ", " + "'" + lastName + "'" + ", " + age + ");";
        try (Connection connection = util.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(sqlUpdate);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        long newId = id;
        String sqlRem = "delete from new_table where id=1L;";
        try (Connection connection = util.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(sqlRem);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        String sql = "SELECT * FROM new_table";
        List<User> list = new ArrayList<>();
        try {
            Statement statement = util.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
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

    }
}
