package ru.bersenev.miner.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;

    private HibernateSessionFactoryUtil() {
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();//"hibernate.cfg.xml");
                configuration.addAnnotatedClass(UsersTable.class);// передаем наши классы в конфигуратор
                configuration.addAnnotatedClass(ResultTable.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                //     configuration.getProperties() - мол настроить -  Properties — это параметры для работы hibernate, указанные в специальном файле hibernate.cfg.xml.
                sessionFactory = configuration.buildSessionFactory(builder.build());

            } catch (HibernateException e) {
                System.out.println("Исключение!" + e);
            }
        }
        return sessionFactory;
    }
}