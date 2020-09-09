package ru.bersenev.miner.user.service;

import ru.bersenev.miner.hibernate.ResultTable;
import ru.bersenev.miner.hibernate.UsersTable;

import java.util.ArrayList;
import java.util.List;

public class ConverterData {
    public User converterUsersTable(UsersTable usersTable) {


        return new User(
                usersTable.getName(),
                usersTable.getLength(),
                usersTable.getKolBomb(),
                usersTable.getPercent(),
                usersTable.getNomRadioButton(),
                converterThisResultTable(usersTable.getResultTables(), usersTable.getName())
        );
    }

    public List<User> converterUsersTable(List<UsersTable> usersTableList) {
        List<User> user = new ArrayList();
        for (UsersTable usersTable : usersTableList) {
            user.add(new User(
                    usersTable.getName(),
                    usersTable.getLength(),
                    usersTable.getKolBomb(),
                    usersTable.getPercent(),
                    usersTable.getNomRadioButton(),
                    converterThisResultTable(usersTable.getResultTables(), usersTable.getName())
            ));
        }
        return user;
    }

    private List<Result> converterThisResultTable(List<ResultTable> resultTable, String name) {
        // if (reseltTable instanceof ArrayList){
        List<Result> results = new ArrayList<Result>();
      /* }else{
           List<Result> results = new List<Result>();
       }*/
        for (ResultTable res : resultTable) {

            results.add(new Result(
                    res.getId(),
                    res.getLength(),
                    res.getKolBomb(),
                    res.getTime()

            ));
        }
        return results;
    }

    public List<Result> converterResultTable(List<ResultTable> resultTable) {
        // if (reseltTable instanceof ArrayList){
        List<Result> results = new ArrayList<Result>();
      /* }else{
           List<Result> results = new List<Result>();
       }*/
        for (ResultTable res : resultTable) {

            results.add(new Result(
                    res.getId(),
                    res.getLength(),
                    res.getKolBomb(),
                    res.getTime(),
                    converterThisUsersTable(res.getUser())
            ));
        }
        return results;
    }

   private User converterThisUsersTable(UsersTable usersTable) {


        return new User(
                usersTable.getName(),
                usersTable.getLength(),
                usersTable.getKolBomb(),
                usersTable.getPercent(),
                usersTable.getNomRadioButton()
        );
    }


    public UsersTable converterUser(User user, UsersTable usersTable) {

        usersTable.setLength(user.getLength());
        usersTable.setKolBomb(user.getKolBomb());
        usersTable.setNomRadioButton(user.getNomRadioButton());
        usersTable.setPercent(user.getPercent());
        List<ResultTable> resTab = usersTable.getResultTables();
        List<Result> res = user.getResultTables();

        for (int i = 0; i < usersTable.getResultTables().size(); i++) {
            if (resTab.get(i).getTime() != res.get(i).getTime()) {
                resTab.get(i).setTime(res.get(i).getTime());
            }
        }
        return usersTable;
    }

}
