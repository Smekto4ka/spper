package ru.bersenev.miner.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.bersenev.miner.user.service.ConverterData;
import ru.bersenev.miner.user.service.Result;
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

   /* public List<UsersTable> findUserAll() {
        List<UsersTable> from_usersTable = HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From UsersTable").list();
        return from_usersTable;
    }*/

    public List<Result> findResultAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<ResultTable> fromResultTable = session.createQuery("From ResultTable").list();
        ConverterData converter = new ConverterData();
        List<Result> result = converter.converterResultTable(fromResultTable);
        session.close();
        return result;
    }

    public List<User> findUserAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<UsersTable> fromUsersTable = session.createQuery("From UsersTable").list();
        ConverterData converter = new ConverterData();
        List<User> result = converter.converterUsersTable(fromUsersTable);
        session.close();
        return result;
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

    /* public List<Result> getResultData(int length, int kolBomb) {
         Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();//getCurrentSession();//.openSession();
         CriteriaBuilder cb = session.getCriteriaBuilder();
         CriteriaQuery<ResultTable> cr = cb.createQuery(ResultTable.class);
         Root<ResultTable> root = cr.from(ResultTable.class);
         cr.select(root).where((cb.equal(root.get("length"), length)), (cb.equal(root.get("kolBomb"), kolBomb)));
         cr.orderBy(cb.asc(root.get("time")));
         Query query = session.createQuery(cr);
         List result = new ConverterData().converterResultTable(query.getResultList());

         session.close();
         return result;
     }*/
    public List<Object[]> getResult() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List resultList = session.createQuery(
                "select r.user.name , count(r.user.name) , sum(r.time) from ResultTable r group by r.user.name ")

                .getResultList();
        session.close();
        return resultList;
    }

    public List<Object[]> getTopWinResult(User user, int kol) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List resultList = session.createQuery(
                "select r.user.name, r.time from ResultTable r where r.length=:length and r.kolBomb=:kolBomb order by r.time asc")
                //   "select rt from ResultTable rt where rt.user.name = :name order by rt.time desc", ResultTable.class)
                .setParameter("length", user.getLength()).setParameter("kolBomb", user.getKolBomb()).setMaxResults(kol).getResultList();
        session.close();
        return resultList;
    }

    public String getMinTime(User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List resultList = session.createQuery(
                "select min(r.time) from ResultTable r where r.length=:length and r.kolBomb=:kolBomb and r.user.name=:name group by r.user.name")

                .setParameter("length", user.getLength()).setParameter("kolBomb", user.getKolBomb()).setParameter("name", user.getName()).getResultList();
        session.close();

        return Long.toString((Long) resultList.get(0));
    }
}