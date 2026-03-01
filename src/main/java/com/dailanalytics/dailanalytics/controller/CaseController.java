package com.dailanalytics.dailanalytics.controller;

import java.util.List;

import org.apache.catalina.connector.Response;
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
import com.dailanalytics.dailanalytics.models.Docket;
import com.dailanalytics.dailanalytics.repository.CaseRepo;
import com.dailanalytics.dailanalytics.service.CaseService;

@Controller
@RequestMapping("/api/case")
public class CaseController {

    private final CaseService caseService;
    private final CaseRepo caseRepo;

    public CaseController(CaseService caseService, CaseRepo caseRepo) {
        this.caseService = caseService;
        this.caseRepo = caseRepo;
    }

    // Get all cases
    @GetMapping
    public List<Case> getAllCases() {
        return caseService.getAllCases();
    }

    // Get Specific Case
    @GetMapping("/{recordNo}")
    public Case getCase(@PathVariable Integer recordNo) {
        return caseService.getCaseByRecordNo(recordNo);
    }

    // CREATE

    @PostMapping
    public Case createCase(@RequestBody Case newCase) {
        return caseService.addCase(newCase);
    }

    // UPDATE

    @PutMapping("/{recordNo}")
    public Case updateCase(@PathVariable Integer recordNo, @RequestBody Case updated) {
        return caseService.updateCase(recordNo, updated);
    }

    // DELETE 

    @DeleteMapping("/{recordNo}")
    public void deleteCase(@PathVariable Integer recordNo) {
        caseService.deleteCase(recordNo);
    }
    



    


    
}
