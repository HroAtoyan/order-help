package com.platform.springjpa;

import com.platform.entity.AccountEntity;
import com.platform.entity.AddressEntity;
import com.platform.exceptions.accountexceptions.AccountAlreadyExistsException;
import com.platform.exceptions.accountexceptions.AccountApiException;
import com.platform.exceptions.accountexceptions.AccountBadRequestException;
import com.platform.exceptions.accountexceptions.AccountNotFoundException;
import com.platform.model.Account;
import com.platform.repository.AccountRepository;
import com.platform.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;



@Service
public class AccountSpringJpa implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account createAccount(Account account) {
        AccountEntity accountEntity;
        if (account.getAccountId() != null) {
            throw new AccountBadRequestException("Account id must be null");
        }
        try {
            accountEntity = accountRepository.getByAccountName(account.getAccountName());
        } catch (Exception e) {
            throw new AccountApiException("Problem during creating account");
        }
        if (accountEntity != null) {
            throw new AccountAlreadyExistsException("Account already exists with given name");
        }

        accountEntity = AccountEntity.builder()
                .accountName(account.getAccountName())
                .accountType(account.getAccountType()).build();


        try {
            accountEntity = accountRepository.save(accountEntity);
        } catch (Exception e) {
            throw new AccountApiException("Problem during creating account");


        }
        return accountEntity.toAccount();
    }

    @Override
    public Account getAccount(UUID accountId) {
        AccountEntity entity = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException("Account not found with given ID"));

        return entity.toAccount();
    }

    @Override
    public Account updateAccount(UUID accountId, Account account) {
        AccountEntity existingAccountEntity = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException("Account not found with given id"));

        existingAccountEntity.setAccountName(account.getAccountName());
        existingAccountEntity.setAccountType(account.getAccountType());

        try {
            existingAccountEntity = accountRepository.save(existingAccountEntity);
        } catch (Exception e) {
            throw new AccountApiException("Problem during updating account");
        }

        return existingAccountEntity.toAccount();
    }

    @Override
    public List<Account> getAccounts() {
        return List.of();
    }

    @Override
    public void deleteAccount(UUID accountId) {
        AccountEntity existingAccountEntity = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException("Account not found with given id"));

        try {
            accountRepository.delete(existingAccountEntity);
        } catch (Exception e) {
            throw new AccountApiException("Problem during deleting account");
        }
    }

}
