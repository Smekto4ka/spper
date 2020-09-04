package ru.bersenev.miner.user.service;

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

}
public void updateUser(UsersTable user){
       userDao.updateUser(user);
}
}
