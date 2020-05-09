package sm.deeplink.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "maxlinks")
public class DeepLink implements Serializable {

    public DeepLink() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "linkname")
    private String linkname;

    @Column(name = "linkvalue",columnDefinition="TEXT")
    private String  linkvalue;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    @Fetch(FetchMode.JOIN)
    private User user;

    public DeepLink(String linkname, String linkvalue) {
        this.linkname = linkname;
        this.linkvalue = linkvalue;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLinkname() {
        return linkname;
    }

    public void setLinkname(String linkname) {
        this.linkname = linkname;
    }

    public String getLinkvalue() {
        return linkvalue;
    }

    public void setLinkvalue(String linkvalue) {
        this.linkvalue = linkvalue;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
