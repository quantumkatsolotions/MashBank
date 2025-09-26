package com.quantumkatsolutions.Accounts.mapper;

import com.quantumkatsolutions.Accounts.domain.AccountNumberGenerator;
import com.quantumkatsolutions.Accounts.dto.requests.CreateAccountRequest;
import com.quantumkatsolutions.Accounts.dto.response.AccountResponse;
import com.quantumkatsolutions.Accounts.entity.Account;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;


@UtilityClass
public class ModelMapper {
    public Account toAccount(CreateAccountRequest request) throws Exception {
        if (request == null)
            throw new Exception("request object is null");

        return Account
                .builder()
                .customerId(request.customerId())
                .accountNumber(AccountNumberGenerator.generateAccountNumber(request.accountType()))
                .accountStatus(request.accountStatus())
                .createdAt(LocalDateTime.now())
                .lastUpdatedAt(LocalDateTime.now())
                .build();
    }


    public AccountResponse toAccountResponse(Account account) {
        if (account == null) {
            throw new InvalidAccountException("Account object is null");
        }

        return new AccountResponse(
                account.getAccountId(),
                account.getCustomerId(),
                account.getAccountType(),
                account.getAccountStatus(),
                account.getAvailableBalance(),
                account.getBalance(),
                account.getCreatedAt(),
                account.getLastUpdatedAt()
        );
    }

}
