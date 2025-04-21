/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.List;

/**
 *
 * @author ADMIN
 */
public interface I_List {
    boolean create(String code, String name, String phone, String email);
//
    boolean update(Customer customer, String newName, String newPhone, String newEmail);
 //   
    void display();
//    
    List<Object> search(String name);
//    
    boolean writeDataToFile();
}
