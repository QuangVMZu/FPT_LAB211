/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.models;

import com.utils.Acceptable.CustomerAcceptable;
import com.utils.Utils.Utils;
import java.io.Serializable;

/**
 *
 * @author DELL
 */
public class Customer implements Serializable{
    private static final long serialVersionUID = 5305293927933091843L;
    private String code;
    private String name;
    private String phone;
    private String email;

    public Customer() {
    }

    public Customer(String code, String name, String phone, String email) {
        this.code = code;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    @Override
    public boolean equals(Object obj) {
        Customer i = (Customer) obj;
        return this.getCode().equals(i.getCode());
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "-----------------------------------------------------" + "\n" +
                "Customer's code         : " + this.getCode() + "\n" + 
                "Customer's name         : " + this.getName() + "\n" +
                "Customer's phone number : " + this.getPhone() + "\n" + 
                "Customer's email        : " + this.getEmail();
    }
    
    public String getNameToDisPlay() {
        String[] tmp = name.split(" ");
        String ans = tmp[tmp.length - 1] + ", ";
        for(int i = 0; i < tmp.length - 1; i++) {
            ans += tmp[i] + " ";
        }
        return ans;
    }
    
    public String display() {
        return String.format("%-6s | %-20s | %-12s | %-20s", this.getCode(), getNameToDisPlay(), this.getPhone(), this.getEmail());
    }
    
    
}
