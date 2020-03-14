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
@Table(name="AdvancePayment", schema = "iscaghome")
public class AdvancePayment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "advancePaymentId")
    private Long id;
    @Column(name = "balance")
    private Double balance;
    @Column(name = "dateAdded")
    private Date dateAdded;
    @Column(name = "dateLastModified")
    private Date dateLastModified;
}
