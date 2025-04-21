/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.views;

import com.controllers.CustomersControllers;
import com.models.Customer;
import com.utils.Utils.Utils;

/**
 *
 * @author DELL
 */
public class CustomerViews {

    private static Customer customer = new Customer();
    private static CustomersControllers customerCOntroller = new CustomersControllers();
    
    public static void init() {
        customerCOntroller.readCustomer();
    }

    public static boolean register() {
        String code = Utils.checkValidString("Input customer's code : ", "code");
        String name = Utils.checkValidString("Input customer's name : ", "name");
        String phone = Utils.checkValidString("Input customer's phone : ", "phone");
        String email = Utils.checkValidString("Input customer's email : ", "email");
        code = code.toUpperCase();

        return customerCOntroller.register(code, name, phone, email);
    }

    public static boolean update() {
        String code = Utils.getString("Input customer code : ");
        customer = customerCOntroller.checkExist(code);
        if (customer.getCode() == null) {
            return false;
        } else {
            String name = Utils.updateValidString("Input new customer's name : ", "name", customer.getName());
            String phone = Utils.updateValidString("Input new customer's phone : ", "phone", customer.getPhone());
            String email = Utils.updateValidString("Input new customer's email : ", "email", customer.getEmail());
            return customerCOntroller.update(customer, name, phone, email);
        }
    }
    
    public static boolean searchByName() {
        String name = Utils.getString("Input customer's for searching : ");
        customer = customerCOntroller.searchByName(name);
        if(customer.getCode() == null)
            return false;
        else {
            System.out.println(customer.toString());
            System.out.println("-----------------------------------------------------");
            return true;
        }
    }
    
    public static CustomersControllers getCustomerControllers() {
        return customerCOntroller;
    }
}
