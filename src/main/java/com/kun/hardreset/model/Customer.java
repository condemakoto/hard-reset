package com.kun.hardreset.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Customer {

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
    private boolean Gender;

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

    public boolean isGender() {
        return Gender;
    }
}
