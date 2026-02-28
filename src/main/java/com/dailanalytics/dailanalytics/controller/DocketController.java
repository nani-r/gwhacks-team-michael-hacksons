package com.dailanalytics.dailanalytics.controller;

import com.dailanalytics.dailanalytics.models.Docket;
import com.dailanalytics.dailanalytics.models.Case;
import com.dailanalytics.dailanalytics.service.DocketService;
import com.dailanalytics.dailanalytics.service.CaseService;
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

    // Get a docket by ID
    @GetMapping("/{id}")
    public ResponseEntity<Docket> getDocketById(@PathVariable Long id) {
        Optional<Docket> docket = Optional.of(docketService.getDocket(id));
        return docket.map(ResponseEntity::ok)
                     .orElse(ResponseEntity.notFound().build());
    }

    // Get all dockets for a specific case
    @GetMapping("/case/{caseId}")
    public ResponseEntity<List<Docket>> getDocketsByCase(@PathVariable Long caseId) {
        Optional<Case> caseEntity = Optional.of(caseService.getCaseOrThrow(caseId));
        if (caseEntity.isPresent()) {
            Integer caseIdAsInteger = Math.toIntExact(caseId);
            List<Docket> dockets = docketService.getDocketsByCaseNumber(caseIdAsInteger);
            return ResponseEntity.ok(dockets);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Create a new docket
    @PostMapping
    public ResponseEntity<Docket> createDocket(@RequestBody Docket docket) {
        // Make sure the case exists
        if (docket.getCaseEntity() == null || docket.getCaseEntity().getId() == null) {
            return ResponseEntity.badRequest().build();
        }

        Optional<Case> caseEntity = Optional.of(caseService.getCaseOrThrow(docket.getCaseEntity().getId()));
        if (caseEntity.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        docket.setCaseEntity(caseEntity.get()); // ensure managed entity
        Docket savedDocket = docketService.saveDocket(docket);
        return ResponseEntity.ok(savedDocket);
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
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDocket(@PathVariable Long id) {
        Optional<Docket> existing = Optional.of(docketService.getDocket(id));
        if (existing.isPresent()) {
            docketService.deleteDocket(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}