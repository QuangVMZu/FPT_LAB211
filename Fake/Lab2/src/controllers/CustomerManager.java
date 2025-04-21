/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Customer;
import models.Feast;
import models.I_List;
import utils.Utils;

/**
 *
 * @author ADMIN
 */
public class CustomerManager extends ArrayList<Customer> implements I_List {

    @Override
    public boolean create(String code, String name, String phone, String email) {
        boolean result = false;
        try {
            Customer customer = new Customer(code, name, phone, email);
            if (this.contains(customer)) {
                System.out.println("Duplicate customer.");
                return false;
            }
            this.add(customer);
            // ✅ Gọi phương thức lưu vào file ngay sau khi thêm khách hàng
            if (writeDataToFile()) {
                System.out.println("Khách hàng đã được lưu vào file.");
            } else {
                System.out.println("Lỗi: Không thể lưu khách hàng vào file.");
            }

            result = true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return result;
    }

    @Override
    public boolean update(Customer customer, String newName, String newPhone, String newEmail) {
        boolean result = false;
        try {
            customer.setName(newName);
            customer.setPhone(newPhone);
            customer.setEmail(newEmail);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();

        }

        return result;
    }

    @Override
    public void display() {
        try {
            ArrayList<Object> listSetCode = Utils.readObjectFromFile("FeastMenu.csv"); // Đọc dữ liệu từ file CSV
            if (listSetCode.isEmpty()) {
                System.out.println("No data found in FeastMenu.csv!");
                return;
            }
            // Ép kiểu về danh sách Feast
            ArrayList<Feast> feastList = new ArrayList<>();
            for (Object obj : listSetCode) {
                if (obj instanceof Feast) {
                    feastList.add((Feast) obj);
                }
            }

            // Sắp xếp danh sách bằng Bubble Sort
            int n = feastList.size();
            for (int i = 0; i < n - 1; i++) {
                for (int j = 0; j < n - i - 1; j++) {
                    int price1 = Integer.parseInt(feastList.get(j).getPrice());
                    int price2 = Integer.parseInt(feastList.get(j + 1).getPrice());
                    if (price1 > price2) { // Nếu giá lớn hơn thì hoán đổi vị trí
                        Feast temp = feastList.get(j);
                        feastList.set(j, feastList.get(j + 1));
                        feastList.set(j + 1, temp);
                    }
                }
            }

            System.out.println("--------------------------------------------------------------");
            System.out.println("List of Set Menus for ordering party: ");
            System.out.println("--------------------------------------------------------------");

            for (Feast feast : feastList) {
//                if (obj instanceof Feast) {
//                    Feast feast = (Feast) obj;
//                    System.out.printf("| %-10s | %-18s | %-7s | %-15s |\n",
//                            feast.getCodeOfSetMenu(),
//                            feast.getName(),
//                            feast.getPrice(),
//                            feast.getDescription());
                System.out.println(feast.toStringOfMenu());
                String ingredients = feast.getIngrediends();
                String[] ingredientLines = ingredients.split("#\\+");

                System.out.println("Ingredients:");
                for (String line : ingredientLines) {
                    if (!line.trim().isEmpty()) { // Bỏ qua dòng trống
                        System.out.println("+ " + line.trim());
                    }
                }
                System.out.println("--------------------------------------------------------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Object> search(String name) {
        List<Object> newList = new ArrayList<>();
        for (Customer c : this) {
            if (c.getName().toLowerCase().contains(name.toLowerCase())) {
                newList.add(c);
            }
        }
        return newList;
    }

    @Override
    public boolean writeDataToFile() {
        List<Object> list = new ArrayList<>();
        for (Customer i : this) {
            list.add((Object) i);
        }
        Utils.writeListObjectToFile("Customer.dat", list);
        return true;
    }

    public Customer checkExist(String code) {
//        readCustomer();
        for (Customer i : this) {
            if (i.getCustomerCode().equalsIgnoreCase(code)) {
                return i;  // Trả về khách hàng nếu tìm thấy
            }
        }
        return null;  //
    }

    public void readCustomer() {
        List<Object> list = new ArrayList<>();
        try {
            list = Utils.readListOjectFromFileDat("Customer.dat");
        } catch (Exception e) {

        }

        for (Object ob : list) {
            if (ob instanceof Customer) {
                this.add((Customer) ob);
            }
        }
    }

    public void displayAll() {
        if (this.isEmpty()) {
            System.out.println("Danh sách khách hàng trống.");
            return;
        }

        System.out.println("Danh sách khách hàng:");
        System.out.println("-----------------------------------------------------------------------------------");
        System.out.println("Customer Code | Name                 | Phone        | Email                        ");
        System.out.println("-----------------------------------------------------------------------------------");

        for (Customer c : this) {
            System.out.printf("%-14s | %-20s | %-12s | %-30s%n",
                    c.getCustomerCode(), c.getName(), c.getPhone(), c.getEmail());
        }
        System.out.println("-----------------------------------------------------------------------------------");
    }
}
