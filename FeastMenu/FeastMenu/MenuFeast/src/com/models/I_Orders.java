/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.models;

import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author DELL
 */
public interface I_Orders {
    List<FeastMenu> listMenu();
    boolean placeOrder(String customerCode, String menuCode, Integer numberOfTables, LocalDate eventDate);
    boolean updateOrder(Integer id, String menuCode, Integer numberOfTables, LocalDate eventDate);
    boolean saveData();
    boolean displayCustomerOrders();
}
