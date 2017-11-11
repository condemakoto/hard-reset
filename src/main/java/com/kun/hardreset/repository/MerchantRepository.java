package com.kun.hardreset.repository;


import com.kun.hardreset.model.Merchant;
import org.springframework.stereotype.Repository;

@Repository
public class MerchantRepository extends BaseRepository<Merchant> {

    @Override
    protected String getEntityName() {
        return Merchant.class.getSimpleName();
    }

}
