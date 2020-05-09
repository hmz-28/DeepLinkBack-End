package sm.deeplink.entity;


import javax.persistence.*;

@Entity
@Table(name = "maxuser")

public class User {

    @Id
    @Column(name = "userid", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Column(name = "username", nullable = false)
    private String username;
    @Column(name = "password", nullable = false)
    private String Password;
    @Column(name = "email", nullable = true)
    private String Email;

    @Column(name = "company", nullable = true)
    private String Company;

    public User() {
    }

    public Long getUserId() {
        return userId;
    }


    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        this.Password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getCompany() {
        return Company;
    }

    public void setCompany(String company) {
        Company = company;
    }
}
