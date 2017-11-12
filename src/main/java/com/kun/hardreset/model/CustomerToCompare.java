package com.kun.hardreset.model;

import java.util.List;


public class CustomerToCompare {

    private String accountId;
    private double Lat;
    private double Lng;
    //private int age;

    //Accounts
    private double accountCount;
    private double totalBalance;
    private double totalRewards;

    //Transfers
    private double transferCount;
    private double totalTransfersAmount;

    private double groupIndex;

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

    public double getAccountCount() {
        return accountCount;
    }

    public double getTotalBalance() {
        return totalBalance;
    }

    public double getTotalRewards() {
        return totalRewards;
    }

    public double getTransferCount() {
        return transferCount;
    }

    public double getTotalTransfersAmount() {
        return totalTransfersAmount;
    }

    public double getGroupIndex() {
        return groupIndex;
    }

    public void setGroupIndex(double groupIndex) {
        this.groupIndex = groupIndex;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public void setLat(double lat) {
        Lat = lat;
    }

    public void setLng(double lng) {
        Lng = lng;
    }

    public void setAccountCount(double accountCount) {
        this.accountCount = accountCount;
    }

    public void setTotalBalance(double totalBalance) {
        this.totalBalance = totalBalance;
    }

    public void setTotalRewards(double totalRewards) {
        this.totalRewards = totalRewards;
    }

    public void setTransferCount(double transferCount) {
        this.transferCount = transferCount;
    }

    public void setTotalTransfersAmount(double totalTransfersAmount) {
        this.totalTransfersAmount = totalTransfersAmount;
    }
}
