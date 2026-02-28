package com.dailanalytics.dailanalytics.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

import lombok.*;


@Entity
@Table(name="docket")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Docket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "case_number", nullable = false)
    private Integer caseNumber;

    @Column(name = "court", nullable = false)
    private String court;

    @Column(name = "number", nullable = false)
    private String number;

    @Column(name = "link", length = 1000)
    private String link;

    @ManyToOne(optional = false)
    @JoinColumn(name = "case_id", nullable = false)
    @JsonBackReference
    private Case caseEntity;

    public Docket(Integer caseNumber, String court, String number, String link) {
        this.caseNumber = caseNumber;
        this.court = court;
        this.number = number;
        this.link = link;
    }





}
