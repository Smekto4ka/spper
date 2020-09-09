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
                converterResultTable(usersTable.getResultTables(), usersTable.getName())
        );
    }

    private List<Result> converterResultTable(List<ResultTable> resultTable, String name) {
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
                        name
                ));
        }
        return results;
    }

    public UsersTable converterUser(User user, UsersTable usersTable){

        usersTable.setLength(user.getLength());
        usersTable.setKolBomb(user.getKolBomb());
        usersTable.setNomRadioButton(user.getNomRadioButton());
        usersTable.setPercent(user.getPercent());
        List<ResultTable> resTab = usersTable.getResultTables();
        List<Result> res = user.getReseltTables();

        for (int i = 0; i< usersTable.getResultTables().size(); i ++){
            if (resTab.get(i).getTime()!=res.get(i).getTime() ){
                resTab.get(i).setTime(res.get(i).getTime());
            }
        }
        return  usersTable;
    }

}
