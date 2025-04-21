/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.models;

import java.io.Serializable;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

/**
 *
 * @author DELL
 */
public class Orders implements Serializable{
    private static final long serialVersionUID = -2558300858320830379L;
    private Integer code;
    private String customerCode;
    private String menuCode;
    private Integer numberOfTables;
    private LocalDate eventDate;
    private Double totalCost;

    public Orders() {
    }

    public Orders(Integer code, String customerCode, String menuCode, Integer numberOfTables, LocalDate eventDate, Double totalCost) {
        this.code = code;
        this.customerCode = customerCode;
        this.menuCode = menuCode;
        this.numberOfTables = numberOfTables;
        this.eventDate = eventDate;
        this.totalCost = totalCost;
    }

    @Override
    public boolean equals(Object obj) {
        Orders order = (Orders) obj;
        return (this.getCustomerCode().equals(order.getCustomerCode())) && 
                (this.getMenuCode().equals(order.getMenuCode())) && 
                (this.getEventDate().equals(order.getEventDate()));
    }

    @Override
    public String toString() {
        return String.format("%-5s | %-12s | %-15s | %-10s | %-15s | %-8s | %-15s", 
                this.getCode(), this.getEventDate(), this.getCustomerCode(),
                this.getMenuCode(), formatNumber(calPrice()), this.getNumberOfTables(), formatNumber(this.getTotalCost()));
    }
    
    

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }
    
    

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    public Integer getNumberOfTables() {
        return numberOfTables;
    }

    public void setNumberOfTables(Integer numberOfTables) {
        this.numberOfTables = numberOfTables;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }
    
    public Double calPrice() {
        return totalCost/numberOfTables;
    }
    
    public String formatNumber(double number) {
        NumberFormat formatter = NumberFormat.getInstance(Locale.US);
        return formatter.format(number);
    }
    
    
}
