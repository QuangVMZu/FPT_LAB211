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
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Customer;
import models.FeastMenu;
import models.I_ListOfOrder;
import models.OrderFeast;
import utils.Utils;

/**
 *
 * @author ADMIN
 */
public class TableReservationList extends ArrayList<OrderFeast> {

    private CustomersManager customerList;
    private FeastMenuManager menuList;
    private String pathFile;
    private boolean save;
    private final String HEADER_TABLE = ("-------------------------------------------------------------------------------------------\n"
            + "|ID     | Event date | Customer ID | Set Menu | Price         | Tables | Total Cost       |\n"
            + "-------------------------------------------------------------------------------------------"),
            FOOTER_TABLE = ("-------------------------------------------------------------------------------------------\n");

    public TableReservationList() {
        super();
        this.pathFile = "./feast_order_service.dat";
        this.save = true;
        this.customerList = new CustomersManager();
        customerList.readFromFile();
        this.menuList = new FeastMenuManager();
        menuList.readMenuFromFile();
        readOrderFromFile();
    }

    /**
     * *
     * trước khi kiểm tra tt thì cho đọc lại file customer 1 lần sau đó ktra
     * khách hàng đã tồn tại trong list hay chưa kiểm tra menu id có tồn tại hay
     * không nếu tồn tại lấy ra giá tiền của mã menu đó kiểm tra số lượng
     * bàn,ngay thang có hợp lí chưa ---- sau đó kiểm tra đơn hàng đã tồn tại
     * hay chưa nếu rồi thì tbao còn chưa thì tạo MA DON HANG tính tổng cost =>
     * tạo ra một orderFeast rồi đưa nó vào list Order FEAST ĐỂ CÓ THỂ LƯU LẠI
     * HIEENR THI THONG TIN
     */
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
        if (isDupliOrder(customerCode, menuCode, eventDate)) {
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
    private boolean isDupliOrder(String customerCode, String menuCode, Date date) {
        for (OrderFeast i : this) {
            if (i.getCustomeCode().toLowerCase().equalsIgnoreCase(customerCode.toLowerCase())
                    && i.getMenuCode().toLowerCase().equalsIgnoreCase(menuCode.toLowerCase())
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
        System.out.println("----------------------------------------------------------------");
        System.out.println("Customer order information [Order ID: " + newOrder.getOrderCode() + "]");
        System.out.println("----------------------------------------------------------------");
        System.out.println("Code : " + customer.getCustomerCode());
        System.out.println("Customer name : " + customer.getName());
        System.out.println("Phone number : " + customer.getPhoneNumber());
        System.out.println("Email : " + customer.getEmail());
        System.out.println("----------------------------------------------------------------");
        System.out.println("Code of Set Menu: " + newM.getCodeMenu());
        System.out.println("Set menu name : " + newM.getNameParty());
        System.out.println("Event date : " + newOrder.getEventDate());
        System.out.println("Number of tables: " + newOrder.getNumTable());
        System.out.println("Price : " + String.format("%,.0f", newM.getPrice()) + " Vnd");
        System.out.println("Ingredients:\n" + newM.getIngredients());
        System.out.println("----------------------------------------------------------------");
        System.out.println("Total cost : " + String.format("%,.0f", newOrder.getTotalCost()) + " Vnd");
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
            this.save = true;
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();

        } catch (IOException ex) {
            ex.printStackTrace();

        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();

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
            this.save = true;

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
    public void displayOrderList() {
        if (this.isEmpty()) {
            System.out.println("Does not have any order of customer. ");
        } else {
            System.out.println(HEADER_TABLE);
            for (OrderFeast i : this) {
                System.out.println(i);
                System.out.print(FOOTER_TABLE);
            }
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

    /**
     * *
     * tìm kiếm thông tin của đơn hàng mới có tồn tại hay chưa bằng cách search
     * ID nếu có thì ta cập nhật menu code mới tìm menu đó trong menulist để lấy
     * ra thông tin (giá, tên) set lại các giá trị mới cho đơn hàng như(giá,code
     * khách hàng, số lượng bàn, total code, ngày tổ chức(ngày mới phải trễ hơn
     * ngày cũ))
     *
     */
    public boolean updateInfoOrder(String orderCode) {
        boolean updated = false;

        // Tìm kiếm đơn hàng theo mã
        OrderFeast oldOrder = searchByID(orderCode);
        if (oldOrder == null) {
            System.out.println("This Order does not exist.");
            return false;
        }

        // Hiển thị thông tin đơn hàng trước khi cập nhật
        System.out.println("Found Order:");
        System.out.println("------------------------------------------------------------------------------------------");
        System.out.println("Order ID  | Event Date  | Customer ID  | Set Menu | Price   | Tables | Total Cost");
        System.out.println("------------------------------------------------------------------------------------------");
        System.out.printf("%-10s | %-11s | %-12s | %-8s | %8.2f | %-6s | %10.2f\n",
                oldOrder.getOrderCode(),
                Utils.formatDate(oldOrder.getEventDate()),
                oldOrder.getCustomeCode(),
                oldOrder.getMenuCode(),
                oldOrder.getPrice(),
                oldOrder.getNumTable(),
                oldOrder.getTotalCost());
        System.out.println("------------------------------------------------------------------------------------------");

        // Cập nhật mã SetMenu (nếu có nhập)
        String newMenuCode = Utils.updateString("Enter new SetMenu code (Enter to keep current): ", oldOrder.getMenuCode());
        if (!newMenuCode.isEmpty()) {
            FeastMenu newMenu = menuList.searchMenuByCode(newMenuCode);
            if (newMenu != null) {
                oldOrder.setMenuCode(newMenuCode);
                oldOrder.setPrice(newMenu.getPrice());
                updated = true;
            } else {
                System.out.println("Error: SetMenu code not found. Keeping current value.");
            }
        }

        // Cập nhật số bàn
        String newNumTables;
        do {
            newNumTables = Utils.updateString("Enter new number of tables (Enter to keep current): ", oldOrder.getNumTable());
            if (newNumTables.isEmpty() || newNumTables.matches("\\d+")) {
                if (!newNumTables.isEmpty()) {
                    oldOrder.setNumTable(newNumTables);
                    int numTables = Integer.parseInt(newNumTables);
                    oldOrder.setTotalCost(numTables * oldOrder.getPrice());
                    updated = true;
                }
                break;
            }
            System.out.println("Error: Invalid table count. Please enter a valid number.");
        } while (true);

        // Cập nhật ngày tổ chức
        Date newEventDate;
        do {
            String dateInput = Utils.updateString("Enter new event date (dd/MM/yyyy) (Enter to keep current): ", Utils.formatDate(oldOrder.getEventDate()));
            if (dateInput.isEmpty()) {
                break;
            }
            newEventDate = Utils.inputDate(dateInput);
            if (newEventDate != null && newEventDate.after(new Date())) {
                oldOrder.setEventDate(newEventDate);
                updated = true;
                break;
            }
            System.out.println("Error: The event date must be in the future.");
        } while (true);

        // Kiểm tra nếu đơn hàng trùng lặp với đơn khác
        if (isDupliOrder(oldOrder.getCustomeCode(), oldOrder.getMenuCode(), oldOrder.getEventDate())) {
            System.out.println("Error: Duplicate order data detected!");
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
