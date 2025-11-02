package com.onlinebankingloanmanagement.loanmanagement.repository;

import com.onlinebankingloanmanagement.loanmanagement.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for Document entity.
 */
@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
    List<Document> findByLoanApplicationId(Long loanApplicationId);
}