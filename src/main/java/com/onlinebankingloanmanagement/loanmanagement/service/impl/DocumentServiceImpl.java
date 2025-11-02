package com.onlinebankingloanmanagement.loanmanagement.service.impl;

import com.onlinebankingloanmanagement.loanmanagement.exception.ResourceNotFoundException;
import com.onlinebankingloanmanagement.loanmanagement.model.Document;
import com.onlinebankingloanmanagement.loanmanagement.model.LoanApplication;
import com.onlinebankingloanmanagement.loanmanagement.repository.DocumentRepository;
import com.onlinebankingloanmanagement.loanmanagement.repository.LoanApplicationRepository;
import com.onlinebankingloanmanagement.loanmanagement.service.DocumentService;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;
    private final LoanApplicationRepository loanApplicationRepository;

    public DocumentServiceImpl(DocumentRepository documentRepository, LoanApplicationRepository loanApplicationRepository) {
        this.documentRepository = documentRepository;
        this.loanApplicationRepository = loanApplicationRepository;
    }

    @Override
    public void storeDocument(Long loanApplicationId, String documentType, MultipartFile file, String username) {
        LoanApplication loanApplication = loanApplicationRepository.findById(loanApplicationId)
                .orElseThrow(() -> new ResourceNotFoundException("LoanApplication", "id", loanApplicationId));

        if (!loanApplication.getUser().getUsername().equals(username)) {
            throw new AccessDeniedException("You do not have permission to upload documents to this application.");
        }

        try {
            Document document = new Document();
            document.setLoanApplication(loanApplication);
            document.setDocumentType(documentType);
            document.setFileName(StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename())));
            document.setFileContent(file.getBytes());
            document.setUploadDate(LocalDateTime.now());
            documentRepository.save(document);
        } catch (IOException ex) {
            throw new RuntimeException("Could not store file. Please try again!", ex);
        }
    }

    @Override
    public Document getDocument(Long documentId, String username) {
        Document document = documentRepository.findById(documentId)
                .orElseThrow(() -> new ResourceNotFoundException("Document", "id", documentId));

        if (!document.getLoanApplication().getUser().getUsername().equals(username)) {
            throw new AccessDeniedException("You do not have permission to access this document.");
        }
        return document;
    }
}