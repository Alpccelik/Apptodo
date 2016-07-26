package hello.persistance;


import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class CheckItem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private String content;

    @NotNull
    @ColumnDefault("false")
    private boolean checked;

    @CreatedDate
    private Date createdDate;

    @CreatedBy
    @ManyToOne

    private User createdBy;

    @LastModifiedDate
    private Date lastModifiedDate;

    @LastModifiedBy
    @ManyToOne
    private User lastModifiedBy;

    public CheckItem() {
    }

    public CheckItem(long id) {
        this.id = id;
    }

    public CheckItem(String content, boolean checked) {
        this.content = content;
        this.checked = checked;
    }

    public CheckItem(String content, boolean checked, Date createdDate, User createdBy, Date lastModifiedDate, User lastModifiedBy) {
        this.content = content;
        this.checked = checked;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
        this.lastModifiedDate = lastModifiedDate;
        this.lastModifiedBy = lastModifiedBy;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public User getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(User lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }
}
