package com.kun.hardreset.model;

import java.util.List;


public class CustomerToCompare {

    private String accountId;
    private double Lat;
    private double Lng;
    //private int age;

    //Accounts
    private int accountCount;
    private double totalBalance;
    private double totalRewards;

    //Transfers
    private int transferCount;
    private double totalTransfersAmount;

    public void setCostumer(Customer customer) {
        this.accountId = customer.getId();
        this.Lat = customer.getLat();
        this.Lng = customer.getLng();
    }

    public void setAccounts(List<Account> accounts) {
        if (accounts != null && !accounts.isEmpty()) {
            accountCount = accounts.size();

            for (Account account : accounts) {
                totalBalance += account.getBalance();
                totalRewards += account.getRewards();
            }
        }
    }

    public void setTransfers(List<Transfer> transfers) {
        if (transfers != null && !transfers.isEmpty()) {
            transferCount = transfers.size();
            for (Transfer transfer : transfers) {
                totalTransfersAmount += transfer.getAmount();
            }
        }
    }

    public String getAccountId() {
        return accountId;
    }

    public double getLat() {
        return Lat;
    }

    public double getLng() {
        return Lng;
    }

    public int getAccountCount() {
        return accountCount;
    }

    public double getTotalBalance() {
        return totalBalance;
    }

    public double getTotalRewards() {
        return totalRewards;
    }

    public int getTransferCount() {
        return transferCount;
    }

    public double getTotalTransfersAmount() {
        return totalTransfersAmount;
    }
}
