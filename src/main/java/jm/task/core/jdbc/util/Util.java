package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Util {
    // реализуйте настройку соеденения с БД
    private static String userName = "root";
    private static String password = "mysql";
    private static String connectionURL = "jdbc:mysql://localhost:3306/task_1_1_3";
    private static String driver = "com.mysql.cj.jdbc.Driver";


    public static Statement statementToDataBase() {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            return DriverManager.getConnection(connectionURL, userName, password).createStatement();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


}
