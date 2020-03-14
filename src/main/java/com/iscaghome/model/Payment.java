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
@Table(name="Payment", schema = "iscaghome")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "paymentId")
    private Long id;
    @Column(name = "amount")
    private Double amount;
    @Column(name = "datePaid")
    private Date datePaid;
    @Column(name = "notes")
    private String notes;
    @Column(name = "paymentTypeId")
    private Long paymentTypeId;
    @Column(name = "billingId")
    private Long billingId;
    @Column(name = "advancePaymentId")
    private Long advancePaymentId;

}
