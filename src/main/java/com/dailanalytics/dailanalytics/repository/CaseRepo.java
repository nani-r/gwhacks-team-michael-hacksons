package com.dailanalytics.dailanalytics.repository;
import org.springframework.stereotype.Repository;
import com.dailanalytics.dailanalytics.models.Case;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

@Repository
public interface CaseRepo extends JpaRepository<Case, Long> {


    // find methods
    public Optional<Case> findbyRecordNumber(Integer no);
    public Optional<Case> findbyCaseSnug(String snug);

    // delete methods
    public void deleteById(Long id);
    public void deleteByRecordNumber(Integer no);

    
}
