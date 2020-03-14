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
@Table(name="RoomStatus", schema = "iscaghome")
public class RoomStatus extends AuditModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roomStatusId")
    private Long id;
    @Column(name = "roomStatusName")
    private String label;
    @Column(name = "roomStatusDescription")
    private String description;

}
