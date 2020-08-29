package ru.denis.util;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import ru.denis.model.User;


import java.util.Properties;

@Slf4j
public class HibernateSessionUtil {
    private static SessionFactory sessionFactory;


    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                sessionFactory = new Configuration().addAnnotatedClass(User.class)
                        .addProperties(getProperty()).buildSessionFactory();
            } catch (Exception e) {
                log.error("Error to get session " + e);
            }
        }
        return sessionFactory;
    }

    private static Properties getProperty() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/jm?serverTimezone=GMT");
        properties.setProperty("dialect", "org.hibernate.dialect.MySQLDialect");
        properties.setProperty("hibernate.connection.username", "root");
        properties.setProperty("hibernate.connection.password", "root");
        properties.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        return properties;
    }
}
