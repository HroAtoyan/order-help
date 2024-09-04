package com.platform.model;

import com.platform.enums.AccountType;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Account {

    private UUID accountId;
    private String accountName;
    private AccountType accountType;

}
