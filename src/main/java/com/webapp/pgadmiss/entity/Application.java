package com.webapp.pgadmiss.entity;



import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "application")
@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
public class Application extends BaseEntity{

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "app_id")
    private int appId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stu_id", nullable = false)
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "major_id", nullable = false)
    private Major major;

    @Column(name = "app_period", nullable = false)
    private int appPeriod;

    @Lob
    @Column(name = "doc_degree_cert")
    private byte[] docDegreeCert;

    @Lob
    @Column(name = "doc_transcript")
    private byte[] docTranscript;

    @Lob
    @Column(name = "doc_RL1")
    private byte[] docRL1;

    @Lob
    @Column(name = "doc_RL2")
    private byte[] docRL2;

    @Lob
    @Column(name = "doc_id_card")
    private byte[] docIdCard;

    @Lob
    @Column(name = "doc_PS")
    private byte[] docPS;

    @Lob
    @Column(name = "doc_CV")
    private byte[] docCV;

    @Lob
    @Column(name = "doc_language")
    private byte[] docLanguage;

    @Lob
    @Column(name = "doc_other")
    private byte[] docOther;


    @Enumerated(EnumType.STRING)
    @Column(name = "app_status", nullable = false)
    private AppStatus appStatus;

    public enum AppStatus {
        SAVE,
        SUBMITTED,
        AUDITING,
        APPROVED
    }

}
