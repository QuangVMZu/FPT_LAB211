/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.views;

import com.controllers.OrdersControllers;
import com.models.FeastMenu;
import com.utils.Utils.Utils;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DELL
 */
public class OrderViews {

    private static List<FeastMenu> listMenu = new ArrayList<>();
    private static OrdersControllers orderControllers = new OrdersControllers();
    
    public static void init() {
        orderControllers.readOrders();
    }

    public static boolean displayMenu() {
        listMenu = orderControllers.listMenu();
        if (listMenu.isEmpty()) {
            System.out.println("Cannot read data from feastMenu.csv. Please check it.");
            return false;
        }
        System.out.println("-----------------------------------------------------");
        System.out.println("LIST OF SET MENU FOR ORDERING PARTY");
        for (FeastMenu i : listMenu) {
            System.out.println(i.toString());
        }
        System.out.println("-----------------------------------------------------");
        return true;
    }

    public static boolean placeOrder() {
        String customerCode = Utils.getString("Input customer's code : ");
        String menuCode = Utils.getString("Input menu's code : ");
        Integer numberOfTables = Utils.getInt("Input number of tables in the event : ", 0, Integer.MAX_VALUE);
        LocalDate eventDate = Utils.getLocalDate("Input event's date [dd-MM-yyyy] : ");
        return orderControllers.placeOrder(customerCode, menuCode, numberOfTables, eventDate);
    }

    public static boolean updateOrder() {
        Integer id = Utils.getInt("Input order id to update : ", Integer.MIN_VALUE, Integer.MAX_VALUE);
        String menuCode = Utils.getString("Input menu's code : ");
        Integer numberOfTables = Utils.getInt("Input number of tables in the event : ", 0, Integer.MAX_VALUE);
        LocalDate eventDate = Utils.getLocalDate("Input event's date [dd-MM-yyyy] : ");
        return orderControllers.updateOrder(id, menuCode, numberOfTables, eventDate);
    }
    
    public static boolean saveData() {
        return orderControllers.saveData();
    }
    
    public static boolean displayCustomerOrder() {
        return orderControllers.displayCustomerOrders();
    }

}
