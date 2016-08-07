package hello.persistance;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Todo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    @OneToMany
    private List<CheckItem> items;

    @ManyToMany
    private Set<User> sharingWith;

    @CreatedBy
    @ManyToOne
    private User createdBy;

    @CreatedDate
    private Date createdDate;

    @LastModifiedBy
    @ManyToOne
    private User lastModifiedBy;

    @LastModifiedDate
    private Date lastModifiedDate;

    public Todo() {
        items = new ArrayList<>();
        sharingWith = new HashSet<>();
    }

    public Todo(long id) {
        this.id = id;
    }

    public Todo(String name, List<CheckItem> items, Set<User> sharingWith) {
        this.name = name;
        this.items = items;
        this.sharingWith = sharingWith;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CheckItem> getItems() {
        return items;
    }

    public void setItems(List<CheckItem> items) {
        this.items = items;
    }

    public Set<User> getSharingWith() {
        return sharingWith;
    }

    public void setSharingWith(Set<User> sharingWith) {
        this.sharingWith = sharingWith;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public User getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(User lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}
