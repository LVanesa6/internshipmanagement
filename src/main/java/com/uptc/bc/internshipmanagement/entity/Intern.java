package com.uptc.bc.internshipmanagement.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "intern")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Intern {

    public enum PracticeStatus {
        ACTIVE, FINISHED, PENDING
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(name = "academic_program", nullable = false)
    private String academicProgram;

    @Column(name = "entry_date", nullable = false)
    // LocalDate no necesita @Temporal
    private LocalDate entryDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "practice_status", nullable = false)
    private PracticeStatus practiceStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supervisor_id")
    private User supervisor;

    @OneToMany(mappedBy = "intern", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Progress> progressList;
}
