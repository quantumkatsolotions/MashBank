package com.quantumkatsolutions.Accounts.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AccountType {
    SAVINGS("63"),
    CURRENT("55"),
    LOAN("88"),
    VAULT("46");

    private final String prefix;

}
