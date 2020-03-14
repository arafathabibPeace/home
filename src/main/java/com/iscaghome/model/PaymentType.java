package com.iscaghome.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="PaymentType", schema = "iscaghome")
public class PaymentType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "paymentTypeId")
    private Long id;
    @Column(name = "paymentTypeName")
    private String name;
    @Column(name = "paymentTypeDescription")
    private String description;
}
