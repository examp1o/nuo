package io.github.jhipster.application.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Bulletin.
 */
@Entity
@Table(name = "bulletin")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "bulletin")
public class Bulletin implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content")
    private String content;

    @Column(name = "jhi_user")
    private Long user;

    @Column(name = "create_time")
    private Long createTime;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public Bulletin content(String content) {
        this.content = content;
        return this;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getUser() {
        return user;
    }

    public Bulletin user(Long user) {
        this.user = user;
        return this;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public Bulletin createTime(Long createTime) {
        this.createTime = createTime;
        return this;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Bulletin bulletin = (Bulletin) o;
        if (bulletin.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), bulletin.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Bulletin{" +
            "id=" + getId() +
            ", content='" + getContent() + "'" +
            ", user=" + getUser() +
            ", createTime=" + getCreateTime() +
            "}";
    }
}
