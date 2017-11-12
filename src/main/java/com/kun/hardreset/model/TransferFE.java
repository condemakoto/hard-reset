package com.kun.hardreset.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TransferFE {

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
    private Integer ScoreRisk;
    private TypeRisk NameTypeRisk;

    private String accountId;
	private String accountType;
	private String nickname;
	private float rewards;
	private double balance;
	private String accountNumber;
	private String customerId;

    public enum TypeRisk {
    	BLACKLIST,HORARIO,DESTINO_POCO_COMUN
	}
        
    public Integer getScoreRisk() {
		return ScoreRisk;
	}

	public void setScoreRisk(Integer scoreRisk) {
		ScoreRisk = scoreRisk;
	}

	public TypeRisk getNameTypeRisk() {
		return NameTypeRisk;
	}

	public void setNameTypeRisk(TypeRisk NameTypeRisk) {
		this.NameTypeRisk = NameTypeRisk;
	}

	public void setId(String id) {
		Id = id;
	}

	public void setAccountType(String accountType) {
		Type = accountType;
	}

	public void setTransaction_Date(String transaction_Date) {
		Transaction_Date = transaction_Date;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public void setMedium(String medium) {
		Medium = medium;
	}

	public void setPlayer_Id(String player_Id) {
		Player_Id = player_Id;
	}

	public void setPayee_Id(String payee_Id) {
		Payee_Id = payee_Id;
	}

	public void setAmount(double amount) {
		Amount = amount;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getId() {
        return Id;
    }

    public String getAccountType() {
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

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public void setRewards(float rewards) {
		this.rewards = rewards;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getType() {
		return Type;
	}

	public String getAccountId() {
		return accountId;
	}

	public String getNickname() {
		return nickname;
	}

	public float getRewards() {
		return rewards;
	}

	public double getBalance() {
		return balance;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public String getCustomerId() {
		return customerId;
	}
}
