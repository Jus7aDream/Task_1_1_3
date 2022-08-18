package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
    private final User user = new User();

    @Override
    public void createUsersTable() throws SQLException {
        try {
            userDaoJDBC.createUsersTable();
        } catch (Exception e) {
            e.toString();
        }
    }

    public void dropUsersTable() {
        try {
            userDaoJDBC.dropUsersTable();
        } catch (Exception e) {
            e.toString();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            userDaoJDBC.saveUser(name, lastName, age);
        } catch (Exception e) {
            e.toString();

        }
    }

    public void removeUserById(long id) {
        try {
            userDaoJDBC.removeUserById(1L);
        } catch (Exception e) {
            System.out.println("При тестировании удаления пользователя по id произошло исключение\n" + e);
        }
    }

    public List<User> getAllUsers() throws SQLException {
        try {
            List<User> userList = userDaoJDBC.getAllUsers();
            if (userList.size() != 1) {
                System.out.println("Проверьте корректность работы метода сохранения " +
                        "пользователя/удаления или создания таблицы");
            }
            return userList;
        } catch (Exception e) {
            System.out.println("При попытке достать всех пользователей из базы данных произошло исключение\n" + e);
        }
        return null;
    }

    public void cleanUsersTable() {
        try {
            userDaoJDBC.cleanUsersTable();
            if (userDaoJDBC.getAllUsers().size() != 0) {
                System.out.println("Метод очищения таблицы пользователей реализован не корректно");
            }
        } catch (Exception e) {
            System.out.println("При тестировании очистки таблицы пользователей произошло исключение\n" + e);
        }
    }
}
