package com.quantumkatsolutions.Accounts.domain;

import com.quantumkatsolutions.Accounts.entity.enums.AccountType;
import lombok.experimental.UtilityClass;

import java.util.concurrent.ThreadLocalRandom;

@UtilityClass
public class AccountNumberGenerator {
    public String generateAccountNumber(AccountType accountType){
        var prefix = accountType.getPrefix();

        long randomNumber = ThreadLocalRandom.current().nextLong(10000000L, 99999999L);

        return prefix + randomNumber;
    }
}
