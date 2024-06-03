package com.cdp.misrutinas.data;
import com.cdp.misrutinas.entidades.Usuario;

public class UserSession {
    private static UserSession instance;
    private Usuario currentUser;

    private UserSession() { }

    public static UserSession getInstance() {
        if (instance == null) {
            instance = new UserSession();
        }
        return instance;
    }

    public void setCurrentUser(Usuario user) {
        this.currentUser = user;
    }

    public Usuario getCurrentUser() {
        return currentUser;
    }
}
