package com.onlinebankingloanmanagement.loanmanagement.service;

import com.onlinebankingloanmanagement.loanmanagement.dto.LoanApplicationRequest;
import com.onlinebankingloanmanagement.loanmanagement.dto.LoanApplicationResponse;
import com.onlinebankingloanmanagement.loanmanagement.model.ApplicationStatus;

import java.util.List;

/**
 * Service for managing loan applications.
 */
public interface LoanApplicationService {

    LoanApplicationResponse createLoanApplication(LoanApplicationRequest request, String username);

    LoanApplicationResponse getLoanApplicationById(Long id, String username);

    List<LoanApplicationResponse> getAllLoanApplicationsForUser(String username);

    List<LoanApplicationResponse> getAllLoanApplicationsByStatus(ApplicationStatus status);

    LoanApplicationResponse updateApplicationStatus(Long id, ApplicationStatus status, String adminUsername);

}