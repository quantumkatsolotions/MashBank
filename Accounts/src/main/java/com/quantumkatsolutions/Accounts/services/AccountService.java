package com.quantumkatsolutions.Accounts.services;

import com.quantumkatsolutions.Accounts.dto.requests.CreateAccountRequest;
import com.quantumkatsolutions.Accounts.dto.response.AccountResponse;
import com.quantumkatsolutions.Accounts.exceptions.AccountNotFoundException;
import com.quantumkatsolutions.Accounts.exceptions.FailedToCreateAccountException;

import java.util.List;

public interface AccountService {
    AccountResponse createAccount(CreateAccountRequest request) throws FailedToCreateAccountException, Exception;
    AccountResponse getSingleAccount(Long customerId, Long accountId) throws Exception;
    List<AccountResponse> getAccounts(Long customerId) throws Exception;
    AccountResponse closeAccount(Long customerId, Long accountId) throws Exception;
    AccountResponse freezeAccount(Long customerId, Long accountId) throws Exception;
    AccountResponse unFreezeAccount(Long customerId, Long accountUd) throws AccountNotFoundException;
}
