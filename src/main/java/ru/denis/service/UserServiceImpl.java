package ru.denis.service;

import ru.denis.dao.UserDao;
import ru.denis.dao.UserDaoHibernateImpl;
import ru.denis.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao user = new UserDaoHibernateImpl();

    @Override
    public void createUsersTable() {
        user.createUsersTable();
    }

    @Override
    public void dropUsersTable() {
        user.dropUsersTable();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        user.saveUser(name, lastName, age);
    }

    @Override
    public void removeUserById(long id) {
        user.removeUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return user.getAllUsers();
    }

    @Override
    public void cleanUsersTable() {
        user.cleanUsersTable();
    }
}
