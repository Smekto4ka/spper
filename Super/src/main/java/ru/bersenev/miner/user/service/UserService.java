package ru.bersenev.miner.user.service;

import ru.bersenev.miner.hibernate.ResultTable;
import ru.bersenev.miner.hibernate.UserDao;
import ru.bersenev.miner.hibernate.UsersTable;

import java.util.ArrayList;
import java.util.List;

public class UserService {

    private UserDao userDao;

    public UserService() {
        userDao = new UserDao();
    }

    public User userAuthorization(String name) {
        return userDao.findUserByName(name);
    }

    public User addUsers(UsersTable user) {
        userDao.saveUser(user);
        return new ConverterData().converterUsersTable(user);

    }

    public void deletUser(String name) {
        userDao.deleteUser(name);
    }

    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    public User addResult(User user, long time) {

        for (Result reseltTable : user.getResultTables()) {
            if (reseltTable.getKolBomb() == user.getKolBomb() && reseltTable.getLength() == user.getLength()) {
                if (reseltTable.getTime() > time) {
                    reseltTable.setTime(time);
                    updateUser(user);

                }
                return user;
            }
        }
        return new UserDao().addResult(user.getName(), new ResultTable(user.getLength(), user.getKolBomb(), time));
    }

    public String getTopWinUser(User user) {

        String otvet = "";
        List<Result> list = userDao.getResultData(user.getLength(), user.getKolBomb());
        for (int i = 0; i < 10 && i < list.size(); i++) {
            otvet += "name : " + list.get(i).getUser().getName() + "\ntime (mc) : " + list.get(i).getTime() + "\n";
        }

        return otvet;
    }

    public List<Result> getTopUser(User user, int kol) {

        List<Result> list = userDao.getResultData(user.getLength(), user.getKolBomb());

        List<Result> result = new ArrayList();
        for (int i = 0; i < kol && i < list.size(); i++) {
         result.add(list.get(i));
        }

        return result;
    }
    public void getResultsPerClient(){

    }
}
