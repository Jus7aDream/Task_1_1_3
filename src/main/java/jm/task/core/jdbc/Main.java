package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // реализуйте алгоритм здесь
//        Util util = new Util();
//        util.getConnection();
        UserServiceImpl userService = new UserServiceImpl();
//        userService.createUsersTable();
//        userService.dropUsersTable();
        userService.saveUser("Artur", "Shakhbanov",  (byte) 33);
//        userService.removeUserById(1L);
//        userService.getAllUsers();



//        String query = "select * from users_table";
//        try {
//            Statement statement = util.getConnection().createStatement();
//            ResultSet resultSet = statement.executeQuery(query);
//            while (resultSet.next()){
//                int result = resultSet.getInt("id");
//                String result2 = resultSet.getString("name");
//                String result3 = resultSet.getString("lastName");
//                int result4 = resultSet.getInt("age");
//            }
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
    }
}
