/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import controllers.CustomerManager;
import controllers.Menu;
import controllers.OrderManager;
import java.util.List;
import models.Customer;
import models.I_List;
import models.I_Menu;
import utils.Utils;

/**
 *
 * @author ADMIN
 */
public class TraditionalFeastOrderManagement {

    public static void main(String[] args) {
        I_Menu menu = new Menu();
        menu.addItem("1. Add new customer.");
        menu.addItem("2. Update customer information.");
        menu.addItem("3. Display customer list.");
        menu.addItem("4. Search customers by name.");
        menu.addItem("5. Place a feast order.");
        menu.addItem("6. Filter data by location.");
        menu.addItem("7. Statistics of registration numbers by location.");
        menu.addItem("8. Save data to file.");
        menu.addItem("9. Exit program.");

        int choice;
        boolean cont = true;
        I_List list = new CustomerManager();

        do {
            menu.showMenu();
            choice = menu.getChoice();
            CustomerManager customerManager = new CustomerManager(); // Danh sách khách hàng
            OrderManager orderManager = new OrderManager(); // Quản lý đơn hàng
            switch (choice) {
                case 1:  // Thêm khách hàng
                    if (CustomerViews.register()) {
                        System.out.println("Khách hàng đã được thêm thành công!");
                    } else {
                        System.out.println("Lỗi: Không thể thêm khách hàng.");
                    }
                    break;

                case 2:  // Cập nhật thông tin khách hàng
                    System.out.println("Cập nhật thông tin khách hàng");
                    String updateCode = Utils.getString("Nhập mã khách hàng cần cập nhật: ").toUpperCase();

                    Customer customerToUpdate = customerManager.checkExist(updateCode);
                    if (customerToUpdate == null) {
                        System.out.println("Lỗi: Không tìm thấy khách hàng với mã " + updateCode);
                    } else {
                        String newName = Utils.updateString("Nhập tên mới (Enter để giữ nguyên): ", customerToUpdate.getName());
                        String newPhone;
                        do {
                            newPhone = Utils.updateString("Nhập số điện thoại mới (Enter để giữ nguyên): ", customerToUpdate.getPhone());
                            if (newPhone.isEmpty() || Utils.isValidPhone(newPhone)) {
                                break;
                            }
                            System.out.println("Lỗi: Số điện thoại không hợp lệ.");
                        } while (true);

                        String newEmail;
                        do {
                            newEmail = Utils.updateString("Nhập email mới (Enter để giữ nguyên): ", customerToUpdate.getEmail());
                            if (newEmail.isEmpty() || Utils.isValidEmail(newEmail)) {
                                break;
                            }
                            System.out.println("Lỗi: Email không hợp lệ.");
                        } while (true);

                        if (customerManager.update(customerToUpdate, newName, newPhone, newEmail)) {
                            System.out.println("Cập nhật thông tin khách hàng thành công.");
                        } else {
                            System.out.println("Lỗi: Không thể cập nhật khách hàng.");
                        }
                    }
                    break;

                case 3:  // Hiển thị danh sách khách hàng
                    System.out.println("Danh sách khách hàng:");
                    customerManager.displayAll();
                    break;

                case 4:  // Tìm kiếm khách hàng theo tên
                    String searchName = Utils.getString("Nhập tên khách hàng cần tìm: ").toLowerCase();
                    List<Object> foundCustomers = customerManager.search(searchName);

                    if (foundCustomers.isEmpty()) {
                        System.out.println("Không tìm thấy khách hàng nào với tên: " + searchName);
                    } else {
                        System.out.println("Danh sách khách hàng tìm thấy:");
                        System.out.println("-----------------------------------------------------------------------------------");
                        System.out.println("Customer Code | Name                 | Phone        | Email                        ");
                        System.out.println("-----------------------------------------------------------------------------------");
                        for (Object obj : foundCustomers) {
                            if (obj instanceof Customer) {
                                Customer c = (Customer) obj;
                                System.out.printf("%-14s | %-20s | %-12s | %-30s%n",
                                        c.getCustomerCode(), c.getName(), c.getPhone(), c.getEmail());
                            }
                        }
                        System.out.println("-----------------------------------------------------------------------------------");
                    }
                    break;

                case 5:  // Đặt tiệc
//                  
                    break;

                case 8:  // Lưu vào file

                    break;

                case 9:  // Thoát chương trình
//                    boolean confirm = menu.confirmYesNo("Do you want to save the changes before exiting? (Y/N): ");
//                    if (confirm) {
//                        if () {
//                            System.out.println("Changes have been saved. Exiting the program.");
//                        } else {
//                            System.out.println("Failed to save changes. Exiting the program.");
//                        }
//                    } else {
//                        System.out.println("Exiting the program without saving changes.");
//                    }
//                    cont = false;
                    break;

                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }

        } while (cont);
    }

}
