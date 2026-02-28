package com.dailanalytics.dailanalytics.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dailanalytics.dailanalytics.models.Case;
import com.dailanalytics.dailanalytics.repository.CaseRepo;

@Service
public class CaseService {

    private final CaseRepo caseRepo;

    public CaseService(CaseRepo caseRepo) {
        this.caseRepo = caseRepo;
    }


    // CREATE 
    public Case addCase(Case newCase) {
        return caseRepo.save(newCase);
    }

    // FIND
    
    public Case getCaseOrThrow(Long caseId) {
        return caseRepo.findById(caseId)
                .orElseThrow(() -> new RuntimeException("Case not found: " + caseId));
    }

    public Case getCaseByRecordNo(Integer recordNo) {
        return caseRepo.findbyRecordNumber(recordNo) 
            .orElseThrow(() ->   new RuntimeException("Case not found with record number: " + recordNo));
    }

    public Case getCaseBySnug(String snug) {
        return caseRepo.findbyCaseSnug(snug)
            .orElseThrow(() -> new RuntimeException("Case not found with snug: " + snug));
    }

    


    // UPDATE
    public Case updateCase(Long caseId, Case updatedCase) {

        Case existing = caseRepo.findById(caseId)
                .orElseThrow(() -> new RuntimeException("Case not found"));

        existing.setCaseSnug(updatedCase.getCaseSnug());
        existing.setBriefDescription(updatedCase.getBriefDescription());

        existing.setAreaOfApplicationList(updatedCase.getAreaOfApplicationList());
        existing.setAreaOfApplicationText(updatedCase.getAreaOfApplicationText());

        existing.setIssueText(updatedCase.getIssueText());
        existing.setIssueList(updatedCase.getIssueList());

        existing.setCauseOfActionList(updatedCase.getCauseOfActionList());
        existing.setCauseOfActionText(updatedCase.getCauseOfActionText());

        existing.setIssueListOld(updatedCase.getIssueListOld());
        existing.setIssueTextOld(updatedCase.getIssueTextOld());

        existing.setAlgorithmList(updatedCase.getAlgorithmList());
        existing.setAlgorithmText(updatedCase.getAlgorithmText());

        existing.setClassActionList(updatedCase.getClassActionList());
        existing.setClassAction(updatedCase.getClassAction());

        existing.setOrganizationsInvolved(updatedCase.getOrganizationsInvolved());
        existing.setJurisdictionFiled(updatedCase.getJurisdictionFiled());
        existing.setDateActionFiled(updatedCase.getDateActionFiled());

        existing.setCurrentJurisdiction(updatedCase.getCurrentJurisdiction());
        existing.setJurisdictionType(updatedCase.getJurisdictionType());
        existing.setJurisdictionName(updatedCase.getJurisdictionName());

        existing.setPublishedOpinions(updatedCase.getPublishedOpinions());
        existing.setPublishedOpinionsBinary(updatedCase.getPublishedOpinionsBinary());

        existing.setStatusDisposition(updatedCase.getStatusDisposition());
        existing.setDateAdded(updatedCase.getDateAdded());
        existing.setLastUpdate(LocalDateTime.now());

        existing.setProgressNotes(updatedCase.getProgressNotes());
        existing.setResearcher(updatedCase.getResearcher());

        existing.setSummarySignificance(updatedCase.getSummarySignificance());
        existing.setSummaryFactsActivity(updatedCase.getSummaryFactsActivity());

        existing.setMostRecentActivity(updatedCase.getMostRecentActivity());
        existing.setMostRecentActivityDate(updatedCase.getMostRecentActivityDate());

        existing.setKeyword(updatedCase.getKeyword());
        existing.setJurisdictionTypeText(updatedCase.getJurisdictionTypeText());

        return caseRepo.save(existing);
    }

    // DELETE
    public void deleteCase(Long caseId) {
        caseRepo.deleteById(caseId);
    }

    public void deleteCaseByRecordNo(Integer caseNumber) {
        caseRepo.deleteByRecordNumber(caseNumber);
    }


    

}