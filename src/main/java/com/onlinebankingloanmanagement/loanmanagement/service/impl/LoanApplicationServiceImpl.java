package com.onlinebankingloanmanagement.loanmanagement.service.impl;

import com.onlinebankingloanmanagement.loanmanagement.dto.LoanApplicationRequest;
import com.onlinebankingloanmanagement.loanmanagement.dto.LoanApplicationResponse;
import com.onlinebankingloanmanagement.loanmanagement.exception.ResourceNotFoundException;
import com.onlinebankingloanmanagement.loanmanagement.mapper.LoanApplicationMapper;
import com.onlinebankingloanmanagement.loanmanagement.model.ApplicationStatus;
import com.onlinebankingloanmanagement.loanmanagement.model.LoanApplication;
import com.onlinebankingloanmanagement.loanmanagement.model.Role;
import com.onlinebankingloanmanagement.loanmanagement.model.User;
import com.onlinebankingloanmanagement.loanmanagement.repository.LoanApplicationRepository;
import com.onlinebankingloanmanagement.loanmanagement.repository.UserRepository;
import com.onlinebankingloanmanagement.loanmanagement.service.LoanApplicationService;
import com.onlinebankingloanmanagement.loanmanagement.service.LoanService;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoanApplicationServiceImpl implements LoanApplicationService {

    private final LoanApplicationRepository loanApplicationRepository;
    private final UserRepository userRepository;
    private final LoanApplicationMapper loanApplicationMapper;
    private final LoanService loanService;

    public LoanApplicationServiceImpl(LoanApplicationRepository loanApplicationRepository, UserRepository userRepository, LoanApplicationMapper loanApplicationMapper, LoanService loanService) {
        this.loanApplicationRepository = loanApplicationRepository;
        this.userRepository = userRepository;
        this.loanApplicationMapper = loanApplicationMapper;
        this.loanService = loanService;
    }

    @Override
    @Transactional
    public LoanApplicationResponse createLoanApplication(LoanApplicationRequest request, String username) {
        User user = getUserByUsername(username);

        LoanApplication loanApplication = loanApplicationMapper.toEntity(request);
        loanApplication.setUser(user);
        loanApplication.setStatus(ApplicationStatus.PENDING);
        loanApplication.setApplicationDate(LocalDateTime.now());

        LoanApplication savedApplication = loanApplicationRepository.save(loanApplication);
        return toDtoWithDownloadUrls(savedApplication);
    }

    @Override
    public LoanApplicationResponse getLoanApplicationById(Long id, String username) {
        User requestingUser = getUserByUsername(username);
        LoanApplication loanApplication = findApplicationById(id);

        // Security check: user must be the owner or an admin/loan officer
        boolean isOwner = loanApplication.getUser().getUsername().equals(username);
        boolean isAdminOrOfficer = requestingUser.getRole() == Role.ADMIN || requestingUser.getRole() == Role.LOAN_OFFICER;

        if (!isOwner && !isAdminOrOfficer) {
            throw new AccessDeniedException("You do not have permission to view this application.");
        }
        return toDtoWithDownloadUrls(loanApplication);
    }

    @Override
    public List<LoanApplicationResponse> getAllLoanApplicationsForUser(String username) {
        User user = getUserByUsername(username);
        return loanApplicationRepository.findByUserId(user.getId())
                .stream()
                .map(this::toDtoWithDownloadUrls)
                .collect(Collectors.toList());
    }

    @Override
    public List<LoanApplicationResponse> getAllLoanApplicationsByStatus(ApplicationStatus status) {
        // This is for admin/officer use. Security should be enforced at the controller level with @PreAuthorize.
        return loanApplicationRepository.findByStatus(status)
                .stream()
                .map(this::toDtoWithDownloadUrls)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public LoanApplicationResponse updateApplicationStatus(Long id, ApplicationStatus status, String adminUsername) {
        User admin = getUserByUsername(adminUsername);

        // Security check: ensure the user updating the status is an admin or loan officer
        if (admin.getRole() != Role.ADMIN && admin.getRole() != Role.LOAN_OFFICER) {
            throw new AccessDeniedException("You do not have permission to update application status.");
        }

        LoanApplication loanApplication = findApplicationById(id);

        // Business rule: Prevent changing status from a final state (APPROVED/REJECTED) to something else.
        ApplicationStatus currentStatus = loanApplication.getStatus();
        if ((currentStatus == ApplicationStatus.APPROVED || currentStatus == ApplicationStatus.REJECTED) && currentStatus != status) {
            throw new IllegalStateException("Cannot change status from a final state: " + currentStatus);
        }

        // Idempotency: If status is not changing, just return the current state.
        if (currentStatus == status) {
            return toDtoWithDownloadUrls(loanApplication);
        }

        loanApplication.setStatus(status);
        loanApplication.setDecisionDate(LocalDateTime.now());

        // Only create a loan if the status is changing to APPROVED
        if (status == ApplicationStatus.APPROVED) {
            loanService.createLoanFromApplication(loanApplication);
        }

        LoanApplication updatedApplication = loanApplicationRepository.save(loanApplication);
        return toDtoWithDownloadUrls(updatedApplication);
    }

    /**
     * Helper method to convert entity to DTO and set document download URLs.
     */
    private LoanApplicationResponse toDtoWithDownloadUrls(LoanApplication loanApplication) {
        LoanApplicationResponse response = loanApplicationMapper.toDto(loanApplication);
        if (response.getDocuments() != null) {
            response.getDocuments().forEach(doc ->
                    doc.setDownloadUrl("/api/documents/" + doc.getId())
            );
        }
        return response;
    }

    // --- Helper Methods ---

    private User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));
    }

    private LoanApplication findApplicationById(Long id) {
        return loanApplicationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("LoanApplication", "id", id));
    }
}