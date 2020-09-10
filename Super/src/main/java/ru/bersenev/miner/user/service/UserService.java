package ru.bersenev.miner.user.service;

import ru.bersenev.miner.hibernate.ResultTable;
import ru.bersenev.miner.hibernate.UserDao;
import ru.bersenev.miner.hibernate.UsersTable;

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
        return new UserDao().addResult(user.getName(), new ResultTable(user.getLength(), user.getKolBomb(), time));
    }

    public String getTopWinUser(User user, int kolData) {
        StringBuilder str = new StringBuilder();
        List<Object[]> listResult = userDao.getTopWinResult(user, kolData);
        for (Object[] mass : listResult) {
            for (Object objekt : mass) {
                str.append(objekt).append("\n");
            }
        }

        return str.toString();
    }

    public String getMinResult(User user) {
        return userDao.getMinTime(user);
    }

  /*  public List<Result> getTopUser(User user, int kol) {

        List<Result> list = userDao.getResultData(user.getLength(), user.getKolBomb());

        List<Result> result = new ArrayList();
        for (int i = 0; i < kol && i < list.size(); i++) {
            result.add(list.get(i));
        }

        return result;
    }

    public void getResultsPerClient() {

    }*/
}
