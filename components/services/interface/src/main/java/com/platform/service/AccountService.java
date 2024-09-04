package com.platform.service;

import com.platform.model.Account;

import java.util.UUID;

public interface AccountService {

    Account createAccount(Account account);

    Account getAccount(UUID accountId);

    Account updateAccount(UUID accountId, Account account);

    void deleteAccount(UUID accountId);
}
