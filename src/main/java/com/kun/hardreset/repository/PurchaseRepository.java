package com.kun.hardreset.repository;

import com.kun.hardreset.model.Purchase;
import org.springframework.stereotype.Repository;

@Repository
public class PurchaseRepository extends BaseRepository<Purchase> {

    @Override
    protected String getEntityName() {
        return Purchase.class.getSimpleName();
    }
}
