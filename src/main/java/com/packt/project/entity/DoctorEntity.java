package com.packt.project.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Data

@Entity
@Table(name = "Doctor")
@EntityListeners(AuditingEntityListener.class)
@NamedQueries(
        @NamedQuery(
                name = "findByLocationAndSpeciality",
                query = "FROM DoctorEntity d WHERE LOWER(d.location) = LOWER(:location) AND " +
                        "LOWER(d.speciality) = LOWER(:speciality) "
        )
)
public class DoctorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "first_name")
    @NotNull
    private String firstName;

    @Column(name = "last_namee")
    @NotNull
    private String lastName;

    @Email
    @Column(unique = true)
    private String email;

    @Column(name = "security_code")
    private String securityCode;

    @NotNull
    private String location;

    @NotNull
    private String speciality;

    @Column(name = "created_at")
    @CreatedDate
    private Instant createdAt;

    @Column(name = "last_modified_at")
    @LastModifiedDate
    private Instant lastModifiedAt;

}
