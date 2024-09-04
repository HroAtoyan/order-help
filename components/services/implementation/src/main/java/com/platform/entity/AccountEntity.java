package com.platform.entity;

import com.platform.constants.DatabaseConstants;
import com.platform.enums.AccountType;
import com.platform.model.Account;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = DatabaseConstants.ACCOUNT_TABLE, schema = DatabaseConstants.SCHEMA)
public class AccountEntity {

    @Id
    @Column(name = "account_id")
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "UUIDGenerator")
    private UUID accountId;

    @Column(name = "account_name", unique = true)
    private String accountName;

    @Column(name = "account_type")
    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @OneToMany(mappedBy = "accountEntity")
    private List<UserEntity> userEntity;

    public Account toAccount() {
        Account account = new Account();
        account.setAccountId(accountId);
        account.setAccountName(accountName);
        account.setAccountType(accountType);

        return account;

    }

}
