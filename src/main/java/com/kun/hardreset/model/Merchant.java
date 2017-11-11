package com.kun.hardreset.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Merchant {
    @Id
    private String Id;
    private String Name;
    private String Category;
    private String Street_Number;
    private String Street_Name;
    private String City;
    private String Zip;
    private double Lat;
    private double Lng;

    public String getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public String getCategory() {
        return Category;
    }

    public String getStreet_Number() {
        return Street_Number;
    }

    public String getStreet_Name() {
        return Street_Name;
    }

    public String getCity() {
        return City;
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
}
