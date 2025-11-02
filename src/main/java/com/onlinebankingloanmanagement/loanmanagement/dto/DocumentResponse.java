package com.onlinebankingloanmanagement.loanmanagement.dto;

import java.time.LocalDateTime;

/**
 * DTO for displaying document metadata.
 */
public class DocumentResponse {
    private Long id;
    private String documentType;
    private String fileName;
    private LocalDateTime uploadDate;
    private String downloadUrl; // A generated URL to download the file content

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public LocalDateTime getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(LocalDateTime uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }
}