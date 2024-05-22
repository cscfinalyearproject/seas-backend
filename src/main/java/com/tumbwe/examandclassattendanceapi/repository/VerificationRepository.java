package com.tumbwe.pathventure.repository;

import com.tumbwe.pathventure.model.User;
import com.tumbwe.pathventure.model.Verification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface VerificationRepository extends JpaRepository<Verification, UUID> {
    Optional<Verification> findByOtp(String otp);
    Optional<Verification> findVerificationByUser(User user);
}
