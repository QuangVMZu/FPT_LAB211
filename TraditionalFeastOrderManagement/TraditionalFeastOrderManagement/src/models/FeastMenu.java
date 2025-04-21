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
public class FeastMenu implements Serializable{

    private String codeMenu, nameParty, ingredients;
    private double price;

    public FeastMenu() {
    }

    public FeastMenu(String codeMenu, String nameParty, double price, String ingredients) {
        this.codeMenu = codeMenu;
        this.nameParty = nameParty;
        this.ingredients = ingredients;
        this.price = price;
    }

    public String getCodeMenu() {
        return codeMenu;
    }

    public void setCodeMenu(String codeMenu) {
        this.codeMenu = codeMenu;
    }

    public String getNameParty() {
        return nameParty;
    }

    public void setNameParty(String nameParty) {
        this.nameParty = nameParty;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Code    : " + codeMenu + "\n"
             + "Name    : " + nameParty + "\n"
             + "Price   : " + String.format("%,.0f", price) + " Vnd\n"
             + "Ingredients:\n" + ingredients + "\n";
    }

}
