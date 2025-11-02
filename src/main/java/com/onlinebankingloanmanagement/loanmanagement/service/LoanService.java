package com.onlinebankingloanmanagement.loanmanagement.service;

import com.onlinebankingloanmanagement.loanmanagement.dto.LoanResponse;
import com.onlinebankingloanmanagement.loanmanagement.model.Loan;
import com.onlinebankingloanmanagement.loanmanagement.model.LoanApplication;

import java.util.List;

/**
 * Service for managing approved loans.
 */
public interface LoanService {
    Loan createLoanFromApplication(LoanApplication loanApplication);

    LoanResponse getLoanById(Long id, String username);

    List<LoanResponse> getAllLoansForUser(String username);
}