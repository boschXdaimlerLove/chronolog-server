package com.bxdlove.worktimeserver.beans;

import com.bxdlove.worktimeserver.api.security.CredentialUtils;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

import java.io.Serial;
import java.io.Serializable;

/**
 * Session scoped bean for the admin login.
 * This bean can be used to check if user is admin: validate access to admin panel.
 *
 * @author Gregor Gottschewski
 */
@Named
@SessionScoped
public class AdminLoginBean implements Serializable {
    
    @Serial
    private static final long serialVersionUID = 1L;
    
    private String email;
    private String password;
    private boolean loggedIn = false;
    
    public String login() {
        if (CredentialUtils.validateAdmin(email, password)) {
            loggedIn = true;
            return "dashboard?faces-redirect=true";
        }

        FacesContext.getCurrentInstance().addMessage(
                null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid credentials", "Email or password is incorrect")
        );
        return null;
    }
    
    public String logout() {
        loggedIn = false;
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "login?faces-redirect=true";
    }
    
    public boolean isLoggedIn() {
        return loggedIn;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}