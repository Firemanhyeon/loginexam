package org.example.logintest.user.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "jwt_tokens")
public class JWTToken {

    @Id
    private String tokenId;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String token;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String authentication;

    // getters and setters
}