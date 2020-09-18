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
    public List<Object[]> getWinUserMySettings(User user, int kol) {
        return userDao.getResultMySettings(user, kol);
    }

    public String[] getStringColumn() {
        List list;


        list = userDao.getListName();
        String[] strings = new String[list.size() + 1];
        strings[0] = null;
        for (int i = 0; i < list.size(); i++) {
            strings[i + 1] = (String) list.get(i);
        }
        return strings;

    }

    public Integer[] getIntegerColumn(String column) {
        List list;

        switch (column) {

            case "length":
                list = userDao.getListLength();
                break;
            case "kolBomb":
                list = userDao.getListKolBomb();
                break;
            default:
                list = new ArrayList<String>();
                list.add(null);
                System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
                break;
        }

        Integer[] strings = new Integer[list.size() + 1];
        strings[0] = null;
        for (int i = 0; i < list.size(); i++) {
            strings[i + 1] = (int) list.get(i);
        }
        return strings;
    }

    private List<Object[]> getListAddRating(List<Object[]> listObject) {
        List<Object[]> listResult = new ArrayList<>();
        for (Object[] objects : listObject) {
            listResult.add(new Object[]{objects[0], objects[1], objects[2], objects[3], ((int) objects[1]*10000000 / ((int) objects[2] * (long) objects[3]))});
        }
        return listResult;
    }

    public List<Object[]> getResultBy(String comboBoxName, Integer comboBoxLength, Integer comboBoxKolBomb) {
        List list = null;
        if (comboBoxName == null && comboBoxLength == null && comboBoxKolBomb == null) {
            list = userDao.getListForResultsWin();
        }
        if (comboBoxName != null && comboBoxLength == null && comboBoxKolBomb == null) {
            list = userDao.getResultByName(comboBoxName);
        }
        if (comboBoxName == null && comboBoxLength != null && comboBoxKolBomb == null) {
            list = userDao.getResultByLength(comboBoxLength);
        }
        if (comboBoxName == null && comboBoxLength == null && comboBoxKolBomb != null) {
            list = userDao.getResultByKolBomb(comboBoxKolBomb);
        }
        if (comboBoxName != null && comboBoxLength != null && comboBoxKolBomb == null) {
            list = userDao.getResultByNameAndLength(comboBoxName, comboBoxLength);
        }
        if (comboBoxName != null && comboBoxLength == null && comboBoxKolBomb != null) {
            list = userDao.getResultByNameAndKolBomb(comboBoxName, comboBoxKolBomb);
        }
        if (comboBoxName == null && comboBoxLength != null && comboBoxKolBomb != null) {
            list = userDao.getResultByKolBombAndLength(comboBoxKolBomb, comboBoxLength);
        }
        if (comboBoxName != null && comboBoxLength != null && comboBoxKolBomb != null) {
            list = userDao.getResultByNameAndLengthAndKolBomb(comboBoxName, comboBoxLength, comboBoxKolBomb);
        }
        if (list.size() == 0 || list == null) {
            List<Object[]> listNull = new ArrayList();
            String[] strings = {"null", "null", "null", "null", "null"};
            listNull.add(strings);
            return listNull;
        }
        return getListAddRating(list);
    }
}
