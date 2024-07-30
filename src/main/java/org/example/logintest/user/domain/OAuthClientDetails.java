package org.example.logintest.user.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "oauth_client_details")
@Getter
@Setter
public class OAuthClientDetails {

    @Id
    private String clientId;

    @Column(nullable = false)
    private String clientSecret;

    private String scope;
    private String authorizedGrantTypes;
    private String authorities;

    private Integer accessTokenValidity;
    private Integer refreshTokenValidity;

    @Column(columnDefinition = "TEXT")
    private String additionalInformation;

    private Boolean autoapprove;

}