package ru.denis;

import ru.denis.dao.UserDao;
import ru.denis.dao.UserDaoHibernateImpl;

public class Main {

    public static void main(String[] args) {
        UserDao user = new UserDaoHibernateImpl();
        user.createUsersTable();
        user.saveUser("Vasy", "Ivanov", (byte) 24);
        user.saveUser("Denis", "Moshkov", (byte) 26);
        user.saveUser("Roman", "Petrov", (byte) 21);
        user.saveUser("Vlad", "Samsonov", (byte) 56);
        user.saveUser("Dima", "Alekseev", (byte) 34);
        user.getAllUsers();
        user.cleanUsersTable();
        user.dropUsersTable();
    }
}
