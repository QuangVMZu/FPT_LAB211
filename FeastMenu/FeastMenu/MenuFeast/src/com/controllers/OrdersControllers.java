/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controllers;

import com.models.Customer;
import com.models.FeastMenu;
import com.models.Orders;
import java.util.ArrayList;
import com.models.I_Orders;
import com.utils.Utils.Utils;
import com.views.CustomerViews;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hoadoan
 */
public class OrdersControllers extends ArrayList<Orders> implements I_Orders {

    private CustomersControllers customerList = new CustomersControllers();
    private List<FeastMenu> menu = new ArrayList<>();

    @Override
    public List<FeastMenu> listMenu() {

        String filePath = "FeastMenu.csv";  // Đường dẫn file CSV
        List<Object> list = new ArrayList<>();
        List<FeastMenu> menu = new ArrayList<>();

        try {
            list = Utils.readMenu("FeastMenu.csv");
        } catch (IOException ex) {
            Logger.getLogger(OrdersControllers.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (Object ob : list) {
            if (ob instanceof FeastMenu) {
                menu.add((FeastMenu) ob);
            }
        }
        return menu;
    }

    @Override
    public boolean placeOrder(String customerCode, String menuCode, Integer numberOfTables, LocalDate eventDate) {
        String errors = "";

        // Kiểm tra xem mã code đã tồn tại trong danh sách của customer chưa
        customerList = CustomerViews.getCustomerControllers();
        Customer checkCustomerCode = customerList.checkExist(customerCode);

        if (checkCustomerCode.getCode() == null) {
            errors += "Customer does not exist.";
        }

        //Kiểm tra code của menu đã đúng chưa
        menu = listMenu();
        boolean checkMenuCode = false;
        for (FeastMenu i : menu) {
            if (i.getCode().equals(menuCode)) {
                checkMenuCode = true;
                break;
            }
        }
        if (!checkMenuCode) {
            errors += "\nMenu does not exist.";
        }

        if (!errors.isEmpty()) {
            System.out.println(errors);
            return false;
        }

//        this.readOrders();
        if (this.contains(new Orders(0, customerCode, menuCode, numberOfTables, eventDate, 0.0))) {
            System.out.println("Dupplicate data.");
            return false;
        }

        //Tạo id riêng cho order mới
        int id = this.size() + 1;

        FeastMenu tmp = new FeastMenu();
        for (FeastMenu i : menu) {
            if (i.getCode().equals(menuCode)) {
                tmp = i;
                break;
            }
        }
        Double totalCost = tmp.getPrice() * numberOfTables;
        Orders newOrder = new Orders(id, customerCode, menuCode, numberOfTables, eventDate, totalCost);
        this.add(newOrder);
        displayOrder(id, checkCustomerCode, tmp, totalCost);
        return true;
    }

    public void writeDataToFile() {
        List<Object> list = new ArrayList<>();
        for (Orders i : this) {
            list.add((Object) i);
        }
        Utils.writeListObjectToFile("feast_order_service.dat", list);
    }

    public void readOrders() {
        this.clear();
        List<Object> list = new ArrayList<>();
        try {
            list = Utils.readListOjectFromFile("feast_order_service.dat");
        } catch (IOException ex) {
            Logger.getLogger(OrdersControllers.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (Object ob : list) {
            if (ob instanceof Orders) {
                this.add((Orders) ob);
            }
        }
    }

    public void displayOrder(Integer id, Customer customer, FeastMenu menu, Double totalCost) {
        System.out.println("-----------------------------------------------------");
        System.out.println("Customer order information [Order ID : " + id + "]");
        System.out.println(customer.toString());
        System.out.println(menu.toString());
        System.out.println("------------------------------------------------------");
        System.out.println("Total cost    : " + totalCost);
        System.out.println("------------------------------------------------------");

    }

    @Override
    public boolean updateOrder(Integer id, String menuCode, Integer numberOfTables, LocalDate eventDate) {
        menu = listMenu();
        Orders order = null;
        for (Orders i : this) {
            if (i.getCode().equals(id)) {
                order = i;
                break;
            }
        }
        if (order == null) {
            System.out.println("This Order does not exist.");
            return false;
        }

        // Lấy ra menu mới
        FeastMenu tmp = new FeastMenu();
        for (FeastMenu i : menu) {
            if (i.getCode().equals(menuCode)) {
                tmp = i;
                break;
            }
        }

        order.setMenuCode(menuCode);
        Double totalCost = tmp.getPrice() * numberOfTables;
        order.setTotalCost(totalCost);
        order.setEventDate(eventDate);

        return true;
    }

    @Override
    public boolean saveData() {
        try {
            customerList = CustomerViews.getCustomerControllers();
            Utils.writeListObjectToFile("Customer.dat", (List<Object>) (Object) customerList);
            Utils.writeListObjectToFile("feast_order_service.dat", (List<Object>) (Object) this);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean displayCustomerOrders() {
        // In ra màn hình danh sách customer
        customerList = CustomerViews.getCustomerControllers();
        System.out.println("Customer information : ");
        for(int i = 0; i < 74; i++) {
            System.out.print("-");
        }
        System.out.println();
        System.out.println("Code   | Customer Name        | Phone        | Customer email     ");
        for(int i = 0; i < 74; i++) {
            System.out.print("-");
        }
        System.out.println();
        for (Customer i : customerList) {
            System.out.println(i.display());
        }
        for(int i = 0; i < 74; i++) {
            System.out.print("-");
        }
        System.out.println("\n");
        

        
        // In ra màn hình danh sách order
        System.out.println("Order information : ");
        for(int i = 0; i < 100; i++) {
            System.out.print("-");
        }
        System.out.println();
        System.out.println("Code  | Event date   | Customer code   | Menu Set   | Price           | Tables   | Cost ");
        for(int i = 0; i < 100; i++) {
            System.out.print("-");
        }
        System.out.println();
        for (Orders i : this) {
            System.out.println(i.toString());
        }
        for(int i = 0; i < 100; i++) {
            System.out.print("-");
        }
        System.out.println();
                
        return true;
    }
}
