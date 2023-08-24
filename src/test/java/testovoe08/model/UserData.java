package testovoe08.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Бизнес-объект "Пользователь" - пользователь системы, под которым можно логиниться
 */
public class UserData {
    private String login;
    private String password;
    private Set<UserData> users = new HashSet<>();

    public UserData withLogin(String login) {
        this.login = login;
        return this;
    }

    public UserData withPassword(String password) {
        this.password = password;
        return this;
    }

    public String getLogin() {
        return this.login;
    }

    public String getPassword() {
        return this.password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserData userData = (UserData) o;
        return login.equals(userData.login) && password.equals(userData.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password);
    }

    @Override
    public String toString() {
        return "{" +
                "login='" + login + '\'' +
                " pass='" + password + '\'' +
                '}';
    }
}
