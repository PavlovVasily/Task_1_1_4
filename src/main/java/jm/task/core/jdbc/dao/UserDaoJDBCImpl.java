package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Statement statement = Util.statementToDataBase()) {
            if (statement != null) {
                statement.executeUpdate(new StringBuilder()
                        .append("CREATE TABLE `task_1_1_3`.`users` (")
                        .append("`id` BIGINT NOT NULL AUTO_INCREMENT,")
                        .append("`name` VARCHAR(45) NULL,")
                        .append("`lastName` VARCHAR(45) NULL,")
                        .append("`age` TINYINT NULL,")
                        .append("PRIMARY KEY (`id`));")
                        .toString());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void dropUsersTable() {
        try (Statement statement = Util.statementToDataBase()) {
            if (statement != null) {
                statement.executeUpdate("drop table users");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Statement statement = Util.statementToDataBase()) {
            if (statement != null) {
                statement.executeUpdate(new StringBuilder()
                        .append("INSERT INTO `task_1_1_3`.`users` (")
                        .append("`name`, `lastName`, `age`)")
                        .append(" VALUES  ('")
                        .append(name + "', '" + lastName + "', '" + age + "');")
                        .toString());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeUserById(long id) {
        try (Statement statement = Util.statementToDataBase()) {
            if (statement != null) {
                statement.executeUpdate(new StringBuilder()
                        .append("DELETE FROM `task_1_1_3`.`users` WHERE")
                        .append(" id='" + id + "';")
                        .toString());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try (Statement statement = Util.statementToDataBase()) {
            if (statement != null) {
                try (ResultSet rs = statement.executeQuery("select * from users")) {
                    while (rs.next()) {
                        User newUser = new User(rs.getString("name"),
                                rs.getString("lastname"),
                                rs.getByte("age"));
                        newUser.setId(rs.getLong("id"));
                        list.add(newUser);
                    }
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public void cleanUsersTable() {
        try (Statement statement = Util.statementToDataBase()) {
            if (statement != null) {
                statement.executeUpdate("truncate table `task_1_1_3`.`users`");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}