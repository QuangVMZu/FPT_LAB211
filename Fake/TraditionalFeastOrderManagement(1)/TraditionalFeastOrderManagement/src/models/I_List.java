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
    boolean create();
//
    boolean update(String code);
 //   
    void display();
//    
    List<Object> search(String name);
//    
    boolean saveToFile();
//
    boolean placeFeastOrder(String code);
}
