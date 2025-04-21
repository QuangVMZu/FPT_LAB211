/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Admin
 */
public class CarInformation implements Serializable {

    public String plate;
    public String name;
    public String phone;
    public String brand;
    public double value;
    public Date date;
    public String placeOfRegistration;
    public int numberOfSeat;

    public CarInformation() {
    }

    public CarInformation(String plate, String name, String phone, String brand, double value, Date date, String placeOfRegistration, int numberOfSeat) {
        this.plate = plate;
        this.name = name;
        this.phone = phone;
        this.brand = brand;
        this.value = value;
        this.date = date;
        this.placeOfRegistration = placeOfRegistration;
        this.numberOfSeat = numberOfSeat;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPlaceOfRegistration() {
        return placeOfRegistration;
    }

    public void setPlaceOfRegistration(String placeOfRegistration) {
        this.placeOfRegistration = placeOfRegistration;
    }

    public int getNumberOfSeat() {
        return numberOfSeat;
    }

    public void setNumberOfSeat(int numberOfSeat) {
        this.numberOfSeat = numberOfSeat;
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("$#,###.##");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return "License plate    : " + plate
                + "\nOwner            : " + name
                + "\nPhone            : " + phone
                + "\nCar brand        : " + brand
                + "\nValue of Vehicl  : " + df.format(value)
                + "\nnumber of seat   : " + numberOfSeat
                + "\nregistration date: " + sdf.format(date);
    }
}
