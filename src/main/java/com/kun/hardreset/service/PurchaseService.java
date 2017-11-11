package com.kun.hardreset.service;

import com.kun.hardreset.model.Purchase;
import com.kun.hardreset.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Transactional
    public List<Purchase> findAll() {
        return purchaseRepository.findAll();
    }

    @Transactional
    public void create(List<Purchase> purchases) {
        purchaseRepository.create(purchases);
    }

    @Transactional
    public void deleteAll() {
        purchaseRepository.deleteAll();
    }

}
