package database;

import dao.UserDao;
import model.data.User;
import model.data.beans.UserInfo;

import java.util.List;

/**
 * Created by david on 10/3/16.
 */
public class UsersDataBaseManager {

    private static UserDao userDao;

    public static void createDao( String persistenceUnit ) {
        userDao = new UserDao( persistenceUnit );
    }

    public static List<User> getUsers() {
        return userDao.getUsers();
    }

    public static User getUser( Long id ) {
        return userDao.getUser( id );
    }

    public static User getUser( String username ) {
        return userDao.getUser( username );
    }

    public static UserInfo getUserCheckingPassword( String username, String password ) {
        return userDao.getUserCheckingPassword( username, password );
    }

    public static void newUser( User user ) {
        userDao.newUser( user );
    }

    public static void deleteUser( User user ) {
        userDao.deleteUser( user );
    }

    public static boolean deleteUser( Long id ) {
        return userDao.deleteUser( id );
    }

    public static boolean deleteUser( String username ) {
        return userDao.deleteUser( username );
    }
}
