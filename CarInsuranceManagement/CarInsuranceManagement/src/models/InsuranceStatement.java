/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Admin
 */
public class InsuranceStatement implements Serializable {
    
    public String no;
    public String insuranceID;
    public String plate;
    public Date establishedDate;
    public int insurancePeriod;
    public double value;
    public double fees;
    public String nameInsurance;

    public InsuranceStatement() {
    }

    public InsuranceStatement(String no, String insuranceID, String plate, Date establishedDate, int insurancePeriod, double value, double fees, String nameInsurance) {
        this.no = no;
        this.insuranceID = insuranceID;
        this.plate = plate;
        this.establishedDate = establishedDate;
        this.insurancePeriod = insurancePeriod;
        this.value = value;
        this.fees = fees;
        this.nameInsurance = nameInsurance;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getInsuranceID() {
        return insuranceID;
    }

    public void setInsuranceID(String insuranceID) {
        this.insuranceID = insuranceID;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public Date getEstablishedDate() {
        return establishedDate;
    }

    public void setEstablishedDate(Date establishedDate) {
        this.establishedDate = establishedDate;
    }

    public int getInsurancePeriod() {
        return insurancePeriod;
    }

    public void setInsurancePeriod(int insurancePeriod) {
        this.insurancePeriod = insurancePeriod;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getFees() {
        return fees;
    }

    public void setFees(double fees) {
        this.fees = fees;
    }

    public String getNameInsurance() {
        return nameInsurance;
    }

    public void setNameInsurance(String nameInsurance) {
        this.nameInsurance = nameInsurance;
    }

    @Override
    public String toString() {
        return "InsuranceStatement{" + "no=" + no + ", insuranceID=" + insuranceID + ", plate=" + plate + ", establishedDate=" + establishedDate + ", insurancePeriod=" + insurancePeriod + ", value=" + value + ", fees=" + fees + ", nameInsurance=" + nameInsurance + '}';
    }

}