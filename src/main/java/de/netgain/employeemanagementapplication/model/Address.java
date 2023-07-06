package de.netgain.employeemanagementapplication.model;

import jakarta.persistence.Embeddable;

/**
 * Representation of a physical location.
 *
 * @author MirkoSchulze
 */
@Embeddable
public class Address {

    private String street;
    private String houseNumber;
    private String zipCode;
    private String city;
    private String country;

    public Address() {
    }

    public Address(String street, String houseNumber, String zipCode, String city, String country) {
        this.street = street;
        this.houseNumber = houseNumber;
        this.zipCode = zipCode;
        this.city = city;
        this.country = country;
    }

    //<editor-fold defaultstate="collapsed" desc="getter/setter">
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    //</editor-fold>

    @Override
    public String toString() {
        return String.format("%s %s, %s %s, %s", this.street, this.houseNumber, this.zipCode, this.city, this.country);
    }

}
