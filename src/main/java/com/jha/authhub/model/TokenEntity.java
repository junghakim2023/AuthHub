package com.jha.authhub.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="token")
public class TokenEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tokenIndex;


    private String tokenKey;

    @OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="userIndex", nullable=true)
    private UserEntity user;

    private String accessToken;
    private String refreshToken;
}
