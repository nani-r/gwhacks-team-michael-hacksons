package com.dailanalytics.dailanalytics.service;

import com.dailanalytics.dailanalytics.models.Case;
import com.dailanalytics.dailanalytics.models.Docket;
import com.dailanalytics.dailanalytics.repository.CaseRepo;
import com.dailanalytics.dailanalytics.repository.DocketRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DocketService {

    private final DocketRepo docketRepo;
    private final CaseRepo caseRepo;

    public DocketService(DocketRepo docketRepo, CaseRepo caseRepo) {
        this.docketRepo = docketRepo;
        this.caseRepo = caseRepo;
    }

    private Case getCaseOrThrow(Integer caseNumber) {
        return caseRepo.findByRecordNumber(caseNumber)
                .orElseThrow(() -> new RuntimeException("Case not found: " + caseNumber));
    }

    // CREATE
    public Docket addDocketToCase(Integer caseNumber, Docket docket) {
        Case caseEntity = getCaseOrThrow(caseNumber);
        docket.setId(null);
        docket.setCaseEntity(caseEntity);
        if (caseEntity.getRecordNumber() != null) {
            docket.setCaseNumber(caseEntity.getRecordNumber());
        }
        return docketRepo.save(docket);
    }

    // FIND

    public List<Docket> getDocketsByCaseNumber(Integer caseNumber) {
        return docketRepo.findByCaseNumber(caseNumber);
    }

    public List<Docket> getDocketsByCourt(String court) {
        return docketRepo.findByCourt(court);
    }

    public Docket getDocket(Long docketId) {
        return docketRepo.findById(docketId)
                .orElseThrow(() -> new RuntimeException("Document not found: " + docketId));
    }

    // UPDATE
    public Docket updateDocument(Long docketId, Docket updated) {
        Docket existing = getDocket(docketId);
        existing.setCourt(updated.getCourt());
        existing.setLink(updated.getLink());
        existing.setNumber(updated.getNumber());
        return docketRepo.save(existing);
    }

    // DELETE
    public void deleteDocket(Long docketId) {
        docketRepo.deleteById(docketId);
    }

    public void deleteDocketsByCaseNumber(Integer caseNumber) {
        docketRepo.deleteByCaseNumber(caseNumber);
    }

    public List<Docket> getAllDockets() {
        return docketRepo.findAll();
    }

    public Docket saveDocket(Docket docket) {
        return docketRepo.save(docket);
    }
}

