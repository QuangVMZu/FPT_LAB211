/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.models;

import java.io.Serializable;

/**
 *
 * @author Wang
 */
public class FeastOrder implements Serializable {

    private String codeOfSetMenu;
    private String name;
    private String price;
    private String date;
    private int numberOfTable;
    private String description;

    public FeastOrder(String codeOfSetMenu) {
        this.codeOfSetMenu = codeOfSetMenu;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        FeastOrder order = (FeastOrder) obj;
        return this.getCodeOfSetMenu().equals(order.getCodeOfSetMenu());
    }

    public FeastOrder() {
    }

    public FeastOrder(String codeOfSetMenu, String name, String price, String date, int numberOfTable, String description) {
        this.codeOfSetMenu = codeOfSetMenu;
        this.name = name;
        this.price = price;
        this.date = date;
        this.numberOfTable = numberOfTable;
        this.description = description;
    }

    public String getCodeOfSetMenu() {
        return codeOfSetMenu;
    }

    public void setCodeOfSetMenu(String codeOfSetMenu) {
        this.codeOfSetMenu = codeOfSetMenu;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getNumberOfTable() {
        return numberOfTable;
    }

    public void setNumberOfTable(int numberOfTable) {
        this.numberOfTable = numberOfTable;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
