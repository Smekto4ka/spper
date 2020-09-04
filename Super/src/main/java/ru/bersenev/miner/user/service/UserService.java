package ru.bersenev.miner.user.service;

import ru.bersenev.miner.hibernate.ReseltTable;
import ru.bersenev.miner.hibernate.UserDao;
import ru.bersenev.miner.hibernate.UsersTable;

public class UserService {

   private UserDao userDao ;

   public UserService (){
       userDao = new UserDao();
   }

public UsersTable userAuthorization(String name){
       return userDao.findUserByName(name);
}
public void addUsers (UsersTable user){
       userDao.saveUser(user);
       updateUser(user);

}
public void updateUser(UsersTable user){
       userDao.updateUser(user);
}
public long addResult(UsersTable user , long time){

       for (ReseltTable reseltTable : user.getReseltTables()){
           if (reseltTable.getKolBomb()==user.getKolBomb()&& reseltTable.getLength()==user.getLength()){
               if (reseltTable.getTime()>time){
               reseltTable.setTime(time);
               updateUser(user);
               return time;
               }
               return reseltTable.getTime() ;
           }
       }
       user.addResult(new ReseltTable(user.getLength(),user.getKolBomb(),time));
    updateUser(user);
    return time;

}
}
