package ru.bersenev.miner.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;


import java.util.List;

public class UserDao {

    public UsersTable findUserByName(String name) {
       /* Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        UsersTable usersTable = session.get(UsersTable.class, name);
        session.close();*/
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(UsersTable.class, name);
    }

    public void saveUser(UsersTable user) {

        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();

        Transaction tx1 = session.beginTransaction();
        session.save(user);
        tx1.commit();

        session.close();
    }

    public void updateUser(UsersTable user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();//getCurrentSession();//.openSession();
       // <property name="hibernate.current_session_context_class">thread</property>
        Transaction tx1 = session.beginTransaction();
        session.update(user);
        tx1.commit();
      session.close();
    }

    public void deleteUser(UsersTable user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();//getCurrentSession();//.openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(user);
        tx1.commit();
       session.close();
    }

    public ReseltTable finnResultById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(ReseltTable.class, id);
    }

    public List<UsersTable> findUserAll() {
        List<UsersTable> users = (List<UsersTable>)  HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From UsersTable").list();
        return users;
    }
    public List<ReseltTable> findResultAll() {
        List<ReseltTable> result = (List<ReseltTable>)  HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From ReseltTable").list();
        return result;
    }

}