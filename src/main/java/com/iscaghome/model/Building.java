package com.iscaghome.model;

import com.sun.istack.NotNull;
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
@Table(name="Building", schema = "iscaghome")
public class Building extends  AuditModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "buildingId")
    private Long id;

    @NotNull
    @Column(name = "buildingName")
    private String label;

    @NotNull
    @Column(name = "buildingLocation")
    private String location;

}
