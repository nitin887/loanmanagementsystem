package com.onlinebankingloanmanagement.loanmanagement.service.impl;

import com.onlinebankingloanmanagement.loanmanagement.dto.LoanResponse;
import com.onlinebankingloanmanagement.loanmanagement.exception.ResourceNotFoundException;
import com.onlinebankingloanmanagement.loanmanagement.mapper.LoanMapper;
import com.onlinebankingloanmanagement.loanmanagement.model.Loan;
import com.onlinebankingloanmanagement.loanmanagement.model.LoanApplication;
import com.onlinebankingloanmanagement.loanmanagement.model.LoanStatus;
import com.onlinebankingloanmanagement.loanmanagement.model.User;
import com.onlinebankingloanmanagement.loanmanagement.repository.LoanRepository;
import com.onlinebankingloanmanagement.loanmanagement.repository.UserRepository;
import com.onlinebankingloanmanagement.loanmanagement.service.LoanService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoanServiceImpl implements LoanService {

    private final LoanRepository loanRepository;
    private final UserRepository userRepository;
    private final LoanMapper loanMapper;

    public LoanServiceImpl(LoanRepository loanRepository, UserRepository userRepository, LoanMapper loanMapper) {
        this.loanRepository = loanRepository;
        this.userRepository = userRepository;
        this.loanMapper = loanMapper;
    }

    @Override
    @Transactional
    public Loan createLoanFromApplication(LoanApplication application) {
        Loan loan = new Loan();
        loan.setLoanApplication(application);
        loan.setUser(application.getUser());
        loan.setLoanType(application.getLoanType());
        loan.setPrincipalAmount(application.getAmountRequested());
        loan.setRemainingBalance(application.getAmountRequested());
        loan.setTermMonths(application.getTermMonths());
        loan.setStatus(LoanStatus.ACTIVE);
        loan.setStartDate(LocalDate.now());
        loan.setEndDate(LocalDate.now().plusMonths(application.getTermMonths()));

        // These would be determined by business rules, e.g., based on credit score
        loan.setInterestRate(new BigDecimal("5.00")); // Example interest rate

        // Simple monthly payment calculation (should be replaced with a proper formula)
        BigDecimal monthlyInterestRate = loan.getInterestRate().divide(new BigDecimal("1200"), 10, RoundingMode.HALF_UP);
        BigDecimal monthlyPayment = application.getAmountRequested().multiply(monthlyInterestRate)
                .divide(BigDecimal.ONE.subtract((BigDecimal.ONE.add(monthlyInterestRate)).pow(-application.getTermMonths())), 2, RoundingMode.HALF_UP);
        loan.setMonthlyPayment(monthlyPayment);

        return loanRepository.save(loan);
    }

    @Override
    public LoanResponse getLoanById(Long id, String username) {
        Loan loan = loanRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Loan", "id", id));
        // Add security check here
        return loanMapper.toDto(loan);
    }

    @Override
    public List<LoanResponse> getAllLoansForUser(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));
        return loanRepository.findByUserId(user.getId()).stream()
                .map(loanMapper::toDto)
                .collect(Collectors.toList());
    }
}