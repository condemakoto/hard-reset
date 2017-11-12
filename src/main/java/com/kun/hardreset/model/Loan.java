package com.kun.hardreset.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Loan {

	@Id
    private String Id;
    private String Account_Id;
    private String Type;    
    private String Creation_Date;
    private String Status;
    private String Credit_Score;
    private String Monthly_Payment;
    private String Amount;
    private String Description;
    private String AmountOwed;
    
    
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getAccount_Id() {
		return Account_Id;
	}
	public void setAccount_Id(String account_Id) {
		Account_Id = account_Id;
	}
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
	public String getCreation_Date() {
		return Creation_Date;
	}
	public void setCreation_Date(String creation_Date) {
		Creation_Date = creation_Date;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public String getCredit_Score() {
		return Credit_Score;
	}
	public void setCredit_Score(String credit_Score) {
		Credit_Score = credit_Score;
	}
	public String getMonthly_Payment() {
		return Monthly_Payment;
	}
	public void setMonthly_Payment(String monthly_Payment) {
		Monthly_Payment = monthly_Payment;
	}
	public String getAmount() {
		return Amount;
	}
	public void setAmount(String amount) {
		Amount = amount;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getAmountOwed() {
		return AmountOwed;
	}
	public void setAmountOwed(String amountOwed) {
		AmountOwed = amountOwed;
	}
      
}
