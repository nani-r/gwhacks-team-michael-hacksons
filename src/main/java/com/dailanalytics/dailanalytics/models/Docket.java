package com.dailanalytics.dailanalytics.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;


@Entity
@Table(name="docket")
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

    public Docket() {}

    public Docket(Integer caseNumber, String court, String number, String link) {
        this.caseNumber = caseNumber;
        this.court = court;
        this.number = number;
        this.link = link;
    }



    // Getters and Setters
    public Long getId() {
        return id;
    }

    public Integer getCaseNumber() {
        return caseNumber;
    }

    public void setCaseNumber(Integer caseNumber) {
        this.caseNumber = caseNumber;
    }

    public String getCourt() {
        return court;
    }

    public void setCourt(String court) {
        this.court = court;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }





}
