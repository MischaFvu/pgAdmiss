package com.webapp.pgadmiss.entity;

import java.time.LocalDate;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "student_entry")
@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
public class StudentEntry extends BaseEntity {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stu_entry_id")
    private int stuEntryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stu_id", nullable = false)
    private Student student;

    @Enumerated(EnumType.STRING)
    @Column(name = "entry_type", nullable = false)
    private EntryType entryType;

    // Work-related fields
    @Column(name = "work_employer")
    private String workEmployer;

    @Enumerated(EnumType.STRING)
    @Column(name = "work_type")
    private WorkType workType;

    @Column(name = "work_job")
    private String workJob;

    @Column(name = "work_start_date")
    private LocalDate workStartDate;

    @Column(name = "work_end_date")
    private LocalDate workEndDate;

    @Column(name = "work_city")
    private String workCity;

    // Referee-related fields
    @Column(name = "referee_name")
    private String refereeName;

    @Column(name = "referee_job")
    private String refereeJob;

    @Column(name = "referee_org")
    private String refereeOrg;

    @Email
    @Column(name = "referee_email")
    private String refereeEmail;

    @Column(name = "referee_phone")
    private String refereePhone;

    @Column(name = "referee_city")
    private String refereeCity;

    public enum EntryType {
        REFEREE,
        WORK
    }
    
    public enum WorkType {
        EMPLOYMENT,
        INTERNSHIP,
        PART_TIME
    }
}
