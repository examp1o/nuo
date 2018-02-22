package io.github.jhipster.application.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Investment.
 */
@Entity
@Table(name = "investment")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "investment")
public class Investment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "amount")
    private Long amount;

    @Column(name = "source_address")
    private String sourceAddress;

    @Column(name = "investment_time")
    private Long investmentTime;

    @Column(name = "jhi_user")
    private Long user;

    @ManyToOne
    private Project project;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAmount() {
        return amount;
    }

    public Investment amount(Long amount) {
        this.amount = amount;
        return this;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getSourceAddress() {
        return sourceAddress;
    }

    public Investment sourceAddress(String sourceAddress) {
        this.sourceAddress = sourceAddress;
        return this;
    }

    public void setSourceAddress(String sourceAddress) {
        this.sourceAddress = sourceAddress;
    }

    public Long getInvestmentTime() {
        return investmentTime;
    }

    public Investment investmentTime(Long investmentTime) {
        this.investmentTime = investmentTime;
        return this;
    }

    public void setInvestmentTime(Long investmentTime) {
        this.investmentTime = investmentTime;
    }

    public Long getUser() {
        return user;
    }

    public Investment user(Long user) {
        this.user = user;
        return this;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public Project getProject() {
        return project;
    }

    public Investment project(Project project) {
        this.project = project;
        return this;
    }

    public void setProject(Project project) {
        this.project = project;
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
        Investment investment = (Investment) o;
        if (investment.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), investment.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Investment{" +
            "id=" + getId() +
            ", amount=" + getAmount() +
            ", sourceAddress='" + getSourceAddress() + "'" +
            ", investmentTime=" + getInvestmentTime() +
            ", user=" + getUser() +
            "}";
    }
}
