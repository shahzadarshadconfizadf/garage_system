package com.garagesystem.model;

import javax.persistence.*;

@Entity
@Table(name = "vehicle")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vehicle_sequence")
    @SequenceGenerator(name = "vehicle_sequence", sequenceName = "vehicle_sequence", allocationSize = 2)
    @Column(name = "vehicle_id")
    private long id;

    @Column(name = "vehicle_registration")
    private String registration;

    @Column(name = "vehicle_chassis_number")
    private String chassisNumber;

    @Column(name = "vehicle_make")
    private String vehicleMake;

    @Column(name = "vehicle_model")
    private String vehicleModel;

    @Column(name = "vehicle_colour")
    private String vehicleColour;

    @Column(name = "vehicle_transmission")
    private String transmission;

    @Column(name = "vehicle_fuel_type")
    private String fuelType;

    @Column(name = "vehicle_year")
    private long manufactureYear;

    @Column(name = "vehicle_mileage")
    private long mileage;

    public Vehicle() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public String getChassisNumber() {
        return chassisNumber;
    }

    public void setChassisNumber(String chassisNumber) {
        this.chassisNumber = chassisNumber;
    }

    public String getVehicleMake() {
        return vehicleMake;
    }

    public void setVehicleMake(String vehicleMake) {
        this.vehicleMake = vehicleMake;
    }

    public String getVehicleColour() {
        return vehicleColour;
    }

    public void setVehicleColour(String vehicleColour) {
        this.vehicleColour = vehicleColour;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public long getManufactureYear() {
        return manufactureYear;
    }

    public void setManufactureYear(long manufactureYear) {
        this.manufactureYear = manufactureYear;
    }

    public long getMileage() {
        return mileage;
    }

    public void setMileage(long mileage) {
        this.mileage = mileage;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }


    public Vehicle(String registration, String chassisNumber, String vehicleMake, String vehicleModel, String vehicleColour, String transmission, String fuelType, long manufactureYear, long mileage) {
        this.registration = registration;
        this.chassisNumber = chassisNumber;
        this.vehicleMake = vehicleMake;
        this.vehicleModel = vehicleModel;
        this.vehicleColour = vehicleColour;
        this.transmission = transmission;
        this.fuelType = fuelType;
        this.manufactureYear = manufactureYear;
        this.mileage = mileage;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", registration='" + registration + '\'' +
                ", chassisNumber='" + chassisNumber + '\'' +
                ", vehicleMake='" + vehicleMake + '\'' +
                ", vehicleModel='" + vehicleModel + '\'' +
                ", vehicleColour='" + vehicleColour + '\'' +
                ", transmission='" + transmission + '\'' +
                ", fuelType='" + fuelType + '\'' +
                ", manufactureYear=" + manufactureYear +
                ", mileage=" + mileage +
                '}';
    }
}
