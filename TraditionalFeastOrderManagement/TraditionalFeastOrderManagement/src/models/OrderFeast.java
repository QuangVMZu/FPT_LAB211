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
 * @author ADMIN
 */
public class OrderFeast implements Serializable {

    private String menuCode;
    private String customeCode;
    private String orderCode;
    private String numTable;
    private double totalCost;
    private Date eventDate;
    private double price;

    public OrderFeast() {
    }

    public OrderFeast(String orderCode, String customeCode, String menuCode, String numTable, double totalCost, Date eventDate, double price) {
        this.orderCode = orderCode;
        this.customeCode = customeCode;
        this.menuCode = menuCode;
        this.numTable = numTable;
        this.totalCost = totalCost;
        this.eventDate = eventDate;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getCustomeCode() {
        return customeCode;
    }

    public void setCustomeCode(String customeCode) {
        this.customeCode = customeCode;
    }

    public String getNumTable() {
        return numTable;
    }

    public void setNumTable(String numTable) {
        this.numTable = numTable;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        DecimalFormat df = new DecimalFormat("#,### VND");

        return String.format("| %-5s | %-10s | %-11s | %-8s | %-10s | %-6s | %-16s |",
                getOrderCode(),
                (getEventDate() != null) ? sdf.format(getEventDate()) : "N/A",
                getCustomeCode().toUpperCase(),
                getMenuCode().toUpperCase(),
                df.format(getPrice()),
                getNumTable(),
                df.format(getTotalCost()));
    }
}
