package com.dailanalytics.dailanalytics.controller;

import com.dailanalytics.dailanalytics.models.Docket;
import com.dailanalytics.dailanalytics.models.Case;
import com.dailanalytics.dailanalytics.service.DocketService;
import com.dailanalytics.dailanalytics.service.CaseService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/dockets")
public class DocketController {

    private final DocketService docketService;
    private final CaseService caseService;

    public DocketController(DocketService docketService, CaseService caseService) {
        this.docketService = docketService;
        this.caseService = caseService;
    }

    // Get all dockets
    @GetMapping
    public List<Docket> getAllDockets() {
        return docketService.getAllDockets();
    }

    @GetMapping("/case/{caseNumber}")
    public ResponseEntity<List<Docket>> getDocketsByCase(
            @PathVariable Integer caseNumber) {

        List<Docket> dockets = docketService.getDocketsByCaseNumber(caseNumber);
        return ResponseEntity.ok(dockets);
    }

    // CREATE docket under caseNumber
    @PostMapping("/case/{caseNumber}")
    public ResponseEntity<Docket> createDocket(
            @PathVariable Integer caseNumber,
            @RequestBody Docket docket) {

        Docket created = docketService.addDocketToCase(caseNumber, docket);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // Update an existing docket
    @PutMapping("/{id}")
    public ResponseEntity<Docket> updateDocket(@PathVariable Long id, @RequestBody Docket updatedDocket) {
        Optional<Docket> existing = Optional.of(docketService.getDocket(id));
        if (existing.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // Ensure case exists
        if (updatedDocket.getCaseEntity() == null || updatedDocket.getCaseEntity().getId() == null) {
            return ResponseEntity.badRequest().build();
        }

        Optional<Case> caseEntity = Optional.of(caseService.getCaseOrThrow(updatedDocket.getCaseEntity().getId()));
        if (caseEntity.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        updatedDocket.setId(id);
        updatedDocket.setCaseEntity(caseEntity.get());
        Docket saved = docketService.saveDocket(updatedDocket);
        return ResponseEntity.ok(saved);
    }

    // Delete a docket
    @DeleteMapping("/{caseNo}")
    public ResponseEntity<Void> deleteDocketById(@PathVariable Integer caseNo) {
        Optional<List<Docket>> existing = Optional.of(docketService.getDocketsByCaseNumber(caseNo));
        if (existing.isPresent()) {
            docketService.deleteDocketsByCaseNumber(caseNo);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    


}