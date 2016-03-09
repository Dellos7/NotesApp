package model.data.beans;

import annotations.Secured;

/**
 * Created by david on 9/3/16.
 */
public class UserInfo {

    private String username;
    private Secured.Role userRole;
    private boolean isAuthenticated;

    public UserInfo() {
        this.username = null;
        this.userRole = null;
        this.isAuthenticated = false;
    }

    public String getUsername() {
        return username;

    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Secured.Role getUserRole() {
        return userRole;
    }

    public void setUserRole(Secured.Role userRole) {
        this.userRole = userRole;
    }

    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        isAuthenticated = authenticated;
    }
}