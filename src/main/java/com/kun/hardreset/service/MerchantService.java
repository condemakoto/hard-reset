package com.kun.hardreset.service;

import com.kun.hardreset.model.Merchant;
import com.kun.hardreset.repository.MerchantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MerchantService {

    @Autowired
    private MerchantRepository merchantRepository;

    @Transactional
    public void create(List<Merchant> merchants) {
        merchantRepository.create(merchants);
    }

    @Transactional
    public void deleteAll() {
        merchantRepository.deleteAll();
    }

    @Transactional
    public List<Merchant> findAll() {
        return merchantRepository.findAll();
    }

}
