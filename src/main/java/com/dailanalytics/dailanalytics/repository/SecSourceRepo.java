package com.dailanalytics.dailanalytics.repository;

import org.springframework.stereotype.Repository;
import com.dailanalytics.dailanalytics.models.Case;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface SecSourceRepo extends JpaRepository<SecSourceRepo, Long> {

    List<SecSourceRepo> findByCaseNumber(Long caseNumber);
    public void deleteByCaseNumber(Long caseNumber);

}
