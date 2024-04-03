package com.example.userservicejan24.repo;

import com.example.userservicejan24.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface TokenRepository extends JpaRepository<Token, Long>{
    Optional<Token> findByValueAndDeletedEquals(String token, boolean isDeleted);
}