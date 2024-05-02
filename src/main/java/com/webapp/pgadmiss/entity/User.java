package com.webapp.pgadmiss.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "user")
@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
public class User extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;

    @NotBlank(message = "User name cannot be null")
    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "nationality")
    private String nationality;

    @NotBlank(message = "Email cannot be null")
    @Email(message = "Invalid email format")
    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private UserRole role;


}