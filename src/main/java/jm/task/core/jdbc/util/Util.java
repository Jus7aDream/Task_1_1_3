package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class Util {
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/users_schema";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "Qaz123321";
    private Connection connection = null;

    public Connection getConnection() {

        try {
            //Подключаем Драйвер
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
//            if(!connection.isClosed()) {
//                System.out.println("Connected");
//            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
            System.out.println("Connection_ERROR");
        }
        return connection;

    }
}
