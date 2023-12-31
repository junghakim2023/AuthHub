package com.jha.authhub.repository;

import com.jha.authhub.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findOneByName(String name);
    Optional<UserEntity> findOneByEmail(String email);
}
