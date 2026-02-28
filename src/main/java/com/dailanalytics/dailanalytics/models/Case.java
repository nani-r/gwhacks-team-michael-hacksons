package com.dailanalytics.dailanalytics.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Case {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String caseSnug;

    private Integer recordNumber;

    private String caption;

    @Column(length = 64000)
    private String briefDescription;

    // List<String> fields
    @ElementCollection
    private List<String> areaOfApplicationList;

    private String areaOfApplicationText;

    @Column(length = 64000)
    private String issueText;

    @ElementCollection
    private List<String> issueList;

    @ElementCollection
    private List<String> causeOfActionList;

    private String causeOfActionText;

    @ElementCollection
    private List<String> issueListOld;

    private String issueTextOld;

    @ElementCollection
    private List<String> algorithmList;

    private String algorithmText;

    @ElementCollection
    private List<String> classActionList;

    private String classAction;

    private String organizationsInvolved;

    private String jurisdictionFiled;

    private LocalDateTime dateActionFiled;

    private String currentJurisdiction;

    @ElementCollection
    private List<String> jurisdictionType;

    private String jurisdictionName;

    private String publishedOpinions;

    private Boolean publishedOpinionsBinary;

    private String statusDisposition;

    private LocalDateTime dateAdded;

    private LocalDateTime lastUpdate;

    private String progressNotes;

    private String researcher;

    @Column(length = 64000)
    private String summarySignificance;

    @Column(length = 64000)
    private String summaryFactsActivity;

    private String mostRecentActivity;

    private LocalDateTime mostRecentActivityDate;

    private String keyword;

    private String jurisdictionTypeText;

    @OneToMany(mappedBy = "caseEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Docket> dockets;

    @OneToMany(mappedBy = "caseEntity", cascade = CascadeType.ALL, orphanRemoval = true) 
    @JsonManagedReference
    private List<Document> documents;

    @OneToMany(mappedBy = "caseEntity", cascade = CascadeType.ALL, orphanRemoval = true) 
    @JsonManagedReference
    private List<SecSource> secSources;
    
}
