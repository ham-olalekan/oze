package com.oze.staffservice.db.models;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@Builder
@Table(name = "staff")
@AllArgsConstructor
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long Id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "staff_id", unique = true)
    private String uuid;

    @Column(name = "registration_date", nullable = false)
    private Date registrationDate;

    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    @Column(name = "updated_at", nullable = false)
    private Date updatedAt;


    public Staff() {
    }

    @PrePersist
    @PreUpdate
    private void setDefault(){
        if( createdAt == null){
            createdAt =  new Date();
        }
        updatedAt = new Date();
    }
}
