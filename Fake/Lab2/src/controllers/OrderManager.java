/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Customer;
import models.Feast;
import models.FeastCustomer;
import models.I_ListOfOrder;
import utils.Utils;

/**
 *
 * @author ADMIN
 */
public class OrderManager extends ArrayList<FeastCustomer> implements I_ListOfOrder {

    private CustomerManager customerList = new CustomerManager();
    private List<Feast> menu = new ArrayList<>();

    public OrderManager() {
        super();
    }

    public List<Feast> listMenu() {
        List<Object> list = new ArrayList<>();
        List<Feast> menu = new ArrayList<>();
        try {
            list = Utils.readObjectFromFile("FeastMenu.csv");
        } catch (Exception ex) {
            System.out.println("Lỗi khi đọc tệp: " + ex.getMessage());
        }

        for (Object ob : list) {
            if (ob instanceof Feast) {
                menu.add((Feast) ob);
            }
        }
        return menu;
    }

    @Override
    public boolean createOfOrder(String customerCode, String menuCode, int numberOfTables, Date eventDate) {
        // Kiểm tra khách hàng có tồn tại không
        Customer customer = customerList.checkExist(customerCode);
        if (customer == null) {
            System.out.println("Lỗi: Khách hàng không tồn tại.");
            return false;
        }

        // Kiểm tra code của menu đã đúng chưa
        menu = listMenu();
        Feast selectedMenu = null;
        for (Feast i : menu) {
            if (i.getCodeOfSetMenu().equals(menuCode)) {
                selectedMenu = i;
                break;
            }
        }
        if (selectedMenu == null) {
            System.out.println("Lỗi: Mã thực đơn không hợp lệ.");
            return false;
        }

        // Kiểm tra trùng lặp đơn hàng
        for (FeastCustomer order : this) {
            if (order.getCustomerCode().equalsIgnoreCase(customerCode)
                    && order.getCodeOfSetMenu().equalsIgnoreCase(menuCode)
                    && order.getDate().equals(eventDate)) {
                System.out.println("Lỗi: Đơn hàng đã tồn tại.");
                return false;
            }
        }

        // Tạo id riêng cho order mới
        String orderID = generateOrderID();
        double totalCost = Integer.parseInt(selectedMenu.getPrice()) * numberOfTables;
        FeastCustomer newOrder = new FeastCustomer(orderID, customerCode, menuCode, numberOfTables, eventDate, totalCost);
        this.add(newOrder);
        displayOrder(orderID, customer, selectedMenu, totalCost);
        return true;
    }

    private String generateOrderID() {
        return "ORD" + (this.size() + 1);
    }

    @Override
    public boolean updateOrder(String id, String menuCode, Integer numberOfTables, Date eventDate) {
        menu = listMenu();
        FeastCustomer order = null;
        for (FeastCustomer i : this) {
            if (i.getCodeID().equals(id)) {
                order = i;
                break;
            }
        }
        if (order == null) {
            System.out.println("This Order does not exist.");
            return false;
        }

        // Lấy ra menu mới
        Feast tmp = new Feast();
        for (Feast i : menu) {
            if (i.getCodeOfSetMenu().equals(menuCode)) {
                tmp = i;
                break;
            }
        }

        order.setCodeOfSetMenu(menuCode);
        double totalCost = Integer.parseInt(tmp.getPrice()) * numberOfTables;
        order.setTotalCost(totalCost);
        order.setDate(eventDate);

        return true;
    }

    public void displayOrder(String id, Customer customer, Feast menu, double totalCost) {
        System.out.println("-----------------------------------------------------");
        System.out.println("Customer order information [Order ID : " + id + "]");
        System.out.println(customer.toString());
        System.out.println(menu.toString());
        System.out.println("------------------------------------------------------");
        System.out.println("Total cost    : " + totalCost);
        System.out.println("------------------------------------------------------");
    }

    public void writeDataToFile() {
        List<Object> list = new ArrayList<>();
        for (FeastCustomer i : this) {
            list.add((Object) i);
        }
        Utils.writeListObjectToFile("feast_order_service.dat", list);
    }

    public void readOrders() {
        this.clear();
        List<Object> list = new ArrayList<>();
        try {
            list = Utils.readListOjectFromFileDat("feast_order_service.dat");
        } catch (Exception ex) {
        }

        for (Object ob : list) {
            if (ob instanceof FeastCustomer) {
                this.add((FeastCustomer) ob);
            }
        }
    }
}
