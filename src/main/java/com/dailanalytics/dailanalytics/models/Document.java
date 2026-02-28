package com.dailanalytics.dailanalytics.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer caseNumber;

    @Column(length = 64000)
    private String court;

    private LocalDateTime date;

    @Column(length = 64000)
    private String link;

    @Column(length = 64000)
    private String citeOrReference;
}