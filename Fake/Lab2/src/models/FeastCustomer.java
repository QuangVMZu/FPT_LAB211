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
 * @author ADMIN
 */
public class FeastCustomer implements Serializable {

    private Double totalCost;
    private String codeID;
    private String customerCode;
    private String codeOfSetMenu;
    private int numOfTable;
    private Date date;

    public FeastCustomer(String codeID, String customerCode, String codeOfSetMenu, int numOfTable, Date date) {
        this.codeID = codeID;
        this.customerCode = customerCode;
        this.codeOfSetMenu = codeOfSetMenu;
        this.numOfTable = numOfTable;
        this.date = date;
    }

    public FeastCustomer(String codeID, String customerCode, String codeOfSetMenu, int numOfTable, Date date, Double totalCost) {
        this.totalCost = totalCost;
        this.codeID = codeID;
        this.customerCode = customerCode;
        this.codeOfSetMenu = codeOfSetMenu;
        this.numOfTable = numOfTable;
        this.date = date;
    }

    public FeastCustomer() {
    }

    @Override
    public boolean equals(Object obj) {
        FeastCustomer order = (FeastCustomer) obj;
        return (this.getCustomerCode().equals(order.getCustomerCode())) && 
                (this.getCodeOfSetMenu().equals(order.getCodeOfSetMenu())) && 
                (this.getDate().equals(order.getDate()));
    }

    // Constructor thêm Customer
    public FeastCustomer(String customerCode, String codeOfSetMenu, int numOfTable, Date date) {
        this.customerCode = customerCode;
        this.codeOfSetMenu = codeOfSetMenu;
        this.numOfTable = numOfTable;
        this.date = date;
    }

    // Getter và Setter cho customer
    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    public String getCodeID() {
        return codeID;
    }

    public void setCodeID(String codeID) {
        this.codeID = codeID;
    }

    public String getCodeOfSetMenu() {
        return codeOfSetMenu;
    }

    public void setCodeOfSetMenu(String codeOfSetMenu) {
        this.codeOfSetMenu = codeOfSetMenu;
    }

    public int getNumOfTable() {
        return numOfTable;
    }

    public void setNumOfTable(int numOfTable) {
        this.numOfTable = numOfTable;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    

}
