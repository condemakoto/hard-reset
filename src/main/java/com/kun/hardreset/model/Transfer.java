package com.kun.hardreset.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Transfer {

    @Id
    private String Id;
    private String Type;
    private String Transaction_Date;
    private String Status;
    private String Medium;
    private String Player_Id;
    private String Payee_Id;
    private double Amount;
    private String Description;

    public String getId() {
        return Id;
    }

    public String getType() {
        return Type;
    }

    public String getTransaction_Date() {
        return Transaction_Date;
    }

    public String getStatus() {
        return Status;
    }

    public String getMedium() {
        return Medium;
    }

    public String getPlayer_Id() {
        return Player_Id;
    }

    public String getPayee_Id() {
        return Payee_Id;
    }

    public double getAmount() {
        return Amount;
    }

    public String getDescription() {
        return Description;
    }
}
