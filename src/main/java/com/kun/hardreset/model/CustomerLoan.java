package com.kun.hardreset.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CustomerLoan {

    @Id
    private String Id;
    private String First_Name;
    private String Last_Name;
    private String Doc_Number;

    private String Street_number;
    private String Street_name;
    private String City;
    private String State;
    private String Zip;
    private double Lat;
    private double Lng;
    private String Gender;
    private String Loan;
    
    public String getLoan() {
		return Loan;
	}

	public void setLoan(String loan) {
		Loan = loan;
	}

	public void setId(String id) {
		Id = id;
	}

	public void setFirst_Name(String first_Name) {
		First_Name = first_Name;
	}

	public void setLast_Name(String last_Name) {
		Last_Name = last_Name;
	}

	public void setDoc_Number(String doc_Number) {
		Doc_Number = doc_Number;
	}

	public void setStreet_number(String street_number) {
		Street_number = street_number;
	}

	public void setStreet_name(String street_name) {
		Street_name = street_name;
	}

	public void setCity(String city) {
		City = city;
	}

	public void setState(String state) {
		State = state;
	}

	public void setZip(String zip) {
		Zip = zip;
	}

	public void setLat(double lat) {
		Lat = lat;
	}

	public void setLng(double lng) {
		Lng = lng;
	}


	public String getId() {
        return Id;
    }

    public String getFirst_Name() {
        return First_Name;
    }

    public String getLast_Name() {
        return Last_Name;
    }

    public String getDoc_Number() {
        return Doc_Number;
    }

    public String getStreet_number() {
        return Street_number;
    }

    public String getStreet_name() {
        return Street_name;
    }

    public String getCity() {
        return City;
    }

    public String getState() {
        return State;
    }

    public String getZip() {
        return Zip;
    }

    public double getLat() {
        return Lat;
    }

    public double getLng() {
        return Lng;
    }

	public String getGender() {
		return Gender;
	}

	public void setGender(String gender) {
		Gender = gender;
	}
    
}
