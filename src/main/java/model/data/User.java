package model.data;

import annotations.Secured;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by david on 9/3/16.
 */
@XmlRootElement
@XmlType( propOrder = { "id", "username", "role" } )
@Entity
@Table( name = "users" )
@NamedQueries(value = {
        @NamedQuery( name = User.RETRIEVE_ALL, query = "SELECT u FROM User u"),
        @NamedQuery( name = User.RETRIEVE_BY_ID, query = "SELECT u FROM User u WHERE u.id = :id"),
        @NamedQuery( name = User.RETRIEVE_BY_USERNAME, query = "SELECT u FROM User u WHERE u.username = :username" ),
        @NamedQuery( name =  User.CHECK_PASSWORD, query = "SELECT u FROM User u WHERE u.username = :username AND u.password = :password" )
})
public class User {

    public static final String RETRIEVE_ALL = "Retrieve all users";
    public static final String RETRIEVE_BY_ID = "Retrieve user by id";
    public static final String RETRIEVE_BY_USERNAME = "Retrieve user by username";
    public static final String CHECK_PASSWORD = "Check password";

    @Id
    @GeneratedValue( strategy =  GenerationType.AUTO )
    @Column( name = "id" )
    private Long id;
    @Column( name = "username", nullable = false )
    private String username;
    @Column( name = "password", nullable =  false )
    private String password;
    @Column( name = "role", nullable = false )
    @Enumerated(EnumType.STRING)
    private Secured.Role role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Secured.Role getRole() {
        return role;
    }

    public void setRole(Secured.Role role) {
        this.role = role;
    }

    @Override
    public boolean equals( Object obj ) {
        User other = (User) obj;
        if( this.id == other.getId() ) {
            return true;
        }
        else if( this.username.equals( other.getUsername() ) ) {
            return true;
        }
        return false;
    }
}
