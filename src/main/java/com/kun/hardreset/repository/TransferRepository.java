package com.kun.hardreset.repository;

import com.kun.hardreset.model.Transfer;
import org.springframework.stereotype.Repository;

@Repository
public class TransferRepository extends BaseRepository<Transfer> {

    @Override
    protected String getEntityName() {
        return Transfer.class.getSimpleName();
    }
}
