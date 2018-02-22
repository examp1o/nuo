package io.github.jhipster.application.service.dto;


import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Bulletin entity.
 */
public class BulletinDTO implements Serializable {

    private Long id;

    private String content;

    private Long user;

    private Long createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BulletinDTO bulletinDTO = (BulletinDTO) o;
        if(bulletinDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), bulletinDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "BulletinDTO{" +
            "id=" + getId() +
            ", content='" + getContent() + "'" +
            ", user=" + getUser() +
            ", createTime=" + getCreateTime() +
            "}";
    }
}
