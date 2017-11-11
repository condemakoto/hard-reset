package com.kun.hardreset.service;

import com.kun.hardreset.model.Account;
import com.kun.hardreset.repository.AccountsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AccountsService {

    @Autowired
    private AccountsRepository accounsRepository;

    @Transactional
    public List<Account> findAll() {
        return accounsRepository.getAccounts();
    }

    @Transactional
    public void create(List<Account> accounts) {
        accounsRepository.create(accounts);
    }

    @Transactional
    public void deleteAll() {
        accounsRepository.clearEntity();
    }

}
