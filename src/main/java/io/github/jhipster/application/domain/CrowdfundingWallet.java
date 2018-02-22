package io.github.jhipster.application.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.util.Objects;

import io.github.jhipster.application.domain.enumeration.TokensType;

/**
 * A CrowdfundingWallet.
 */
@Entity
@Table(name = "crowdfunding_wallet")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "crowdfundingwallet")
public class CrowdfundingWallet implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "tokens_type")
    private TokensType tokensType;

    @Column(name = "crowdfunding_amount")
    private Long crowdfundingAmount;

    @Column(name = "crowdfunding_address")
    private String crowdfundingAddress;

    @OneToOne
    @JoinColumn(unique = true)
    private Project project;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TokensType getTokensType() {
        return tokensType;
    }

    public CrowdfundingWallet tokensType(TokensType tokensType) {
        this.tokensType = tokensType;
        return this;
    }

    public void setTokensType(TokensType tokensType) {
        this.tokensType = tokensType;
    }

    public Long getCrowdfundingAmount() {
        return crowdfundingAmount;
    }

    public CrowdfundingWallet crowdfundingAmount(Long crowdfundingAmount) {
        this.crowdfundingAmount = crowdfundingAmount;
        return this;
    }

    public void setCrowdfundingAmount(Long crowdfundingAmount) {
        this.crowdfundingAmount = crowdfundingAmount;
    }

    public String getCrowdfundingAddress() {
        return crowdfundingAddress;
    }

    public CrowdfundingWallet crowdfundingAddress(String crowdfundingAddress) {
        this.crowdfundingAddress = crowdfundingAddress;
        return this;
    }

    public void setCrowdfundingAddress(String crowdfundingAddress) {
        this.crowdfundingAddress = crowdfundingAddress;
    }

    public Project getProject() {
        return project;
    }

    public CrowdfundingWallet project(Project project) {
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
        CrowdfundingWallet crowdfundingWallet = (CrowdfundingWallet) o;
        if (crowdfundingWallet.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), crowdfundingWallet.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CrowdfundingWallet{" +
            "id=" + getId() +
            ", tokensType='" + getTokensType() + "'" +
            ", crowdfundingAmount=" + getCrowdfundingAmount() +
            ", crowdfundingAddress='" + getCrowdfundingAddress() + "'" +
            "}";
    }
}
