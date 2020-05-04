package sm.deeplink.entity;


import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "maxuser")

public class User implements Serializable , UserDetails {
    private static final long serialVersionUID = 4048798961366546485L;

    public User() {
    }

    @Id
    @Column(name = "userid", nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "username", nullable = false)
    private  String UserName;

    @Column(name = "password", nullable = false)
    private  String Password;

    public User(Long userId, String userName, String password) {
        this.userId = userId;
        UserName = userName;
        Password = password;
    }

    public User(String userName, String password) {
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public String getPassword() {
        return Password;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
