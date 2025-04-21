/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.List;

/**
 *
 * @author Wang
 */
public interface I_List {

    void addNew(Customer x);
//

    boolean updateInfoCutomer(String code);
//

    void displayAll(List<Customer> cs);
//    

    void searchByName(String name);
//    

    void readFromFile();
//

    void saveToFile();
//
}