package com.quantumkatsolutions.Accounts.dto.response;

import com.quantumkatsolutions.Accounts.entity.enums.AccountStatus;
import com.quantumkatsolutions.Accounts.entity.enums.AccountType;

import java.time.LocalDateTime;

public record AccountResponse(
        Long accountId,
        Long customerId,
        AccountType accountType,
        AccountStatus accountStatus,
        double availableBalance,
        double balance,
        LocalDateTime createdAt,
        LocalDateTime lastUpdatedAt
) {
}
