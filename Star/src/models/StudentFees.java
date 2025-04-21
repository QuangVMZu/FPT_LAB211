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
public class StudentFees {
    private String code;
    private String className;
    private String season;
    private double price;

    public StudentFees() {
    }

    public StudentFees(String code, String className, String season, double price) {
        this.code = code;
        this.className = className;
        this.season = season;
        this.price = price;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "StudentFees{" + "code=" + code + ", className=" + className + ", season=" + season + ", price=" + price + '}';
    }
    
}
