package com.kun.hardreset.model;

public class Account {

    private String Id;
    private String Type;
    private String Nickname;
    private float Rewards;
    private double Balance;
    private String Account_Number;
    private String Customer_Id;

    public String getId() {
        return Id;
    }

    public String getType() {
        return Type;
    }

    public String getNickname() {
        return Nickname;
    }

    public float getRewards() {
        return Rewards;
    }

    public double getBalance() {
        return Balance;
    }

    public String getAccount_Number() {
        return Account_Number;
    }

    public String getCustomer_Id() {
        return Customer_Id;
    }
}
