package com.onlinebankingloanmanagement.loanmanagement.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a single payment made towards a loan.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "loan_id", nullable = false)
    private Loan loan;

    private LocalDateTime paymentDate;

    @Column(nullable = false)
    private BigDecimal amountPaid;

    private BigDecimal principalComponent;
    private BigDecimal interestComponent;

    @Column(unique = true)
    private String transactionId;

}