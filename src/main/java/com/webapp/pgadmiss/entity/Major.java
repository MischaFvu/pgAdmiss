package com.webapp.pgadmiss.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "major")
@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
public class Major extends BaseEntity {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "major_id")
    private int majorId;

    @Column(name = "major_no", unique = true, nullable = false)
    private int majorNo;

    // @NotBlank(message = "Major name cannot be null.")
    @Column(name = "major_name", nullable = false)
    private String majorName;

    @Enumerated(EnumType.STRING)
    @Column(name = "major_status", nullable = false)
    private MajorStatus majorStatus;

    // @NotNull(message = "Fee cannot be null.")
    // @DecimalMin(value = "0.0", inclusive = false, message = "Fee cannot be zero or negative.")
    // @DecimalMax(value = "999999.99", inclusive = true, message = "Fee cannot be greater than 999999.99.")
    @Column(name = "fee", nullable = false)
    private BigDecimal fee;

    // @NotNull(message = "Start date cannot be null.")
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    // @NotNull(message = "End date cannot be null.")
    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;
    
    // @NotNull(message = "Period cannot be null.")
    // @Min(value = 2024, message = "Period cannot be less than 2024.")
    // @Max(value = 2030, message = "Period cannot be greater than 2030.")
    @Column(name = "period", nullable = false)
    private int period;

    // Duration - months
    // @NotNull(message = "Duration cannot be null.")
    // @Min(value = 12, message = "Duration cannot be less than 12.")
    // @Max(value = 36, message = "Duration cannot be greater than 36.")
    @Column(name = "duration", nullable = false)
    private int duration;

    @Email(message = "Invalid email format.")
    @Column(name = "contact_email", nullable = false)
    private String contactEmail;

    // @NotBlank(message = "Contact phone cannot be null.")
    // @Size(min = 11, max = 11, message = "Contact phone must be 11 digits.")
    @Column(name = "contact_phone", nullable = false)
    private String contactPhone;

    // @Size(max = Integer.MAX_VALUE, message = "Description cannot exceed 65535 characters.")
    @Column(name = "description")
    private String description;

    // @Size(max = Integer.MAX_VALUE, message = "Course info cannot exceed 65535 characters.")
    @Column(name = "course_info")
    private String courseInfo;

    // Major director_id : User user_id (director) = n : 1
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "director_id")
    private User user;

    public enum MajorStatus {
        ACTIVE,
        INACTIVE
    }

}
