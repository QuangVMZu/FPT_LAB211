/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.models;

/**
 *
 * @author hoadoan
 */
public interface I_Customer {
    boolean register(String code, String name, String phone, String email);
    boolean update(Customer customer, String newName, String newPhone, String newEmail);
    Customer searchByName(String name);
}
