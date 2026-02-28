package com.dailanalytics.dailanalytics.repository;

import com.dailanalytics.dailanalytics.models.Docket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocketRepo extends JpaRepository<Docket, Long> {

    List<Docket> findByCaseNumber(Long caseNumber);
    public void deleteByCaseNumber(Long caseNumber);
}

