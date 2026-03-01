package com.dailanalytics.dailanalytics.controller;

import com.dailanalytics.dailanalytics.models.SecSource;
import com.dailanalytics.dailanalytics.models.Case;
import com.dailanalytics.dailanalytics.service.SecSourceService;
import com.dailanalytics.dailanalytics.service.CaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/sec-sources")
public class SecSourceController {

    private final SecSourceService secSourceService;
    private final CaseService caseService;

    public SecSourceController(SecSourceService secSourceService, CaseService caseService) {
        this.secSourceService = secSourceService;
        this.caseService = caseService;
    }

    // get all sources
    @GetMapping
    public List<SecSource> getAllSecSources() {
        return secSourceService.getAllSecSources();
    }

    // get by id
    @GetMapping("/{caseNo}")
    public ResponseEntity<Optional<List<SecSource>>> getByCaseNo(@PathVariable Integer caseNo) {
        Optional<List<SecSource>> src = Optional.of(secSourceService.getSecSourcesByCaseNumber(caseNo));
        return ResponseEntity.ok(src);
    }

    // create
    @PostMapping
    public ResponseEntity<SecSource> createSecSource(@RequestBody SecSource secSource) {
        if (secSource.getCaseEntity() == null || secSource.getCaseEntity().getId() == null) {
            return ResponseEntity.badRequest().build();
        }
        Optional<Case> caseEntity = Optional.of(caseService.getCaseOrThrow(secSource.getCaseEntity().getId()));
        if (caseEntity.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        secSource.setCaseEntity(caseEntity.get());
        if (caseEntity.get().getRecordNumber() != null) {
            secSource.setCaseNumber(caseEntity.get().getRecordNumber());
        }
        SecSource saved = secSourceService.saveSecSource(secSource);
        return ResponseEntity.ok(saved);
    }

    // update
    @PutMapping("/{id}")
    public ResponseEntity<SecSource> updateSecSource(@PathVariable Long id, @RequestBody SecSource updated) {
        Optional<SecSource> existing = Optional.of(secSourceService.getSecSource(id));
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
        SecSource saved = secSourceService.saveSecSource(updated);
        return ResponseEntity.ok(saved);
    }

    // delete
    @DeleteMapping("/{caseNo}")
    public ResponseEntity<Void> deleteSecSource(@PathVariable Integer caseNo) {
        Optional<List<SecSource>> existing = Optional.of(secSourceService.getSecSourcesByCaseNumber(caseNo));
        if (existing.isPresent()) {
            secSourceService.deleteSecSource(caseNo);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

