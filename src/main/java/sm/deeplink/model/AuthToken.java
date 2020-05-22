package sm.deeplink.model;

import sm.deeplink.entity.User;

public class AuthToken {

    private String token;
    private String username;
    private User user;

    public AuthToken() {

    }

    public AuthToken(String token, User user) {
        this.token = token;
        this.user = user;
    }

    public AuthToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
