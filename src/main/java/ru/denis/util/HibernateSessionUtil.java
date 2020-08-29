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
        properties.getProperty("hibernate.connection.url");
        properties.getProperty("dialect");
        properties.getProperty("hibernate.connection.username");
        properties.getProperty("hibernate.connection.password");
        properties.getProperty("hibernate.connection.driver_class");
        return properties;
    }
}
