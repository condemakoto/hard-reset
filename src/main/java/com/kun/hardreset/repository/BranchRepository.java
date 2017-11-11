package com.kun.hardreset.repository;

import com.kun.hardreset.model.Branch;
import org.springframework.stereotype.Repository;

@Repository
public class BranchRepository extends BaseRepository<Branch> {

    @Override
    protected String getEntityName() {
        return Branch.class.getSimpleName();
    }
}
