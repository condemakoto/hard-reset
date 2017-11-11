package com.kun.hardreset.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Purchase {
    @Id
    private String Id;
    private String Merchant_Id;
    private String Player_Id;
    private String Status;
    private String Purchase_Date;
    private double Amount;
    private String Medium;
    private String Description;
    private String Type;

    public String getId() {
        return Id;
    }

    public String getMerchant_Id() {
        return Merchant_Id;
    }

    public String getPlayer_Id() {
        return Player_Id;
    }

    public String getStatus() {
        return Status;
    }

    public String getPurchase_Date() {
        return Purchase_Date;
    }

    public double getAmount() {
        return Amount;
    }

    public String getMedium() {
        return Medium;
    }

    public String getDescription() {
        return Description;
    }

    public String getType() {
        return Type;
    }
}
