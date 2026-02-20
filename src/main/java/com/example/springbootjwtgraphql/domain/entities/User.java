package com.example.springbootjwtgraphql.domain.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String firstName;
    private String lastName;

    private String username;

    private String password;

    private String email;

    private String confirmPassword;
}
