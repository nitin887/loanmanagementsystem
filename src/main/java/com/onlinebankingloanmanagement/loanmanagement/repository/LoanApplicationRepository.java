package com.onlinebankingloanmanagement.loanmanagement.repository;

import com.onlinebankingloanmanagement.loanmanagement.model.ApplicationStatus;
import com.onlinebankingloanmanagement.loanmanagement.model.LoanApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for LoanApplication entity.
 */
@Repository
public interface LoanApplicationRepository extends JpaRepository<LoanApplication, Long> {

    List<LoanApplication> findByUserId(Long userId);

    List<LoanApplication> findByStatus(ApplicationStatus status);
}