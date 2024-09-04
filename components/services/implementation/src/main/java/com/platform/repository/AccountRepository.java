package com.platform.repository;

import com.platform.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AccountRepository extends JpaRepository<AccountEntity, UUID> {

    AccountEntity getByAccountName(String name);

}
