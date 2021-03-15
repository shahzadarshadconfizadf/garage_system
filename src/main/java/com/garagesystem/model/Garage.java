package com.garagesystem.model;

import javax.persistence.*;

@Entity
@Table(name = "garage")
public class Garage {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "garage_sequence")
    @SequenceGenerator(name = "garage_sequence", sequenceName = "garage_sequence", allocationSize = 2)
    @Column(name = "garage_id")
    private long id;


    @Column(name = "administrator_id")
    private long administratorId;

    @Column(name = "garage_address_line1")
    private String addressLine1;

    @Column(name = "garage_address_line2")
    private String addressLine2;


    @Column(name = "garage_city")
    private String city;

    @Column(name = "garage_county")
    private String county;

    @Column(name = "garage_postcode")
    private String postcode;

    @Column(name = "garage_telephone")
    private String telephone;

    @Column(name = "garage_registered_vat")
    private String registeredVat;

    @Column(name = "garage_name")
    private String name;


    public Garage() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAdministratorId() {
        return administratorId;
    }

    public void setAdministratorId(long administratorId) {
        this.administratorId = administratorId;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getRegisteredVat() {
        return registeredVat;
    }

    public void setRegisteredVat(String registeredVat) {
        this.registeredVat = registeredVat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Garage(long administratorId, String addressLine1, String addressLine2, String city, String county, String postcode, String telephone, String registeredVat, String name) {
        this.administratorId = administratorId;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.county = county;
        this.postcode = postcode;
        this.telephone = telephone;
        this.registeredVat = registeredVat;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Garage{" +
                "id=" + id +
                ", administratorId=" + administratorId +
                ", addressLine1='" + addressLine1 + '\'' +
                ", addressLine2='" + addressLine2 + '\'' +
                ", city='" + city + '\'' +
                ", county='" + county + '\'' +
                ", postcode='" + postcode + '\'' +
                ", telephone='" + telephone + '\'' +
                ", registeredVat='" + registeredVat + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
