package org.levelup.web;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

public class AddUserForm {

    @NotBlank
    @Length(max = 50)
    private String userLogin;

    @NotBlank
    @Length(max = 50)
    private String password;

    @NotBlank
    @Length(max = 50)
    private String userName;

    private boolean isAdmin;

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public AddUserForm() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public AddUserForm(String userLogin, String password, String userName) {
        this.userLogin = userLogin;
        this.password = password;
        this.userName = userName;
        this.isAdmin = false;
    }
}
