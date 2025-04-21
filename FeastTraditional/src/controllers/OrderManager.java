/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import models.Customer;
import models.FeastMenu;
import models.I_ListOfOrder;
import models.OrderFeast;
import utils.Utils;

/**
 *
 * @author Wang
 */
public class OrderManager extends ArrayList<OrderFeast> implements I_ListOfOrder {

    private CustomersManager customerList;
    private FeastMenuManager menuList;
    private final String pathFile;
    private final String HEADER_TABLE = ("\n----------------------------------------------------------------------------------------------\n"
            + " Order ID  | Event date | Customer ID | Set Menu | Price         | Tables | Total Cost       \n"
            + "----------------------------------------------------------------------------------------------"),
            FOOTER_TABLE = ("----------------------------------------------------------------------------------------------\n");

    public OrderManager() {
        super();
        this.pathFile = "./feast_order_service.dat";
        this.customerList = new CustomersManager();
        customerList.readFromFile();
        this.menuList = new FeastMenuManager();
        menuList.readMenuFromFile();
        readOrderFromFile();
    }

    @Override
    public void placeOrder(OrderFeast order) {
        customerList.readFromFile();

        // Lấy mã khách hàng từ đơn đặt hàng
        String customerCode = order.getCustomeCode();
        // Kiểm tra khách hàng đã tồn tại trong cusList
        Customer existingCustomer = customerList.searchByCode(customerCode);

        // Nếu khách hàng không tồn tại, thông báo lỗi và dừng thực hiện
        if (existingCustomer == null) {
            System.out.println("Customer with code " + customerCode + " not found. Please register the customer first.");
        }

        // Sau khi kiểm tra mã khách hàng hợp lệ, tiếp tục nhập thông tin đặt tiệc
        // NHẬP CODE CỦA MENU CHỌN
        String menuCode = order.getMenuCode();
        menuCode = menuCode.trim(); // Loại bỏ khoảng trắng thừa
        FeastMenu feastMenu = menuList.searchMenuByCode(menuCode); // Tìm kiếm menu bằng mã menu

        // Nếu mã menu không tồn tại, thông báo lỗi và dừng thực hiện
        if (feastMenu == null) {
            System.out.println("SetMenu code not found...");
        }

        // Lấy ra giá tiền của mỗi mã menu
        double price = feastMenu.getPrice();

        // Lấy ra số lượng bàn và kiểm tra
        String numTableStr = order.getNumTable();
        int numTable = Integer.parseInt(numTableStr); // Chuyển số lượng bàn thành kiểu số nguyên

        // Lấy ngày tổ chức từ đơn đặt hàng
        Date eventDate = order.getEventDate();

        // Kiểm tra có bị trùng lặp đơn hàng không
        if (isDupliOrder(customerCode, menuCode, eventDate, null)) {
            System.out.println("Duplicate order data found!");
            return;
        }

        // Tạo mã đơn hàng mới sau khi tất cả kiểm tra hợp lệ
        String orderId = generateOrderId();

        // Tính tổng chi phí (số bàn * giá mỗi bàn)
        double totalCost = numTable * price;

        // Tạo đơn hàng mới và thêm vào danh sách
        OrderFeast newOrder = new OrderFeast(orderId, customerCode, menuCode, numTableStr, totalCost, eventDate, price);
        this.add(newOrder);

        // Hiển thị thông tin đơn hàng đã đặt thành công
        displayOrderInfo(newOrder, feastMenu, existingCustomer);

        saveToFileOrder();
    }

    // kiểm tra đơn hàng có bị trùng lặp hay không
    private boolean isDupliOrder(String customerCode, String menuCode, Date date, OrderFeast currentOrder) {
        for (OrderFeast i : this) {
            // Bỏ qua chính nó khi kiểm tra trùng lặp
            if (i == currentOrder) {
                continue;
            }

            if (i.getCustomeCode().equalsIgnoreCase(customerCode)
                    && i.getMenuCode().equalsIgnoreCase(menuCode)
                    && i.getEventDate().equals(date)) {
                return true;
            }
        }
        return false;
    }

    // TẠO MÃ ĐƠN HÀNG
    private String generateOrderId() {
        return String.valueOf(this.size() + 1);
    }

    private void displayOrderInfo(OrderFeast newOrder, FeastMenu newM, Customer customer) {
        // format date về dạng dd/MM/yy
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String newEvenDate = sdf.format(newOrder.getEventDate());

        System.out.println("----------------------------------------------------------------");
        System.out.println("Customer order information [Order ID: " + newOrder.getOrderCode() + "]");
        System.out.println("----------------------------------------------------------------");
        System.out.println(" Code : " + customer.getCustomerCode());
        System.out.println(" Customer name : " + customer.getName());
        System.out.println(" Phone number : " + customer.getPhoneNumber());
        System.out.println(" Email : " + customer.getEmail());
        System.out.println("----------------------------------------------------------------");
        System.out.println(" Code of Set Menu: " + newM.getCodeMenu());
        System.out.println(" Set menu name : " + newM.getNameParty());
        System.out.println(" Event date : " + newEvenDate);
        System.out.println(" Number of tables: " + newOrder.getNumTable());
        System.out.println(" Price : " + String.format("%,.0f", newM.getPrice()) + " VND");
        System.out.println(" Ingredients:\n" + newM.getIngredients());
        System.out.println("----------------------------------------------------------------");
        System.out.println(" Total cost : " + String.format("%,.0f", newOrder.getTotalCost()) + " VND");
        System.out.println("----------------------------------------------------------------");
        System.out.println();
    }

