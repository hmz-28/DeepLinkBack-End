package sm.deeplink.model;

public class AuthToken {

    private String token;
    private String username;
    private Long userid;

    public AuthToken() {

    }

    public AuthToken(String token, Long userid) {
        this.token = token;
        this.username = username;
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

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }
}
