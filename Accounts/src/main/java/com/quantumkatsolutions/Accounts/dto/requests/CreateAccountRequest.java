package com.quantumkatsolutions.Accounts.dto.requests;

import com.quantumkatsolutions.Accounts.entity.enums.AccountStatus;
import com.quantumkatsolutions.Accounts.entity.enums.AccountType;

public record CreateAccountRequest(
        Long customerId,
        AccountType accountType,
        AccountStatus accountStatus
) {
}
