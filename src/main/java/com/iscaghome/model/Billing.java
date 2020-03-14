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
@Table(name="Billing", schema = "iscaghome")
public class Billing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "billingId")
    private Long id;
    @Column(name = "billingDate")
    private Date billingDate;
    @Column(name = "dueDate")
    private Date dueDate;
    @Column(name = "billingAmount")
    private Double amount;
    @Column(name = "tenantId")
    private Long tenantId;
    @Column(name = "roomId")
    private Long roomId;
    @Column(name = "tenantApplicationId")
    private Long tenantApplicationId;
}
