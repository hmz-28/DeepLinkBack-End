package sm.deeplink.entity;

import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;

public class User {
    @Id
    @GeneratedValue
    @Column(name = "userid", nullable = false)
    private Long userId;
    @Column(name = "username", length = 36, nullable = false)
    private  String UserName;
    @Column(name = "Password", length = 128, nullable = false)
    private  String Password;

    public User() {
    }

    public User(Long userId, String userName, String password) {
        this.userId = userId;
        UserName = userName;
        Password = password;
    }

    public Long getUserId() {
        return userId;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
