/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;

/**
 *
 * @author ADMIN
 */
public class Feast implements Serializable {

    private String codeOfSetMenu;
    private String name;
    private String price;
    private String ingrediends;

    public Feast() {
    }

    public Feast(String codeOfSetMenu) {
        this.codeOfSetMenu = codeOfSetMenu;
    }

    public boolean equals(Object obj) {
        Feast feast = (Feast) obj; // Ép kiểu an toàn
        return this.codeOfSetMenu != null && this.codeOfSetMenu.equalsIgnoreCase(feast.codeOfSetMenu);
    }

    public Feast(String codeOfSetMenu, String name, String price, String ingrediends) {
        this.codeOfSetMenu = codeOfSetMenu;
        this.name = name;
        this.price = price;
        this.ingrediends = ingrediends;
    }

    public Feast(String codeOfSetMenu, String price) {
        this.codeOfSetMenu = codeOfSetMenu;
        this.price = price;
    }

    public String getIngrediends() {
        return ingrediends;
    }

    public void setIngrediends(String ingrediends) {
        this.ingrediends = ingrediends;
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
        return name;
    }

    public void setDate(String name) {
        this.name = name;
    }

    public String getDescription() {
        return ingrediends;
    }

    public void setDescription(String ingrediends) {
        this.ingrediends = ingrediends;
    }
    
    public String toStringOfMenu() {
        return String.format("Code       :%s\nName       :%s\nPrice      :%,d Vnd",
                this.codeOfSetMenu,
                this.name,
                Integer.parseInt(this.price)
                );
    }
}
