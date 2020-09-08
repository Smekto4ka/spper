package ru.bersenev.miner.user.service;

import ru.bersenev.miner.hibernate.ReseltTable;
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
                converterResultTable(usersTable.getReseltTables(), usersTable.getName())
        );
    }

    private List<Result> converterResultTable(List<ReseltTable> reseltTable, String name) {
        // if (reseltTable instanceof ArrayList){
        List<Result> results = new ArrayList<Result>();
      /* }else{
           List<Result> results = new List<Result>();
       }*/
        for (ReseltTable res : reseltTable) {

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
        List<ReseltTable> resTab = usersTable.getReseltTables();
        List<Result> res = user.getReseltTables();

        for ( int i = 0 ; i< usersTable.getReseltTables().size(); i ++){
            if (resTab.get(i).getTime()!=res.get(i).getTime() ){
                resTab.get(i).setTime(res.get(i).getTime());
            }
        }
        return  usersTable;
    }

}
