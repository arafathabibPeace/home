package com.iscaghome.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="RoomCategory", schema = "iscaghome")
public class RoomCategory extends AuditModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roomCategoryId")
    private Long id;
    @Column(name = "categoryName")
    private String label;
    @Column(name = "categoryRate")
    private double rate;
    @Column(name = "rateUnit")
    private String rateUnit;
    @Column(name = "allowedOccupantsNumber")
    private int permittedOccupantsNumber;
    @Column(name = "description")
    private String description;


}
