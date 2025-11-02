package com.onlinebankingloanmanagement.loanmanagement.dto;

import com.onlinebankingloanmanagement.loanmanagement.model.ApplicationStatus;
import com.onlinebankingloanmanagement.loanmanagement.model.LoanType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * DTO for displaying loan application details.
 */
public class LoanApplicationResponse {
    private Long id;
    private Long userId;
    private LoanType loanType;
    private BigDecimal amountRequested;
    private Integer termMonths;
    private String purpose;
    private ApplicationStatus status;
    private LocalDateTime applicationDate;
    private LocalDateTime decisionDate;
    // We can include document metadata here
    private Set<DocumentResponse> documents;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LoanType getLoanType() {
        return loanType;
    }

    public void setLoanType(LoanType loanType) {
        this.loanType = loanType;
    }

    public BigDecimal getAmountRequested() {
        return amountRequested;
    }

    public void setAmountRequested(BigDecimal amountRequested) {
        this.amountRequested = amountRequested;
    }

    public Integer getTermMonths() {
        return termMonths;
    }

    public void setTermMonths(Integer termMonths) {
        this.termMonths = termMonths;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }

    public LocalDateTime getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(LocalDateTime applicationDate) {
        this.applicationDate = applicationDate;
    }

    public LocalDateTime getDecisionDate() {
        return decisionDate;
    }

    public void setDecisionDate(LocalDateTime decisionDate) {
        this.decisionDate = decisionDate;
    }

    public Set<DocumentResponse> getDocuments() {
        return documents;
    }

    public void setDocuments(Set<DocumentResponse> documents) {
        this.documents = documents;
    }
}