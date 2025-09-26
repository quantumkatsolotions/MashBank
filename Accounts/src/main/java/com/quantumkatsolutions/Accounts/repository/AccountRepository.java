package com.quantumkatsolutions.Accounts.repository;

import com.quantumkatsolutions.Accounts.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findAccountByAccountIdAndCustomerId(Long accountId, Long customerId);
    List<Account> findAccountByCustomerId(Long customerId);

}
