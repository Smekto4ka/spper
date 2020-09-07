package ru.bersenev.miner.user.service;

import ru.bersenev.miner.hibernate.ReseltTable;
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

        for (Result reseltTable : user.getReseltTables()) {
            if (reseltTable.getKolBomb() == user.getKolBomb() && reseltTable.getLength() == user.getLength()) {
                if (reseltTable.getTime() > time) {
                    reseltTable.setTime(time);
                    updateUser(user);

                }
                return user;
            }
        }
        return new UserDao().addResult(user.getName(), new ReseltTable(user.getLength(), user.getKolBomb(), time));
    }

public void getTopWinUser(User user){
    List<ReseltTable> list = userDao.getResultData(user.getLength(),user.getKolBomb());

    for(ReseltTable res : list){
        System.out.println(res.getUser().getName() + " " + Long.toString(res.getTime()));
    }

}
}
