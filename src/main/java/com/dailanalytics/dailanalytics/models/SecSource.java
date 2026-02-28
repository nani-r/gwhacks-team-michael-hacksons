package com.dailanalytics.dailanalytics.models;
import lombok.*;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "secondary_source")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SecSource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "case_number", nullable = false)
    private Integer caseNumber;
    
    @Column(name = "secondary_source_link", length = 1000, nullable = false)
    private String secondarySourceLink;

    @Column(name = "secondary_source_title", length = 1000)
    private String secondarySourceTitle;

    @ManyToOne(optional = false)
    @JoinColumn(name = "case_id", nullable = false)
    @JsonBackReference
    private Case caseEntity;

    public SecSource(String link, String title, Case caseEntity) {
        this.secondarySourceLink = link;
        this.secondarySourceTitle = title;
        this.caseEntity = caseEntity;
    }

    // Getters & Setters
    public Long getId() { return id; }

    public String getSecondarySourceLink() { return secondarySourceLink; }
    public void setSecondarySourceLink(String secondarySourceLink) {
        this.secondarySourceLink = secondarySourceLink;
    }

    public String getSecondarySourceTitle() { return secondarySourceTitle; }
    public void setSecondarySourceTitle(String secondarySourceTitle) {
        this.secondarySourceTitle = secondarySourceTitle;
    }

    public Case getCaseEntity() { return caseEntity; }
    public void setCaseEntity(Case caseEntity) {
        this.caseEntity = caseEntity;
    }
}

