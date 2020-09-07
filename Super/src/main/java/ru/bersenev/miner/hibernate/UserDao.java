package ru.bersenev.miner.hibernate;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import ru.bersenev.miner.user.service.ConverterData;
import ru.bersenev.miner.user.service.User;

import java.util.List;

public class UserDao {


    public User findUserByName(String name) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        UsersTable usersTable = session.get(UsersTable.class, name);
        User user = null;
        if (usersTable != null) {
            ConverterData converter = new ConverterData();
            user = converter.converterUsersTable(usersTable);
        }
        session.close();

        return user;    //HibernateSessionFactoryUtil.getSessionFactory().openSession().get(UsersTable.class, name);
    }

    public void saveUser(UsersTable user) {

        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();

        Transaction tx1 = session.beginTransaction();
        session.save(user);
        tx1.commit();

        session.close();
    }

    public void updateUser(User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();//getCurrentSession();//.openSession();
        // <property name="hibernate.current_session_context_class">thread</property>
        ConverterData converter = new ConverterData();
        UsersTable usersTable = session.get(UsersTable.class, user.getName());
        usersTable = converter.converterUser(user, usersTable);
        Transaction tx1 = session.beginTransaction();
        session.update(usersTable);
        tx1.commit();
        session.close();
    }

    public void updateUserTable(UsersTable user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();//getCurrentSession();//.openSession();
        // <property name="hibernate.current_session_context_class">thread</property>
        Transaction tx1 = session.beginTransaction();
        session.update(user);
        tx1.commit();
        session.close();
    }

    public User addResult(String name, ReseltTable result) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        UsersTable usersTable = session.get(UsersTable.class, name);
        usersTable.addResult(result);
        Transaction tx1 = session.beginTransaction();
        session.update(usersTable);
        tx1.commit();

        User user = new ConverterData().converterUsersTable(usersTable);
        session.close();
        return user;
    }

    public void deleteUser(String name) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();//getCurrentSession();//.openSession();
        UsersTable user = session.get(UsersTable.class, name);
        Transaction tx1 = session.beginTransaction();
        session.delete(user);
        tx1.commit();
        session.close();
    }

    public ReseltTable finnResultById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(ReseltTable.class, id);
    }

    public List<UsersTable> findUserAll() {
        List<UsersTable> users = (List<UsersTable>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From UsersTable").list();
        return users;
    }

    public List<ReseltTable> findResultAll() {
        List<ReseltTable> result = (List<ReseltTable>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From ReseltTable").list();
        return result;
    }

    public List getResultData(int length, int kolBomb) {

        Criteria criteria;

        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();//getCurrentSession();//.openSession();

       criteria = session.createCriteria(ReseltTable.class).add(Restrictions.eq("length", length)).add(Restrictions.eq("kolBomb", kolBomb));
       return criteria.list();

      /*  Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();//getCurrentSession();//.openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<ReseltTable> cr = cb.createQuery(ReseltTable.class);
        Root<ReseltTable> root = cr.from(ReseltTable.class);
        cr.select(root).where(cb.like(root.<String>get("length"), Integer.toString(length)), cb.like(root.<String>get("kolBomb"), Integer.toString(kolBomb)));
        cr.orderBy(cb.asc(root.get("time")));
        Query query = session.createQuery(cr);
        return query.getResultList();*/
    }
}