package com.onlinebankingloanmanagement.loanmanagement.service;

import com.onlinebankingloanmanagement.loanmanagement.model.Document;
import org.springframework.web.multipart.MultipartFile;

/**
 * Service for handling document uploads and retrieval.
 */
public interface DocumentService {
    void storeDocument(Long loanApplicationId, String documentType, MultipartFile file, String username);

    Document getDocument(Long documentId, String username);
}