/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import controllers.CustomerManager;
import models.Customer;
import utils.Utils;

/**
 *
 * @author ADMIN
 */
public class CustomerViews {

    private static Customer customer = new Customer();
    private static CustomerManager customerController = new CustomerManager();

    public static CustomerManager getCustomerControllers() {
        return customerController;
    }

    public static void init() {
        customerController.readCustomer();
    }

    public static boolean register() {
        boolean check = false;
        System.out.println("Danh sách khách hàng hiện tại:");
        customerController.displayAll();
        try {
            boolean checkDuplicate;
            boolean continous;
            Customer customer = new Customer();

            // Nhập mã khách hàng
            do {
                String code = Utils.getString("Input customer Code: ");
                if (Utils.isValidCode(code)) {
                    customer.setCustomerCode(code.toUpperCase());
                    checkDuplicate = customerController.checkExist(code) != null;
                    if (checkDuplicate) {
                        System.out.println("Lỗi: Mã khách hàng đã tồn tại.");
                    }
                } else {
                    System.out.println("Lỗi: Mã khách hàng không hợp lệ.");
                    checkDuplicate = true;
                }
            } while (checkDuplicate);

            // Nhập tên khách hàng
            do {
                String name = Utils.getString("Input customer name: ");
                if (name.length() > 1 && name.length() < 24) {
                    customer.setName(name);
                    continous = false;
                } else {
                    System.out.println("Lỗi: Tên khách hàng không hợp lệ.");
                    continous = true;
                }
            } while (continous);

            // Nhập số điện thoại
            do {
                String phone = Utils.getString("Input customer phone: ");
                if (Utils.isValidPhone(phone)) {
                    customer.setPhone(phone);
                    continous = false;
                } else {
                    System.out.println("Lỗi: Số điện thoại không hợp lệ.");
                    continous = true;
                }
            } while (continous);

            // Nhập email
            do {
                String email = Utils.getString("Input customer email: ");
                if (Utils.isValidEmail(email)) {
                    customer.setEmail(email);
                    continous = false;
                } else {
                    System.out.println("Lỗi: Email không hợp lệ.");
                    continous = true;
                }
            } while (continous);

            // Đăng ký khách hàng vào hệ thống
            check = customerController.create(customer.getCustomerCode(), customer.getName(), customer.getPhone(), customer.getEmail());
            if (check) {
                System.out.println("Khách hàng đã được đăng ký thành công.");
            } else {
                System.out.println("Lỗi: Đăng ký khách hàng thất bại.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }

    public boolean update() {
        boolean check = false;
        try {
            
            customerController.readCustomer();
            // Nhập mã khách hàng cần cập nhật
            String code = Utils.getString("Nhập mã khách hàng cần cập nhật: ").toUpperCase();

            // Tìm kiếm khách hàng theo mã code
            Customer customer = customerController.checkExist(code);

            if (customer == null) {
                System.out.println("Lỗi: Không tìm thấy khách hàng với mã " + code);
                return false;
            }

            // Hiển thị thông tin khách hàng hiện tại
            System.out.println("Khách hàng tìm thấy:");
            System.out.println("-----------------------------------------------------------------------------------");
            System.out.println("Customer Code | Name                 | Phone        | Email                        ");
            System.out.println("-----------------------------------------------------------------------------------");
            System.out.printf("%-14s | %-20s | %-12s | %-30s%n",
                    customer.getCustomerCode(), customer.getName(), customer.getPhone(), customer.getEmail());
            System.out.println("-----------------------------------------------------------------------------------");

            // Nhập và cập nhật tên khách hàng
            String newName = Utils.updateString("Nhập tên mới (Nhấn Enter để giữ nguyên): ", customer.getName());
            if (!newName.isEmpty()) {
                customer.setName(newName);
            }

            // Nhập và cập nhật số điện thoại
            String newPhone;
            do {
                newPhone = Utils.updateString("Nhập số điện thoại mới (Nhấn Enter để giữ nguyên): ", customer.getPhone());
                if (newPhone.isEmpty() || Utils.isValidPhone(newPhone)) {
                    if (!newPhone.isEmpty()) {
                        customer.setPhone(newPhone);
                    }
                    break;
                }
                System.out.println("Lỗi: Số điện thoại không hợp lệ.");
            } while (true);

            // Nhập và cập nhật email
            String newEmail;
            do {
                newEmail = Utils.updateString("Nhập email mới (Nhấn Enter để giữ nguyên): ", customer.getEmail());
                if (newEmail.isEmpty() || Utils.isValidEmail(newEmail)) {
                    if (!newEmail.isEmpty()) {
                        customer.setEmail(newEmail);
                    }
                    break;
                }
                System.out.println("Lỗi: Email không hợp lệ.");
            } while (true);

            // Gọi hàm cập nhật từ CustomerManager
            check = customerController.update(customer, newName, newPhone, newEmail);

            if (check) {
                System.out.println("Cập nhật thông tin khách hàng thành công.");
            } else {
                System.out.println("Lỗi: Cập nhật khách hàng thất bại.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }
//    

    public static boolean searchByName() {
        String name = Utils.getString("Input customer's for searching : ");
        customer = (Customer) customerController.search(name);
        if (customer.getCustomerCode() == null) {
            return false;
        } else {
            System.out.println(customer.toString());
            System.out.println("-----------------------------------------------------");
            return true;
        }
    }

}
