/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author Wang
 */
public interface I_ListOfOrder {

    void placeOrder(OrderFeast order);
//    

    boolean updateInfoOrder(String orderCode);
//    

    void displayOrderList();
//    

    void saveToFileOrder();
//
}