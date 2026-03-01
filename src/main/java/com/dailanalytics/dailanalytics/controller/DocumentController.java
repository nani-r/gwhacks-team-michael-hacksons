package com.dailanalytics.dailanalytics.controller;

import com.dailanalytics.dailanalytics.models.Document;
import com.dailanalytics.dailanalytics.models.Case;
import com.dailanalytics.dailanalytics.service.DocumentService;
import com.dailanalytics.dailanalytics.service.CaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/documents")
public class DocumentController {

    private final DocumentService documentService;
    private final CaseService caseService;

    public DocumentController(DocumentService documentService, CaseService caseService) {
        this.documentService = documentService;
        this.caseService = caseService;
    }

    // get all documents
    @GetMapping
    public List<Document> getAllDocuments() {
        return documentService.getAllDocuments();
    }

    // get by caseNo
    @GetMapping("/{caseNo}")
    public ResponseEntity<Optional<List<Document>>> getDocumentsByCaseNo(@PathVariable Integer caseNo) {
        Optional<List<Document>> doc = Optional.of(documentService.getDocumentsByCaseNumber(caseNo));
        return ResponseEntity.ok(doc);
    }

    // additional filters (date and court)
    @GetMapping("/date/{date}")
    public List<Document> getByDate(@PathVariable String date) {
        // expecting ISO date yyyy-MM-dd
        return documentService.getDocumentsByDate(java.time.LocalDate.parse(date));
    }

    @GetMapping("/court/{court}")
    public List<Document> getByCourt(@PathVariable String court) {
        return documentService.getDocumentsByCourt(court);
    }

    // create
    @PostMapping
    public ResponseEntity<Document> createDocument(@RequestBody Document document) {
        if (document.getCaseEntity() == null || document.getCaseEntity().getId() == null) {
            return ResponseEntity.badRequest().build();
        }
        Optional<Case> caseEntity = Optional.of(caseService.getCaseOrThrow(document.getCaseEntity().getId()));
        if (caseEntity.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        document.setCaseEntity(caseEntity.get());
        if (caseEntity.get().getRecordNumber() != null) {
            document.setCaseNumber(caseEntity.get().getRecordNumber());
        }
        Document saved = documentService.saveDocument(document);
        return ResponseEntity.ok(saved);
    }

    // update
    @PutMapping("/{id}")
    public ResponseEntity<Document> updateDocument(@PathVariable Long id, @RequestBody Document updated) {
        Optional<Document> existing = Optional.of(documentService.getDocument(id));
        if (existing.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        if (updated.getCaseEntity() == null || updated.getCaseEntity().getId() == null) {
            return ResponseEntity.badRequest().build();
        }
        Optional<Case> caseEntity = Optional.of(caseService.getCaseOrThrow(updated.getCaseEntity().getId()));
        if (caseEntity.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        updated.setId(id);
        updated.setCaseEntity(caseEntity.get());
        if (caseEntity.get().getRecordNumber() != null) {
            updated.setCaseNumber(caseEntity.get().getRecordNumber());
        }
        Document saved = documentService.saveDocument(updated);
        return ResponseEntity.ok(saved);
    }

    // delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDocument(@PathVariable Integer caseNo) {
        Optional<List<Document>> existing = Optional.of(documentService.getDocumentsByCaseNumber(caseNo));
        if (existing.isPresent()) {
            documentService.deleteDocumentsByCaseNumber(caseNo);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

