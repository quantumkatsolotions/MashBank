package com.quantumkatsolutions.Accounts.services;

import com.quantumkatsolutions.Accounts.dto.requests.CreateAccountRequest;
import com.quantumkatsolutions.Accounts.dto.response.AccountResponse;
import com.quantumkatsolutions.Accounts.entity.Account;
import com.quantumkatsolutions.Accounts.entity.enums.AccountStatus;
import com.quantumkatsolutions.Accounts.exceptions.AccountNotFoundException;
import com.quantumkatsolutions.Accounts.exceptions.FailedToCreateAccountException;
import com.quantumkatsolutions.Accounts.mapper.ModelMapper;
import com.quantumkatsolutions.Accounts.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository repository;


    @Override
    public AccountResponse createAccount(CreateAccountRequest request) throws FailedToCreateAccountException, Exception {

        validateData(request);

        Account account = ModelMapper.toAccount(request);

        return ModelMapper.toAccountResponse(repository.save(account));
    }

    @Override
    public AccountResponse getSingleAccount(Long customerId, Long accountId) throws Exception {
        validateIds(customerId, accountId);

        Account account = repository.findAccountByAccountIdAndCustomerId(accountId, customerId);

        if (account == null)
            throw new AccountNotFoundException("Account not found");

        return ModelMapper.toAccountResponse(account);
    }

    @Override
    public List<AccountResponse> getAccounts(Long customerId) throws Exception {
        try{
            if (customerId == null || customerId <= 0) {
                throw new AccountNotFoundException("Invalid customer ID");
            }

            List<Account> accounts = repository.findAccountByCustomerId(customerId);

            if (accounts == null || accounts.isEmpty()) {
                throw new AccountNotFoundException("No accounts found for customerId: " + customerId);
            }

            return accounts.stream()
                    .map(ModelMapper::toAccountResponse)
                    .toList();
        }catch (Exception e){
            throw new Exception("Something went wrong");
        }
    }


    @Override
    public AccountResponse closeAccount(Long customerId, Long accountId) throws Exception {
        try{
            if(customerId == null || accountId == null){
                throw new AccountNotFoundException("Failed to retrieve account | Incorrect customer id or account id");
            }
            Account account = repository.findAccountByAccountIdAndCustomerId(accountId, customerId);

            if (account == null){
                throw new AccountNotFoundException("Account not found");
            }

            account.setAccountStatus(AccountStatus.CLOSED);
            Account updatedAccount = repository.save(account);
            return ModelMapper.toAccountResponse(updatedAccount);

        }catch (Exception e){
            throw new Exception("Failed to close account");
        }

    }

    @Override
    public AccountResponse freezeAccount(Long customerId, Long accountId) throws Exception {
        try{
            if(customerId == null || accountId == null){
                throw new AccountNotFoundException("Failed to retrieve account | Incorrect customer id or account id");
            }
            Account account = repository.findAccountByAccountIdAndCustomerId(accountId, customerId);

            if (account == null){
                throw new AccountNotFoundException("Account not found");
            }
            account.setAccountStatus(AccountStatus.FROZEN);
            Account updatedAccount = repository.save(account);

            return ModelMapper.toAccountResponse(updatedAccount);
        }catch (Exception e){
            throw new Exception("Something went wrong | Failed to free account");
        }
    }

    @Override
    public AccountResponse unFreezeAccount(Long customerId, Long accountUd) throws AccountNotFoundException {
       try{
           if (customerId == null || accountUd == null){
               throw new AccountNotFoundException("Account not found");
           }

           Account account = repository.findAccountByAccountIdAndCustomerId(accountUd, customerId);

           if (account == null){
               throw new AccountNotFoundException("Account not found");
           }

           account.setAccountStatus(AccountStatus.OPEN);
           Account updatedAccount = repository.save(account);

           return ModelMapper.toAccountResponse(updatedAccount);

       } catch (Exception e) {
           throw new RuntimeException(e);
       }
    }

    // Helpers
    private void validateData(CreateAccountRequest request) throws FailedToCreateAccountException {
        if (request == null) {
            throw new FailedToCreateAccountException("Failed to create account | request object is null");
        }

        if (request.accountStatus() == null ||
                request.accountType() == null ||
                request.customerId() == null) {
            throw new FailedToCreateAccountException("Failed to create account || Make sure you inserted all values");
        }
    }

    private void validateIds(Long customerId, Long accountId) throws Exception {
        if (customerId == 0 || accountId == 0)
            throw new Exception("Customer Id or Account Id is required");
    }

}
