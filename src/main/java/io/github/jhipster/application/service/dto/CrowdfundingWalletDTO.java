package io.github.jhipster.application.service.dto;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import io.github.jhipster.application.domain.enumeration.TokensType;

/**
 * A DTO for the CrowdfundingWallet entity.
 */
public class CrowdfundingWalletDTO implements Serializable {

    private Long id;

    private TokensType tokensType;

    private Long crowdfundingAmount;

    private String crowdfundingAddress;

    private Long projectId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TokensType getTokensType() {
        return tokensType;
    }

    public void setTokensType(TokensType tokensType) {
        this.tokensType = tokensType;
    }

    public Long getCrowdfundingAmount() {
        return crowdfundingAmount;
    }

    public void setCrowdfundingAmount(Long crowdfundingAmount) {
        this.crowdfundingAmount = crowdfundingAmount;
    }

    public String getCrowdfundingAddress() {
        return crowdfundingAddress;
    }

    public void setCrowdfundingAddress(String crowdfundingAddress) {
        this.crowdfundingAddress = crowdfundingAddress;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CrowdfundingWalletDTO crowdfundingWalletDTO = (CrowdfundingWalletDTO) o;
        if(crowdfundingWalletDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), crowdfundingWalletDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CrowdfundingWalletDTO{" +
            "id=" + getId() +
            ", tokensType='" + getTokensType() + "'" +
            ", crowdfundingAmount=" + getCrowdfundingAmount() +
            ", crowdfundingAddress='" + getCrowdfundingAddress() + "'" +
            "}";
    }
}
