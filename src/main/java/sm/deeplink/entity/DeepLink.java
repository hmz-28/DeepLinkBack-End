package sm.deeplink.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "maxlinks")
public class DeepLink implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "linkname")
    private String linkname;
    @Column(name = "description")
    private String description;
    @Column(name = "customer")
    private String customer;
    @Column(name = "environment")
    private String environment;
    @Column(name = "profile")
    private String profile;
    @Column(name = "status")
    private String status;
    @Column(name = "editedby")
    private String editedby;
    @Column(name = "modificationdate")
    private Date modificationdate;
    @Column(name = "linkvalue", columnDefinition = "TEXT")
    private String linkvalue;
    private String cryptedlinkvalue;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @Fetch(FetchMode.JOIN)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;

    public DeepLink() {
    }

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


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEditedby() {
        return editedby;
    }

    public void setEditedby(String editedby) {
        this.editedby = editedby;
    }

    public Date getModificationdate() {
        return modificationdate;
    }

    public void setModificationdate(Date modificationdate) {
        this.modificationdate = modificationdate;
    }

    public String getCryptedlinkvalue() {
        return cryptedlinkvalue;
    }

    public void setCryptedlinkvalue(String cryptedlinkvalue) {
        this.cryptedlinkvalue = cryptedlinkvalue;
    }
}
