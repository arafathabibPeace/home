package com.iscaghome.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="TenantApplication", schema = "iscaghome")
public class TenantApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tenantApplicationId")
    private Long id;
    @Column(name = "dateApplied")
    private Date dateApplied;
    @Column(name = "dateApproved")
    private Date dateApproved;
    @Column(name = "tenantId")
    private Long tenantId;
    @Column(name = "roomId")
    private Long roomId;
    @Column(name = "applicationStatusId")
    private Long statusId;
}