    //ĐỌC FILE
    public void readOrderFromFile() {
        FileInputStream fis = null;
        try {

            File f = new File(pathFile);
            if (!f.exists()) {

                return;
            }
            //tạo đối tượng đọc dữ liệu
            fis = new FileInputStream(f);
            //tạo ois để đọc dữ liệu từ file
            ObjectInputStream ois = new ObjectInputStream(fis);
            //tạo vòng lặp để đọc dữ liệu
            while (fis.available() > 0) {
                OrderFeast c = (OrderFeast) ois.readObject();
                this.add(c);
            }
            ois.close();
        } catch (FileNotFoundException ex) {

        } catch (IOException ex) {

        } catch (ClassNotFoundException ex) {

        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException ex) {
            }
        }
    }

    //LƯU FILE
    @Override
    public void saveToFileOrder() {
        FileOutputStream fos = null;
        try {

            // tạo file để thu thập dữ liệu đki
            File f = new File(pathFile);

            // mở luồn file output
            fos = new FileOutputStream(f);

            // tạo đối tường outputstream để tuần tự hóa dữ liệu
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            //ghi dữ liệu tuần tự hóa vào file
            for (OrderFeast thi : this) {
                oos.writeObject(thi);
            }
            oos.close();
        } catch (FileNotFoundException ex) {

        } catch (IOException ex) {

        } finally {
            try {
                // Đóng tất cả luồng để giải phóng tài nguyên
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException ex) {

            }
        }
    }

    //display bảng khách hàng đã đặt bàn
    @Override
    public void displayOrderList() {
        if (this.isEmpty()) {
            System.out.println("Does not have any order of customer. ");
        } else {
            System.out.println(HEADER_TABLE);
            for (OrderFeast i : this) {
                System.out.println(i);
            }
            System.out.print(FOOTER_TABLE);
        }
    }

    // tìm kiếm khách hàng bằng mã order
    public OrderFeast searchByID(String orderCode) {
        if (this.isEmpty()) {
            System.out.println("Order list is empty.");
            return null;
        }

        for (OrderFeast i : this) {
            if (i.getOrderCode().trim().equalsIgnoreCase(orderCode.trim())) {
                return i;
            }
        }
        System.out.println("Order not found with code: " + orderCode);
        return null;
    }

    @Override
    public boolean updateInfoOrder(String orderCode) {
        boolean updated = false;

        // Tìm kiếm đơn hàng theo mã
        OrderFeast oldOrder = searchByID(orderCode);
        if (oldOrder == null) {
            System.out.println("This Order does not exist.");
            return false;
        }

        // Hiển thị thông tin đơn hàng trước khi cập nhật
        System.out.print("Found Order you want to update: ");
        System.out.println(HEADER_TABLE);
        System.out.println(oldOrder);
        System.out.print(FOOTER_TABLE);

        // Cập nhật mã SetMenu (nếu có nhập)
        do {
            String newMenuCode = Utils.updateString("Enter new SetMenu code: ", oldOrder.getMenuCode()).toUpperCase();
            if (newMenuCode.matches(utils.Acceptable.MENU_ID_VALID)) {
                if (!newMenuCode.isEmpty()) {
                    FeastMenu newMenu = menuList.searchMenuByCode(newMenuCode);
                    if (newMenu != null) {
                        oldOrder.setMenuCode(newMenuCode);
                        oldOrder.setPrice(newMenu.getPrice());
                        updated = true;
                    }
                    break;
                }
            } else {
                System.out.println("SetMenu code not found. Keeping current value.");
            }
        } while (!updated);

        // Cập nhật số bàn
        do {
            String newNumTables = Utils.updateString("Enter new number of tables: ", oldOrder.getNumTable());
            if (newNumTables.matches("\\d+")) {
                if (!newNumTables.isEmpty()) {
                    oldOrder.setNumTable(newNumTables);
                    int numTables = Integer.parseInt(newNumTables);
                    oldOrder.setTotalCost(numTables * oldOrder.getPrice());
                    updated = true;
                }
                break;
            }
            System.out.println("Invalid table count. Please enter a valid number.");
        } while (updated);

        // Cập nhật ngày tổ chức
        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false); // Bật kiểm tra chặt chẽ ngày hợp lệ

        while (true) {
            System.out.print("Enter event date (dd/MM/yyyy): ");
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println("Keeping the current event date: " + sdf.format(oldOrder.getEventDate()));
                break; // Nếu người dùng không nhập gì, giữ nguyên ngày cũ
            }

            try {
                Date newEventDate = sdf.parse(input);

                if (newEventDate.after(new Date())) { // Kiểm tra ngày trong tương lai
                    oldOrder.setEventDate(newEventDate);
                    break;
                } else {
                    System.out.println("The event date must be in the future. Please try again.");
                }
            } catch (ParseException ex) {
                System.out.println("Invalid date format. Please enter the date in dd/MM/yyyy format.");
            }
        }

        // Kiểm tra nếu đơn hàng trùng lặp với đơn khác
        if (isDupliOrder(oldOrder.getCustomeCode(), oldOrder.getMenuCode(), oldOrder.getEventDate(), oldOrder)) {
            System.out.println("Duplicate order data detected!");
            return false;
        }

        // Lưu thay đổi vào file nếu có cập nhật
        if (updated) {
            saveToFileOrder();
            System.out.println("Order updated successfully.");
        } else {
            System.out.println("No changes were made.");
        }
        return updated;
    }

}
