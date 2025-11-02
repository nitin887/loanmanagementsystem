package com.onlinebankingloanmanagement.loanmanagement.dto;

import com.onlinebankingloanmanagement.loanmanagement.model.LoanStatus;
import com.onlinebankingloanmanagement.loanmanagement.model.LoanType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

/**
 * DTO for displaying details of an approved loan.
 */
public class LoanResponse {
    private Long id;
    private Long userId;
    private Long loanApplicationId;
    private LoanType loanType;
    private BigDecimal principalAmount;
    private BigDecimal interestRate;
    private Integer termMonths;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal monthlyPayment;
    private BigDecimal remainingBalance;
    private LoanStatus status;
    private Set<PaymentResponse> payments;

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

    public Long getLoanApplicationId() {
        return loanApplicationId;
    }

    public void setLoanApplicationId(Long loanApplicationId) {
        this.loanApplicationId = loanApplicationId;
    }

    public LoanType getLoanType() {
        return loanType;
    }

    public void setLoanType(LoanType loanType) {
        this.loanType = loanType;
    }

    public BigDecimal getPrincipalAmount() {
        return principalAmount;
    }

    public void setPrincipalAmount(BigDecimal principalAmount) {
        this.principalAmount = principalAmount;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public Integer getTermMonths() {
        return termMonths;
    }

    public void setTermMonths(Integer termMonths) {
        this.termMonths = termMonths;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getMonthlyPayment() {
        return monthlyPayment;
    }

    public void setMonthlyPayment(BigDecimal monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }

    public BigDecimal getRemainingBalance() {
        return remainingBalance;
    }

    public void setRemainingBalance(BigDecimal remainingBalance) {
        this.remainingBalance = remainingBalance;
    }

    public LoanStatus getStatus() {
        return status;
    }

    public void setStatus(LoanStatus status) {
        this.status = status;
    }

    public Set<PaymentResponse> getPayments() {
        return payments;
    }

    public void setPayments(Set<PaymentResponse> payments) {
        this.payments = payments;
    }
}