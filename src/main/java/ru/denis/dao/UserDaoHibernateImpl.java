package ru.denis.dao;


import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.Transaction;

import ru.denis.model.User;
import ru.denis.util.HibernateSessionUtil;

import java.util.LinkedList;
import java.util.List;

@Slf4j
public class UserDaoHibernateImpl implements UserDao {
    private static final String CREATE_TABLE = String.format("CREATE table if not exists jm.user(\n\tid int auto_increment primary key,\n\tname varchar(35) not null,\n\tlastName varchar(45) not null,\n\tage int not null,\n\tconstraint id_UNIQUE\n\t\tunique (id)\n);");
    private static final String DROP_TABLE = "DROP TABLE if exists jm.user;";
    private static final String CLEAR = "DELETE from jm.user;";
    private static final String ALL = "From User";

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        try (Session session = HibernateSessionUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery(CREATE_TABLE).executeUpdate();
            transaction.commit();
            log.info("Table user created");
        } catch (Exception e) {
            log.error("Failed to create User table", e);
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = HibernateSessionUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery(DROP_TABLE).executeUpdate();
            transaction.commit();
            log.info("User table dropped.");
        } catch (Exception e) {
            log.error("Failed to drop User table. ", e);
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = HibernateSessionUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(new User(name, lastName, age));
            transaction.commit();
            log.info("User named " + name + " added to DB.");
        } catch (Exception e) {
            log.error("Failed to add user " + name, e);
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = HibernateSessionUtil.getSessionFactory().openSession()) {
            User user = session.load(User.class, id);
            Transaction transaction = session.beginTransaction();
            session.delete(user);
            transaction.commit();
            log.info("User with id " + id + " delete.");
        } catch (Exception e) {
            log.error("Failed to delete user with id " + id, e);
        }
    }


    @Override
    public List<User> getAllUsers() {
        List<User> allUsers = new LinkedList<>();
        try (Session session = HibernateSessionUtil.getSessionFactory().openSession()) {
            allUsers = (List<User>) session.createQuery(ALL).list();
            log.info("All users received: " + allUsers.toString());
        } catch (Exception e) {
            log.error("Failed to get all users. " + e);
        }
        return allUsers;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = HibernateSessionUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery(CLEAR).executeUpdate();
            transaction.commit();
            log.info("All users have been removed from the table");
        } catch (Exception e) {
            log.error("Failed to delete all users from table ", e);
        }
    }
}
