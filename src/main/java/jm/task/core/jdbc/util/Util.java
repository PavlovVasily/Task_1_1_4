package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Util {
    // реализуйте настройку соеденения с БД
    private static String userName = "root";
    private static String password = "mysql";
    private static String connectionURL = "jdbc:mysql://localhost:3306/task_1_1_3";
    private static String driver = "com.mysql.cj.jdbc.Driver";

    private static SessionFactory sessionFactory;

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

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, driver);
                settings.put(Environment.URL, connectionURL);
                settings.put(Environment.USER, userName);
                settings.put(Environment.PASS, password);
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");

                //settings.put(Environment.SHOW_SQL, "true");

                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

                //settings.put(Environment.HBM2DDL_AUTO, "validate");

                Configuration cfg = new Configuration();
                cfg.setProperties(settings);

                Configuration configuration = new Configuration();

                configuration.setProperties(settings);

                configuration.addAnnotatedClass(User.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }




}
