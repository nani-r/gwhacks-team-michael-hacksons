package com.dailanalytics.dailanalytics.repository;

import com.dailanalytics.dailanalytics.models.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepo extends JpaRepository<Document, Long> {

    // find all documents for a given Case (by case id)
    List<Document> findByCaseEntity_Id(Long caseId);

    // or by caseNumber column
    List<Document> findByCaseNumber(Integer caseNumber);
}
