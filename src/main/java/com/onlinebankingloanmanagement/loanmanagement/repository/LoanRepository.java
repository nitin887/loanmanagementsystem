package com.onlinebankingloanmanagement.loanmanagement.repository;

import com.onlinebankingloanmanagement.loanmanagement.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository for Loan entity.
 */
@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
    List<Loan> findByUserId(Long userId);

    Optional<Loan> findByLoanApplicationId(Long loanApplicationId);
}