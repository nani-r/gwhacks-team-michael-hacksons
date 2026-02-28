package com.dailanalytics.dailanalytics.repository;

import com.dailanalytics.dailanalytics.models.SecSource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SecSourceRepo extends JpaRepository<SecSource, Long> {

    List<SecSource> findByCaseNumber(Integer caseNumber);

    void deleteByCaseNumber(Integer caseNumber);
}
