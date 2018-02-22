package io.github.jhipster.application.service.dto;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Investment entity.
 */
public class InvestmentDTO implements Serializable {

    private Long id;

    private Long amount;

    private String sourceAddress;

    private Long investmentTime;

    private Long user;

    private Long projectId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getSourceAddress() {
        return sourceAddress;
    }

    public void setSourceAddress(String sourceAddress) {
        this.sourceAddress = sourceAddress;
    }

    public Long getInvestmentTime() {
        return investmentTime;
    }

    public void setInvestmentTime(Long investmentTime) {
        this.investmentTime = investmentTime;
    }

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
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

        InvestmentDTO investmentDTO = (InvestmentDTO) o;
        if(investmentDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), investmentDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "InvestmentDTO{" +
            "id=" + getId() +
            ", amount=" + getAmount() +
            ", sourceAddress='" + getSourceAddress() + "'" +
            ", investmentTime=" + getInvestmentTime() +
            ", user=" + getUser() +
            "}";
    }
}
