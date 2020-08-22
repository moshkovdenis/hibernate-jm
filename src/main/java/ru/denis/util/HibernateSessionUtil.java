package ru.denis.util;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.denis.model.User;

@Slf4j
public class HibernateSessionUtil {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration con = new Configuration().configure().addAnnotatedClass(User.class);
                sessionFactory = con.buildSessionFactory();
            } catch (Exception e) {
                log.error("Ошибка при получении сессии " + e);
            }
        }
        return sessionFactory;
    }
}
