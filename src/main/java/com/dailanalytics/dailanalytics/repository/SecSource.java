package com.dailanalytics.dailanalytics.repository;

import org.springframework.stereotype.Repository;
import com.dailanalytics.dailanalytics.models.Case;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface SecSource extends JpaRepository<SecSource, Long> {

    List<SecSource> findByCaseEntity(Case caseEntity);
    void deleteByCaseEntityId(Long caseId);
    List<SecSource> findByCaseNumber(Long caseNumber);

}
