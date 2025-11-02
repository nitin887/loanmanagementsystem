package com.onlinebankingloanmanagement.loanmanagement.controller;

import com.onlinebankingloanmanagement.loanmanagement.dto.LoanApplicationRequest;
import com.onlinebankingloanmanagement.loanmanagement.dto.LoanApplicationResponse;
import com.onlinebankingloanmanagement.loanmanagement.model.ApplicationStatus;
import com.onlinebankingloanmanagement.loanmanagement.service.LoanApplicationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
public class LoanApplicationController {

    private final LoanApplicationService loanApplicationService;

    public LoanApplicationController(LoanApplicationService loanApplicationService) {
        this.loanApplicationService = loanApplicationService;
    }

    // Endpoint for customers to create a loan application
    @PostMapping
    public ResponseEntity<LoanApplicationResponse> createLoanApplication(@Valid @RequestBody LoanApplicationRequest request,
                                                                         @AuthenticationPrincipal UserDetails userDetails) {
        LoanApplicationResponse response = loanApplicationService.createLoanApplication(request, userDetails.getUsername());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Endpoint for customers to get their own applications
    @GetMapping
    public ResponseEntity<List<LoanApplicationResponse>> getCurrentUserLoanApplications(@AuthenticationPrincipal UserDetails userDetails) {
        List<LoanApplicationResponse> applications = loanApplicationService.getAllLoanApplicationsForUser(userDetails.getUsername());
        return ResponseEntity.ok(applications);
    }

    // Endpoint for customers to get a specific application by ID
    @GetMapping("/{id}")
    public ResponseEntity<LoanApplicationResponse> getLoanApplicationById(@PathVariable Long id,
                                                                          @AuthenticationPrincipal UserDetails userDetails) {
        LoanApplicationResponse application = loanApplicationService.getLoanApplicationById(id, userDetails.getUsername());
        return ResponseEntity.ok(application);
    }

    // Endpoint for admins/officers to get applications by status
    @GetMapping("/status/{status}")
    // @PreAuthorize("hasAnyRole('ADMIN', 'LOAN_OFFICER')")
    public ResponseEntity<List<LoanApplicationResponse>> getLoanApplicationsByStatus(@PathVariable ApplicationStatus status) {
        List<LoanApplicationResponse> applications = loanApplicationService.getAllLoanApplicationsByStatus(status);
        return ResponseEntity.ok(applications);
    }

    // Endpoint for admins/officers to update an application's status
    @PutMapping("/{id}/status")
    // @PreAuthorize("hasAnyRole('ADMIN', 'LOAN_OFFICER')")
    public ResponseEntity<LoanApplicationResponse> updateApplicationStatus(@PathVariable Long id, @RequestParam ApplicationStatus status, @AuthenticationPrincipal UserDetails userDetails) {
        LoanApplicationResponse updatedApplication = loanApplicationService.updateApplicationStatus(id, status, userDetails.getUsername());
        return ResponseEntity.ok(updatedApplication);
    }
}