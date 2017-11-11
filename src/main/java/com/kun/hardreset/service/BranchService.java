package com.kun.hardreset.service;

import com.kun.hardreset.model.Branch;
import com.kun.hardreset.repository.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BranchService {

    @Autowired
    private BranchRepository branchRepository;

    @Transactional
    public void create(List<Branch> branches) {
        for (Branch branch : branches) {
            if (branch.getStreet_name() != null && branch.getStreet_name().length() > 200) {
                branch.setStreet_name(branch.getStreet_name().replace(" ", ""));
            }
        }
        branchRepository.create(branches);
    }

    @Transactional
    public void deleteAll() {
        branchRepository.deleteAll();
    }

    @Transactional
    public List<Branch> findAll() {
        return branchRepository.findAll();
    }
}
