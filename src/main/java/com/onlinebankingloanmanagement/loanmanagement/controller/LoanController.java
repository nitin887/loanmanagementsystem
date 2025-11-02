package com.onlinebankingloanmanagement.loanmanagement.controller;

import com.onlinebankingloanmanagement.loanmanagement.dto.LoanResponse;
import com.onlinebankingloanmanagement.loanmanagement.service.LoanService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @GetMapping
    public ResponseEntity<List<LoanResponse>> getAllLoansForCurrentUser(@AuthenticationPrincipal UserDetails userDetails) {
        List<LoanResponse> loans = loanService.getAllLoansForUser(userDetails.getUsername());
        return ResponseEntity.ok(loans);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoanResponse> getLoanById(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
        LoanResponse loan = loanService.getLoanById(id, userDetails.getUsername());
        return ResponseEntity.ok(loan);
    }
}