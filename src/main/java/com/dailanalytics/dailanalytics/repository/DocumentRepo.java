package com.dailanalytics.dailanalytics.repository;

import com.dailanalytics.dailanalytics.models.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DocumentRepo extends JpaRepository<Document, Long> {

    // or by caseNumber column
    List<Document> findByCaseEntity_Id(Long caseId);

    List<Document> findByCaseNumber(Integer caseNumber);

    List<Document> findByDate(LocalDate date);

    List<Document> findByCourt(String court);

    // Delete Methods
    public void deleteByCaseNumber(Long caseNumber);
    public void deleteById(Long id);
    
}
