package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Transaction transaction = null;

        try (Session session = Util.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();
            session.createSQLQuery(new StringBuilder()
                    .append("CREATE TABLE `task_1_1_3`.`users` (")
                    .append("`id` BIGINT NOT NULL AUTO_INCREMENT,")
                    .append("`name` VARCHAR(45) NULL,")
                    .append("`lastName` VARCHAR(45) NULL,")
                    .append("`age` TINYINT NULL,")
                    .append("PRIMARY KEY (`id`));")
                    .toString()).executeUpdate();

            transaction.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage() + " - Ошибка создания таблицы (таблица уже существует)");
        }

    }

    @Override
    public void dropUsersTable() {
        Transaction transaction = null;

        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery("drop table users").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage() + " - Ошибка удаления таблицы (таблица отсутствует)");
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;

        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            User user = new User(name, lastName, age);
            user.setId((long) 8);

            session.save(user);

            transaction.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;

        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            User deletedUser = session.get(User.class, id);
            if (deletedUser != null) {
                session.remove(deletedUser);
            }

            transaction.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<User> getAllUsers() {
        Transaction transaction = null;
        List<User> list = new ArrayList<>();

        try (Session session = Util.getSessionFactory().openSession()) {

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);

            Root<User> root = criteriaQuery.from(User.class);
            CriteriaQuery<User> all = criteriaQuery.select(root);

            TypedQuery<User> allQuery = session.createQuery(all);
            list = allQuery.getResultList();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;

        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery("delete from users").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
