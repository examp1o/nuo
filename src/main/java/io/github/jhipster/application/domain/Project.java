package io.github.jhipster.application.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.util.Objects;

import io.github.jhipster.application.domain.enumeration.ProjectLevel;

/**
 * A Project.
 */
@Entity
@Table(name = "project")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "project")
public class Project implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "total_amount")
    private Long totalAmount;

    @Column(name = "tokens_amout")
    private Long tokensAmout;

    @Column(name = "tokens_hard_top")
    private Long tokensHardTop;

    @Column(name = "tokens_name")
    private String tokensName;

    @Column(name = "jhi_exchange")
    private String exchange;

    @Column(name = "concept")
    private String concept;

    @Column(name = "code")
    private String code;

    @Enumerated(EnumType.STRING)
    @Column(name = "crowdfunding_level")
    private ProjectLevel crowdfundingLevel;

    @Column(name = "crowdfunding_target")
    private Integer crowdfundingTarget;

    @Column(name = "depot_lock")
    private Integer depotLock;

    @Column(name = "remarks")
    private String remarks;

    @Column(name = "website")
    private String website;

    @Column(name = "illustration")
    private String illustration;

    @Column(name = "grade")
    private Integer grade;

    @Column(name = "crowdfunding_platform")
    private String crowdfundingPlatform;

    @Column(name = "issue_date")
    private Long issueDate;

    @Column(name = "issue_end_date")
    private Long issueEndDate;

    @Column(name = "service_charge")
    private Integer serviceCharge;

    @Column(name = "min_crowdfunding_target")
    private Long minCrowdfundingTarget;

    @Column(name = "jhi_user")
    private Long user;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTotalAmount() {
        return totalAmount;
    }

    public Project totalAmount(Long totalAmount) {
        this.totalAmount = totalAmount;
        return this;
    }

    public void setTotalAmount(Long totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Long getTokensAmout() {
        return tokensAmout;
    }

    public Project tokensAmout(Long tokensAmout) {
        this.tokensAmout = tokensAmout;
        return this;
    }

    public void setTokensAmout(Long tokensAmout) {
        this.tokensAmout = tokensAmout;
    }

    public Long getTokensHardTop() {
        return tokensHardTop;
    }

    public Project tokensHardTop(Long tokensHardTop) {
        this.tokensHardTop = tokensHardTop;
        return this;
    }

    public void setTokensHardTop(Long tokensHardTop) {
        this.tokensHardTop = tokensHardTop;
    }

    public String getTokensName() {
        return tokensName;
    }

    public Project tokensName(String tokensName) {
        this.tokensName = tokensName;
        return this;
    }

    public void setTokensName(String tokensName) {
        this.tokensName = tokensName;
    }

    public String getExchange() {
        return exchange;
    }

    public Project exchange(String exchange) {
        this.exchange = exchange;
        return this;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getConcept() {
        return concept;
    }

    public Project concept(String concept) {
        this.concept = concept;
        return this;
    }

    public void setConcept(String concept) {
        this.concept = concept;
    }

    public String getCode() {
        return code;
    }

    public Project code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ProjectLevel getCrowdfundingLevel() {
        return crowdfundingLevel;
    }

    public Project crowdfundingLevel(ProjectLevel crowdfundingLevel) {
        this.crowdfundingLevel = crowdfundingLevel;
        return this;
    }

    public void setCrowdfundingLevel(ProjectLevel crowdfundingLevel) {
        this.crowdfundingLevel = crowdfundingLevel;
    }

    public Integer getCrowdfundingTarget() {
        return crowdfundingTarget;
    }

    public Project crowdfundingTarget(Integer crowdfundingTarget) {
        this.crowdfundingTarget = crowdfundingTarget;
        return this;
    }

    public void setCrowdfundingTarget(Integer crowdfundingTarget) {
        this.crowdfundingTarget = crowdfundingTarget;
    }

    public Integer getDepotLock() {
        return depotLock;
    }

    public Project depotLock(Integer depotLock) {
        this.depotLock = depotLock;
        return this;
    }

    public void setDepotLock(Integer depotLock) {
        this.depotLock = depotLock;
    }

    public String getRemarks() {
        return remarks;
    }

    public Project remarks(String remarks) {
        this.remarks = remarks;
        return this;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getWebsite() {
        return website;
    }

    public Project website(String website) {
        this.website = website;
        return this;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getIllustration() {
        return illustration;
    }

    public Project illustration(String illustration) {
        this.illustration = illustration;
        return this;
    }

    public void setIllustration(String illustration) {
        this.illustration = illustration;
    }

    public Integer getGrade() {
        return grade;
    }

    public Project grade(Integer grade) {
        this.grade = grade;
        return this;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getCrowdfundingPlatform() {
        return crowdfundingPlatform;
    }

    public Project crowdfundingPlatform(String crowdfundingPlatform) {
        this.crowdfundingPlatform = crowdfundingPlatform;
        return this;
    }

    public void setCrowdfundingPlatform(String crowdfundingPlatform) {
        this.crowdfundingPlatform = crowdfundingPlatform;
    }

    public Long getIssueDate() {
        return issueDate;
    }

    public Project issueDate(Long issueDate) {
        this.issueDate = issueDate;
        return this;
    }

    public void setIssueDate(Long issueDate) {
        this.issueDate = issueDate;
    }

    public Long getIssueEndDate() {
        return issueEndDate;
    }

    public Project issueEndDate(Long issueEndDate) {
        this.issueEndDate = issueEndDate;
        return this;
    }

    public void setIssueEndDate(Long issueEndDate) {
        this.issueEndDate = issueEndDate;
    }

    public Integer getServiceCharge() {
        return serviceCharge;
    }

    public Project serviceCharge(Integer serviceCharge) {
        this.serviceCharge = serviceCharge;
        return this;
    }

    public void setServiceCharge(Integer serviceCharge) {
        this.serviceCharge = serviceCharge;
    }

    public Long getMinCrowdfundingTarget() {
        return minCrowdfundingTarget;
    }

    public Project minCrowdfundingTarget(Long minCrowdfundingTarget) {
        this.minCrowdfundingTarget = minCrowdfundingTarget;
        return this;
    }

    public void setMinCrowdfundingTarget(Long minCrowdfundingTarget) {
        this.minCrowdfundingTarget = minCrowdfundingTarget;
    }

    public Long getUser() {
        return user;
    }

    public Project user(Long user) {
        this.user = user;
        return this;
    }

    public void setUser(Long user) {
        this.user = user;
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
        Project project = (Project) o;
        if (project.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), project.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Project{" +
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
