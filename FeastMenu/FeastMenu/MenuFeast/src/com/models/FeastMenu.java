/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.models;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author DELL
 */
public class FeastMenu implements Serializable {

    private static final long serialVersionUID = -5818198711734003206L; // Đảm bảo phiên bản serial
    private String code;
    private String name;
    private Double price;
    private String ingredients;

    public FeastMenu() {
    }

    public FeastMenu(String code, String name, Double price, String ingredients) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.ingredients = ingredients;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getIngredients() {
        String[] tmp = ingredients.split("#\\+");
        StringBuilder ans = new StringBuilder();

        for (String line : tmp) {
            line = line.trim(); // Loại bỏ khoảng trắng dư thừa

            if (!line.isEmpty()) {
                line = line.replaceAll("[\"+]", "").trim(); // Xóa dấu " và dấu +

                ans.append("+ " + line).append("\n");
            }
        }
        return ans.toString().trim(); // Xóa ký tự xuống dòng cuối cùng nếu có
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public String toString() {
        return "-----------------------------------------------------\n"
                + "Code        : " + this.getCode() + "\n"
                + "Name        : " + this.getName() + "\n"
                + "Price       : " + this.getPrice() + "\n"
                + "Ingredients : " + "\n" + this.getIngredients();
    }

}
