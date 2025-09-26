package com.quantumkatsolutions.Accounts.entity;

import com.quantumkatsolutions.Accounts.entity.enums.AccountStatus;
import com.quantumkatsolutions.Accounts.entity.enums.AccountType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long accountId;
    private Long customerId;
    @Embedded
    private AccountType accountType;
    @Embedded
    private String accountNumber;
    private AccountStatus accountStatus;
    private double availableBalance;
    private double balance;
    private LocalDateTime createdAt;
    private LocalDateTime lastUpdatedAt;
}
