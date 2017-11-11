package com.kun.hardreset.repository;

import com.kun.hardreset.model.Account;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class AccounsRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Account> getAccounts() {
        return entityManager.createQuery("select c from Account c").getResultList();
    }

    public void create(Account account) {
        entityManager.persist(account);
    }

    public void create(List<Account> accounts) {
        for (Account account: accounts) {
            entityManager.persist(account);
        }
    }

    public void delete(Account account) {
        entityManager.remove(account);
    }

    public void clearEntity() {
        entityManager.createQuery("delete from Account").executeUpdate();
    }

}
