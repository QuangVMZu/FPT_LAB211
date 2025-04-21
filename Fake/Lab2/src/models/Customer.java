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
public class Customer implements Serializable {

    private String customerCode;
    private String name;
    private String phone;
    private String email;

    public Customer() {
    }

    @Override
    public boolean equals(Object obj) {
        Customer i = (Customer) obj;
        return this.getCustomerCode().equals(i.getCustomerCode());
    }

    public Customer(String customerCode) {
        this.customerCode = customerCode;
    }

    public Customer(String customerCode, String name, String phone, String email) {
        this.customerCode = customerCode;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void getCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getName() {
        return name;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
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
        return "Customer{" + "customerCode=" + customerCode + ", name=" + name + ", phone=" + phone + ", email=" + email + '}';
    }

}
