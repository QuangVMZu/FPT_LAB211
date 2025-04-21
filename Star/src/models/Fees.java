/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author ADMIN
 */
public class Fees {

    private String className;
    private int grand;
    private double fee;

    public Fees() {
    }

    public Fees(String className, int grand, double fee) {
        this.className = className;
        this.grand = grand;
        this.fee = fee;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getGrand() {
        return grand;
    }

    public void setGrand(int grand) {
        this.grand = grand;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }
    
    @Override
    public String toString() {
        return "Fees{" + "className=" + className + ", grand=" + grand + ", fee=" + fee + '}';
    }

}
