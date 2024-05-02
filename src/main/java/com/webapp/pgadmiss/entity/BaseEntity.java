package com.webapp.pgadmiss.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter @Setter @ToString
public class BaseEntity implements Serializable{


    private static final long serialVersionUID = 1L;

    @CreatedDate
    @Column(name = "create_date", updatable = false)
    private LocalDateTime createDate;

    @LastModifiedDate
    @Column(name = "update_date", insertable = false)
    private LocalDateTime updateDate;


    public enum Gender {
        MALE,
        FEMALE
    }

    public enum UserRole {
        STUDENT,
        ADMIN,
        DIRECTOR
    }
}
