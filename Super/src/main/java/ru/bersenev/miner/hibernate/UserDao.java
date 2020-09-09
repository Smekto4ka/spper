package ru.bersenev.miner.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ru.bersenev.miner.user.service.ConverterData;
import ru.bersenev.miner.user.service.User;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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

    public User addResult(String name, ResultTable result) {
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

    public ResultTable finnResultById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(ResultTable.class, id);
    }

    public List<UsersTable> findUserAll() {
        List<UsersTable> from_usersTable = HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From UsersTable").list();
        return from_usersTable;
    }

    public List<ResultTable> findResultAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<ResultTable> fromResultTable = session.createQuery("From ResultTable").list();
     //   session.close();
        return fromResultTable;
    }
/*
    public List<ResultTable> findTopResult(){
        Session session =  HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<ResultTable> resultList = session.createQuery(
                "select rt from ResultTable rt where rt.user.name = :name order by rt.time desc", ResultTable.class)

                .setMaxResults(10).getResultList();
        session.close();
        return resultList;
    }*/

    public List getResultData(int length, int kolBomb) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();//getCurrentSession();//.openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<ResultTable> cr = cb.createQuery(ResultTable.class);
        Root<ResultTable> root = cr.from(ResultTable.class);

     //  cr.select(root).where(new Predicate[]{(cb.between(root.get("length"),  length,  length+1)), (cb.between(root.get("kolBomb"), kolBomb , kolBomb+1))});
        cr.select(root).where((cb.equal(root.get("length"), length)), (cb.equal(root.get("kolBomb"), kolBomb)));
        cr.orderBy(cb.asc(root.get("time")));
        Query query = session.createQuery(cr);
session.close();
        return query.getResultList();
    }
}