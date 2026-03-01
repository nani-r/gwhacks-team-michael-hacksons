package com.dailanalytics.dailanalytics.service;

import com.dailanalytics.dailanalytics.models.Case;
import com.dailanalytics.dailanalytics.models.SecSource;
import com.dailanalytics.dailanalytics.repository.CaseRepo;
import com.dailanalytics.dailanalytics.repository.SecSourceRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SecSourceService {

    private final SecSourceRepo secSourceRepo;
    private final CaseRepo caseRepo;

    public SecSourceService(SecSourceRepo secSourceRepo, CaseRepo caseRepo) {
        this.secSourceRepo = secSourceRepo;
        this.caseRepo = caseRepo;
    }

    private Case getCaseByCaseNumberOrThrow(Integer caseNumber) {
        return caseRepo.findByRecordNumber(caseNumber)
                .orElseThrow(() -> new RuntimeException("Case not found for caseNumber: " + caseNumber));
    }

    // ADD
    public SecSource addSecSource(Integer caseNumber, SecSource secSource) {
        Case caseEntity = getCaseByCaseNumberOrThrow(caseNumber);
        secSource.setCaseNumber(caseNumber);
        secSource.setCaseEntity(caseEntity);
        return secSourceRepo.save(secSource);
    }

    // FIND
    public List<SecSource> getSecSourcesByCaseNumber(Integer caseNumber) {
        return secSourceRepo.findByCaseNumber(caseNumber);
    }

    public SecSource getSecSource(Long id) {
        return secSourceRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("SecSource not found: " + id));
    }

    // UPDATE
    public SecSource updateSecSource(Long id, SecSource updated) {
        SecSource existing = getSecSource(id);
        existing.setSecondarySourceLink(updated.getSecondarySourceLink());
        existing.setSecondarySourceTitle(updated.getSecondarySourceTitle());

        return secSourceRepo.save(existing);
    }

    // DELETE (by id)
    public void deleteSecSource(Long id) {
        secSourceRepo.deleteById(id);
    }

    public void deleteByCaseNumber(Integer caseNumber) {
        secSourceRepo.deleteByCaseNumber(caseNumber);
    }

    // helpers for controllers
    public List<SecSource> getAllSecSources() {
        return secSourceRepo.findAll();
    }

    public SecSource saveSecSource(SecSource secSource) {
        return secSourceRepo.save(secSource);
    }
}
