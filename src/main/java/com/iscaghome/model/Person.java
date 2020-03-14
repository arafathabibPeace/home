package com.iscaghome.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Blob;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="Person", schema = "iscaghome")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "personId")
    private Long id;
    @Column(name = "firstName")
    private String firstName;
    @Column(name = "middleName")
    private String middleName;
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "gender")
    private String gender;
    @Column(name = "contactNumber")
    private String contactNumber;
    @Column(name = "presentAddress")
    private String presentAddress;
    @Column(name = "homeAddress")
    private String homeAddress;
    @Column(name = "email")
    private String email;
    @Lob
    @Column(name = "picture")
    private byte[] imageData;
}
