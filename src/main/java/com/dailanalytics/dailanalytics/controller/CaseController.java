package com.dailanalytics.dailanalytics.controller;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dailanalytics.dailanalytics.models.Case;
import com.dailanalytics.dailanalytics.service.CaseService;

import jakarta.transaction.Transactional;

@Controller
@RequestMapping("/api/cases")
public class CaseController {

    private final CaseService caseService;

    public CaseController(CaseService caseService) {
        this.caseService = caseService;
    }

    // Get all cases
    @GetMapping
    public ResponseEntity<List<Case>> getAllCases() {
        List<Case> cases = caseService.getAllCases();
        return ResponseEntity.ok(cases);
    }

    // Get Specific Case
    @GetMapping("/{recordNo}")
    public ResponseEntity<Case> getCase(@PathVariable Integer recordNo) {
        Case caseFound = caseService.getCaseByRecordNo(recordNo);
        return ResponseEntity.ok(caseFound);
    }

    // CREATE

    @PostMapping
    public ResponseEntity<Case> createCase(@RequestBody Case newCase) {
        Case created = caseService.addCase(newCase);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // UPDATE
    @Transactional
    @PutMapping("/{recordNo}")
    public ResponseEntity<Case> updateCase(@PathVariable Integer recordNo,
                                           @RequestBody Case updated) {
        Case updatedCase = caseService.updateCase(recordNo, updated);
        return ResponseEntity.ok(updatedCase);
    }

    // DELETE
    @Transactional
    @DeleteMapping("/{recordNo}")
    public ResponseEntity<Void> deleteCase(@PathVariable Integer recordNo) {
        caseService.deleteCase(recordNo);
        return ResponseEntity.noContent().build();
    }


    
}
