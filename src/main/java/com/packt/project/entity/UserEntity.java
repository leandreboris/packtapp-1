package com.packt.project.entity;

import com.packt.project.enumerate.GenderEnum;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Entity
@Data
@Table(name = "app_user")
@EntityListeners(AuditingEntityListener.class)
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @Email
    @NotNull
    private String email;

    @NotNull
    private String password;

    @Column(name = "first_name")
    @NotNull
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private Integer age;

    private GenderEnum gender;

    @Column(name = "contact_number")
    private String contactNumber;

    @Column(name = "alternate_contact")
    private String alternateContact;

    private String address;

    @Column(name = "city_code")
    private String cityCode;

    @Column(name = "state_code")
    private String stateCode;

    @Column(name = "country_code")
    private String countryCode;

    @Column(name = "created_at")
    @CreatedDate
    private Instant createdAt;

    @Column(name = "last_modified_at")
    @LastModifiedDate
    private Instant lastModifiedAt;

}
