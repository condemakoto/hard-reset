package com.kun.hardreset.service;

import com.kun.hardreset.model.Transfer;
import com.kun.hardreset.repository.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TransferService {
    @Autowired
    public TransferRepository transferRepository;

    @Transactional
    public void create(List<Transfer> transfers) {

        transferRepository.create(transfers);
    }

    @Transactional
    public void deleteAll() {
        transferRepository.deleteAll();
    }

    @Transactional
    public List<Transfer> findAll() {
        return transferRepository.findAll();
    }
}
