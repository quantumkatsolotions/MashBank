package com.quantumkatsolutions.Accounts.controller;

import com.quantumkatsolutions.Accounts.dto.requests.CreateAccountRequest;
import com.quantumkatsolutions.Accounts.dto.response.AccountResponse;
import com.quantumkatsolutions.Accounts.exceptions.FailedToCreateAccountException;
import com.quantumkatsolutions.Accounts.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService service;

    @PostMapping
    public ResponseEntity<Object> createAccount(@RequestBody  CreateAccountRequest request){
        try{
            AccountResponse response = service.createAccount(request);
            return ResponseEntity.ok(response);
        } catch (FailedToCreateAccountException | Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/customer/customerId/account/accountId")
    public ResponseEntity<Object> getSingleAccount(@PathVariable Long customerId, @PathVariable Long accountId){
        try{
            AccountResponse response = service.getSingleAccount(customerId, accountId);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/customer/customerId")
    public ResponseEntity<Object> getAllCustomerAccounts(@PathVariable Long customerId){
        try{
            List<AccountResponse> accountResponses = service.getAccounts(customerId);
            return ResponseEntity.ok(accountResponses);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/customer/customerId/account/accountId")
    public ResponseEntity<Object> closeAccount(@PathVariable Long customerId, @PathVariable Long accountId){
        try{
            AccountResponse response = service.closeAccount(customerId, accountId);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/customer/customerId/account/accountId")
    public ResponseEntity<Object> freezeAccount(@PathVariable Long customerId, @PathVariable Long accountId){
        try{
            AccountResponse response = service.freezeAccount(customerId, accountId);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/customer/customerId/account/accountId")
    public ResponseEntity<Object> unFreezeAccount(@PathVariable Long customerId, @PathVariable Long accountId){
        try{
            AccountResponse response = service.unFreezeAccount(customerId, accountId);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
