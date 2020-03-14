package com.iscaghome.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="ApplicationStatus", schema = "iscaghome")
public class ApplicationStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "applicationStatusId")
    private Long id;
    @Column(name = "applicationStatusName")
    private String label;
    @Column(name = "applicationStatusDescription")
    private String description;
}
