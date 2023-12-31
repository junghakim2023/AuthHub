package com.jha.authhub.repository;

import com.jha.authhub.model.TokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<TokenEntity, Long> {
    Optional<TokenEntity> findByRefreshToken(String refreshToken);
    Optional<TokenEntity> findByTokenKey(String tokenKey);

    Optional<TokenEntity> findByUser_UserIndex(Long userIndex);
}