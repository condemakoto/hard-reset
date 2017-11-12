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

	public void setType(String type) {
		Type = type;
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
