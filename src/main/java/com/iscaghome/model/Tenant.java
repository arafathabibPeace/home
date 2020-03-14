package com.iscaghome.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="Tenant", schema = "iscaghome")
public class Tenant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tenantId")
    private Long id;
    @Column(name = "tenantNumber")
    private String tenantNumber;
    @Column(name = "personId")
    private Long personId;


}
