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

    public String getTopWinUser(User user) {
        List<ReseltTable> list = userDao.getResultData(user.getLength(), user.getKolBomb());

        long time;
        int nom;
        String otvet = "";
        for (int i = 0; i < 10 ; i++) {
            if (list.size()==0){
                break;
            }
            time = list.get(0).getTime();
            nom = 0;

            for (int j = 1 ; j < list.size() ; j ++) {
                if(list.get(j).getTime()<time){
                    nom = j;
                    time = list.get(j).getTime();
                }
            }
            otvet += "name : " + list.remove(nom).getUser().getName() + "\ntime (mc) : " +time+ "\n";
        }
return otvet;

    }
}
