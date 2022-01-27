package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        // реализуйте алгоритм здесь
        //Создание UserService
        UserService userService = new UserServiceImpl();

        //Создание базы данных
        userService.createUsersTable();

        //Добавление User'ов
        userService.saveUser("Vasya", "Pavlov", (byte)29);
        userService.saveUser("Anya", "Pavlova", (byte)27);
        userService.saveUser("Kolya", "Pavlov", (byte)39);
        userService.saveUser("Marina", "Pavlova", (byte)67);

        //Получение все User'ов из БД и вывод в консоль
        for (User user : userService.getAllUsers()) {
            System.out.println(user);
        }

        //Очистка таблицы
        userService.cleanUsersTable();

        //Удаление таблицы
        userService.dropUsersTable();
    }
}
