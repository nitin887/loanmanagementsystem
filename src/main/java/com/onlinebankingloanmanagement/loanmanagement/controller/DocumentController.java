package com.onlinebankingloanmanagement.loanmanagement.controller;

import com.onlinebankingloanmanagement.loanmanagement.model.Document;
import com.onlinebankingloanmanagement.loanmanagement.service.DocumentService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class DocumentController {

    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @PostMapping("/applications/{appId}/documents")
    public ResponseEntity<String> uploadDocument(@PathVariable Long appId,
                                                 @RequestParam("file") MultipartFile file,
                                                 @RequestParam("type") String documentType,
                                                 @AuthenticationPrincipal UserDetails userDetails) {
        documentService.storeDocument(appId, documentType, file, userDetails.getUsername());
        return ResponseEntity.ok("Document uploaded successfully for application: " + appId);
    }

    @GetMapping("/documents/{docId}")
    public ResponseEntity<Resource> downloadDocument(@PathVariable Long docId, @AuthenticationPrincipal UserDetails userDetails) {
        Document document = documentService.getDocument(docId, userDetails.getUsername());
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + document.getFileName() + "\"")
                .body(new ByteArrayResource(document.getFileContent()));
    }
}