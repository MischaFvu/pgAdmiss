package com.webapp.pgadmiss.entity;

import java.time.LocalDate;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "student")
@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
public class Student extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stu_id")
    private int stuId;

    // Major director_id : User user_id (student) = n : 1
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    // @NotNull(message = "Student name cannot be null")
    // @Size(min = 1, max = 255)
    @Column(name = "stu_name", nullable = false)
    private String stuName;

    @Size(max = 255)
    @Column(name = "given_name")
    private String givenName;

    @Size(max = 255)
    @Column(name = "family_name")
    private String familyName;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @NotNull
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Size(max = 255)
    @Column(name = "nationality")
    private String nationality;

    // @Pattern(regexp = "[A-Za-z0-9]{15,18}|[A-Za-z0-9]{17}[Xx]", message = "Invalid identity number format")
    // @Size(max = 20)
    @Column(name = "id_no")
    private String identityNo;

    @Email(message = "Invalid email format.")
    // @Size(max = 128)
    @Column(name = "email")
    private String email;

    // @Size(max = 11)
    @Column(name = "phone_number")
    private String phoneNumber;

    // @Size(max = 255)
    @Column(name = "address")
    private String address;

    // @Size(max = 255)
    @Column(name = "edu_university")
    private String eduUniversity;

    // @Size(max = 255)
    @Column(name = "edu_address")
    private String eduAddress;

    // @Size(max = 255)
    @Column(name = "edu_major")
    private String eduMajor;

    @Enumerated(EnumType.STRING)
    @Column(name = "edu_qualification")
    private EducationQualification eduQualification;

    @Enumerated(EnumType.STRING)
    @Column(name = "eng_qualification")
    private EnglishQualification engQualification;

    @Column(name = "eng_date")
    private LocalDate engDate;

    // @DecimalMin(value = "0.0", inclusive = true)
    // @DecimalMax(value = "99.9", inclusive = true)
    @Column(name = "eng_score")
    private Double engScore;


    // Enum for education qualifications
    public enum EducationQualification {
        BACHELORS,
        DIPLOMA,
        // Add more qualifications as needed
    }

    // Enum for English qualifications
    public enum EnglishQualification {
        TOEFL,
        IELTS,
        PTE
    }
}
