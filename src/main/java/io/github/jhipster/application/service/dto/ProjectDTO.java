package io.github.jhipster.application.service.dto;


import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.application.domain.enumeration.ProjectLevel;

/**
 * A DTO for the Project entity.
 */
public class ProjectDTO implements Serializable {

    private Long id;

    private Long totalAmount;

    private Long tokensAmout;

    private Long tokensHardTop;

    private String tokensName;

    private String exchange;

    private String concept;

    private String code;

    private ProjectLevel crowdfundingLevel;

    private Integer crowdfundingTarget;

    private Integer depotLock;

    private String remarks;

    private String website;

    private String illustration;

    private Integer grade;

    private String crowdfundingPlatform;

    private Long issueDate;

    private Long issueEndDate;

    private Integer serviceCharge;

    private Long minCrowdfundingTarget;

    private Long user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Long totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Long getTokensAmout() {
        return tokensAmout;
    }

    public void setTokensAmout(Long tokensAmout) {
        this.tokensAmout = tokensAmout;
    }

    public Long getTokensHardTop() {
        return tokensHardTop;
    }

    public void setTokensHardTop(Long tokensHardTop) {
        this.tokensHardTop = tokensHardTop;
    }

    public String getTokensName() {
        return tokensName;
    }

    public void setTokensName(String tokensName) {
        this.tokensName = tokensName;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getConcept() {
        return concept;
    }

    public void setConcept(String concept) {
        this.concept = concept;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ProjectLevel getCrowdfundingLevel() {
        return crowdfundingLevel;
    }

    public void setCrowdfundingLevel(ProjectLevel crowdfundingLevel) {
        this.crowdfundingLevel = crowdfundingLevel;
    }

    public Integer getCrowdfundingTarget() {
        return crowdfundingTarget;
    }

    public void setCrowdfundingTarget(Integer crowdfundingTarget) {
        this.crowdfundingTarget = crowdfundingTarget;
    }

    public Integer getDepotLock() {
        return depotLock;
    }

    public void setDepotLock(Integer depotLock) {
        this.depotLock = depotLock;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getIllustration() {
        return illustration;
    }

    public void setIllustration(String illustration) {
        this.illustration = illustration;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getCrowdfundingPlatform() {
        return crowdfundingPlatform;
    }

    public void setCrowdfundingPlatform(String crowdfundingPlatform) {
        this.crowdfundingPlatform = crowdfundingPlatform;
    }

    public Long getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Long issueDate) {
        this.issueDate = issueDate;
    }

    public Long getIssueEndDate() {
        return issueEndDate;
    }

    public void setIssueEndDate(Long issueEndDate) {
        this.issueEndDate = issueEndDate;
    }

    public Integer getServiceCharge() {
        return serviceCharge;
    }

    public void setServiceCharge(Integer serviceCharge) {
        this.serviceCharge = serviceCharge;
    }

    public Long getMinCrowdfundingTarget() {
        return minCrowdfundingTarget;
    }

    public void setMinCrowdfundingTarget(Long minCrowdfundingTarget) {
        this.minCrowdfundingTarget = minCrowdfundingTarget;
    }

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProjectDTO projectDTO = (ProjectDTO) o;
        if(projectDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), projectDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ProjectDTO{" +
            "id=" + getId() +
            ", totalAmount=" + getTotalAmount() +
            ", tokensAmout=" + getTokensAmout() +
            ", tokensHardTop=" + getTokensHardTop() +
            ", tokensName='" + getTokensName() + "'" +
            ", exchange='" + getExchange() + "'" +
            ", concept='" + getConcept() + "'" +
            ", code='" + getCode() + "'" +
            ", crowdfundingLevel='" + getCrowdfundingLevel() + "'" +
            ", crowdfundingTarget=" + getCrowdfundingTarget() +
            ", depotLock=" + getDepotLock() +
            ", remarks='" + getRemarks() + "'" +
            ", website='" + getWebsite() + "'" +
            ", illustration='" + getIllustration() + "'" +
            ", grade=" + getGrade() +
            ", crowdfundingPlatform='" + getCrowdfundingPlatform() + "'" +
            ", issueDate=" + getIssueDate() +
            ", issueEndDate=" + getIssueEndDate() +
            ", serviceCharge=" + getServiceCharge() +
            ", minCrowdfundingTarget=" + getMinCrowdfundingTarget() +
            ", user=" + getUser() +
            "}";
    }
}
