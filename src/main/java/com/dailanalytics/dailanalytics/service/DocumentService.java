package com.dailanalytics.dailanalytics.service;

import com.dailanalytics.dailanalytics.models.Case;
import com.dailanalytics.dailanalytics.models.Document;
import com.dailanalytics.dailanalytics.repository.CaseRepo;
import com.dailanalytics.dailanalytics.repository.DocumentRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DocumentService {

    private final DocumentRepo documentRepo;
    private final CaseRepo caseRepo;

    public DocumentService(DocumentRepo documentRepo, CaseRepo caseRepo) {
        this.documentRepo = documentRepo;
        this.caseRepo = caseRepo;
    }

    private Case getCaseOrThrow(Long caseId) {
        return caseRepo.findById(caseId)
                .orElseThrow(() -> new RuntimeException("Case not found: " + caseId));
    }

    // CREATE
    public Document addDocumentToCase(Long caseId, Document document) {
        Case caseEntity = getCaseOrThrow(caseId);
        document.setCaseEntity(caseEntity);
        if (caseEntity.getRecordNumber() != null) {
            document.setCaseNumber(caseEntity.getRecordNumber());
        }
        return documentRepo.save(document);
    }

    // FIND
    public List<Document> getDocumentsByCaseNumber(Integer caseNumber) {
        return documentRepo.findByCaseNumber(caseNumber);
    }

    public List<Document> getDocumentsByDate(LocalDate date) {
        return documentRepo.findByDate(date);
    }

    public List<Document> getDocumentsByCourt(String court) {
        return documentRepo.findByCourt(court);
    }

    public Document getDocument(Long documentId) {
        return documentRepo.findById(documentId)
                .orElseThrow(() -> new RuntimeException("Document not found: " + documentId));
    }

    // UPDATE
    public Document updateDocument(Long documentId, Document updated) {
        Document existing = getDocument(documentId);
        existing.setCourt(updated.getCourt());
        existing.setDate(updated.getDate());
        existing.setLink(updated.getLink());
        existing.setCiteOrReference(updated.getCiteOrReference());
        return documentRepo.save(existing);
    }

    // DELETE
    public void deleteDocument(Long documentId) {
        documentRepo.deleteById(documentId);
    }

    public void deleteDocumentsByCaseNumber(Integer caseNumber) {
        documentRepo.deleteByCaseNumber(Long.valueOf(caseNumber));
    }
}