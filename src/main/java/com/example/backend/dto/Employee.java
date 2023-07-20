package com.example.backend.dto;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity

public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String firstName;
    private String lastName;
    private String email;

}
