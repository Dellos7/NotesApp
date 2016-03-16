package dao;

import model.data.User;
import model.data.beans.UserInfo;

import javax.persistence.*;
import java.util.List;

/**
 * Created by david on 10/3/16.
 */
public class UserDao {

    private EntityManagerFactory emf;
    private EntityManager em;
    private EntityTransaction tx;

    public UserDao( String persistenceUnit ) {
        super();
        emf = Persistence.createEntityManagerFactory( persistenceUnit );
        em = emf.createEntityManager();
        tx = em.getTransaction();
    }

    public List<User> getUsers() {
        Query query = em.createNamedQuery( User.RETRIEVE_ALL );
        List<User> users = query.getResultList();
        return users;
    }

    public User getUser( Long id ) {
        User user = null;
        Query query = em.createNamedQuery( User.RETRIEVE_BY_ID );
        query.setParameter( "id", id );
        List<User> usersList = query.getResultList();
        if( !usersList.isEmpty() ) {
            return usersList.get( 0 );
        }
        return user;
    }

    public User getUser( String username ) {
        User user = null;
        Query query = em.createNamedQuery( User.RETRIEVE_BY_USERNAME );
        query.setParameter( "username", username );
        List<User> usersList = query.getResultList();
        if( !usersList.isEmpty() ) {
            return usersList.get( 0 );
        }
        return user;
    }

    public UserInfo getUserCheckingPassword( String username, String encodedPassword ) {
        User user = null;
        UserInfo userInfo = new UserInfo();
        Query query = em.createNamedQuery( User.CHECK_PASSWORD );
        query.setParameter( "username", username );
        query.setParameter( "password", encodedPassword );
        List<User> usersList = query.getResultList();
        if( !usersList.isEmpty() ) {
            user = usersList.get( 0 );
            userInfo.setUsername( user.getUsername() );
            userInfo.setUserRole( user.getRole() );
            userInfo.setAuthenticated( true );
        }
        return userInfo;
    }

    public void newUser( User user ) {
        tx.begin();
        em.persist( user );
        tx.commit();
    }

    public void deleteUser( User user ) {
        tx.begin();
        em.remove( user );
        tx.commit();
    }

    public boolean deleteUser( Long id ) {
        User user = getUser( id );
        if( user != null ) {
            deleteUser( user );
            return true;
        }
        return false;
    }

    public boolean deleteUser( String username ) {
        User user = getUser( username );
        if( user != null ) {
            deleteUser( user );
            return true;
        }
        return false;
    }

}
