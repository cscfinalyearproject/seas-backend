package com.tumbwe.pathventure.repository;

import com.tumbwe.pathventure.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, UUID> {
}
