/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.controllers;

import com.models.Customer;
import com.models.I_Customer;
import com.utils.Utils.Utils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DELL
 */
public class CustomersControllers extends ArrayList<Customer> implements I_Customer {

    @Override
    public boolean register(String code, String name, String phone, String email) {
        boolean result = false;
        try {
            Customer customer = new Customer(code, name, phone, email);
            if(this.contains(customer)) {
                System.out.println("Duplicate customer.");
                return false;
            }
            this.add(customer);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return result;
    }

    @Override
    public boolean update(Customer customer, String newName, String newPhone, String newEmail) {
        boolean result = false;
        try {
            customer.setName(newName);
            customer.setPhone(newPhone);
            customer.setEmail(newEmail);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return result;
    }

    @Override
    public Customer searchByName(String name) {
        Customer cus = new Customer();
        for(Customer i : this) {
            if(i.getName().equals(name)) {
                cus = i;
                break;
            }
        }
        return cus;
    }
    
    public Customer checkExist(String code) {
//        readCustomer();
        Customer cus = new Customer();
        for(Customer i : this) {
            if(i.getCode().equalsIgnoreCase(code)) {
                cus = i;
                break;
            }
        }
        return cus;
    }
    
    public boolean writeDataToFile() {
        List<Object> list = new ArrayList<>();
        for(Customer i : this) {
            list.add((Object) i);
        }
        Utils.writeListObjectToFile("Customer.dat", list);
        return true;
    }
    
    public void readCustomer() {
        this.clear();
        List<Object> list = new ArrayList<>();
        try {
            list = Utils.readListOjectFromFile("Customer.dat");
        } catch (IOException ex) {
            Logger.getLogger(CustomersControllers.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for(Object ob : list) {
            if(ob instanceof Customer) {
                this.add((Customer) ob);
            }
        }   
    }
    
}
