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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Customer;
import models.OrderFeast;
import models.FeastMenu;
import models.I_List;
import utils.Utils;

/**
 *
 * @author ADMIN
 */
public class CustomersManager extends ArrayList<Customer> {

    private String pathFile;
    private boolean save;
    private final String HEADER_TABLE = ("----------------------------------------------------------------------------\n"
            + "|Customer ID | Customer Name        | Phone        |Email                     \n"
            + "----------------------------------------------------------------------------"),
            FOOTER_TABLE = ("----------------------------------------------------------------------------\n");

    public CustomersManager() {
        super();
        this.pathFile = "./customers.dat";
        this.save = true;
        readFromFile();
    }

    public boolean isSave() {
        return save;
    }

    /**
     * *
     * kiểm tra khách hàng đã đki chưa nếu có thì thông báo còn chưa tồn tại thì
     * thêm khách hàng vào list
     */
    public void addNew(Customer x) {
        if (!checkExist(x)) {
            this.add(x);
            save = false;
            saveToFile();
        } else {
            System.out.println("The customer already exists in the Register List");
        }
        System.out.println();
    }

    /**
     * kiểm tra khách hàng đã đki chưa nếu chưa thì thông báo nếu có rồi thì
     * tiến hành update cho khách hàng(không update id khách hàng)
     */
    public boolean updateInfoCutomer(String code) {
        boolean updated = false;

        // Tìm kiếm khách hàng theo mã code
        Customer customer = searchByCode(code);

        if (customer == null) {
            System.out.println("\"This customer does not exist .");
            return false;
        }

        System.out.println("Khách hàng tìm thấy:");
        System.out.println("-----------------------------------------------------------------------------------");
        System.out.println("Customer Code | Name                 | Phone        | Email                        ");
        System.out.println("-----------------------------------------------------------------------------------");
        System.out.printf("%-15s | %-25s | %-12s | %-30s ",
                customer.getCustomerCode(), customer.getName(), customer.getPhoneNumber(), customer.getEmail());
        System.out.println("-----------------------------------------------------------------------------------");

        // Nhập và cập nhật tên khách hàng
        String newName = Utils.updateString("Nhập tên mới (Enter để giữ nguyên): ", customer.getName());
        if (!newName.isEmpty()) {
            customer.setName(newName);
            updated = true;
        }

        // Nhập và cập nhật số điện thoại
        String newPhone;
        do {
            newPhone = Utils.updateString("Nhập số điện thoại mới (Enter để giữ nguyên): ", customer.getPhoneNumber());
            if (newPhone.isEmpty() || Utils.isValidPhone(newPhone)) {
                if (!newPhone.isEmpty()) {
                    customer.setPhoneNumber(newPhone);
                    updated = true;
                }
                break;
            }
            System.out.println("Lỗi: Số điện thoại không hợp lệ.");
        } while (true);

        // Nhập và cập nhật email
        String newEmail;
        do {
            newEmail = Utils.updateString("Nhập email mới (Enter để giữ nguyên): ", customer.getEmail());
            if (newEmail.isEmpty() || Utils.isValidEmail(newEmail)) {
                if (!newEmail.isEmpty()) {
                    customer.setEmail(newEmail);
                    updated = true;
                }
                break;
            }
            System.out.println("Lỗi: Email không hợp lệ.");
        } while (true);

        // Lưu thay đổi vào file nếu có cập nhật
        if (updated) {
            saveToFile();
            System.out.println("Cập nhật thông tin khách hàng thành công.");
        } else {
            System.out.println("Không có thay đổi nào được thực hiện.");
        }

        return updated;
    }

    public void displayAll() {
        displayAll(this);
    }

    //display bảng khách hàng đã đăng kí
    public void displayAll(List<Customer> cs) {
        if (cs.isEmpty()) {
            System.out.println("Does not have any customer information.");
        } else {
            System.out.println(HEADER_TABLE);
            for (Customer i : cs) {
                System.out.println(i);
            }
            System.out.print(FOOTER_TABLE);
        }
    }
//
//    /**
//     * tại sẵn 1 list mới để chưa danh sách kiếm được tìm kiếm khách hàng bằng
//     * tên nếu tồn tại thêm vào danh sách vừa tạo sau đó trình chiếu danh sách
//     * khách hàng có cùng tên tìm kiếm CHÚ Ý : nên dùng hàm contains khi tìm
//     * kiếm
//     */

    public void searchByName(String name) {
        List<Customer> matchingName = new ArrayList<>();
        for (Customer i : this) {
            if (i.getName().toLowerCase().contains(name.toLowerCase())) {
                matchingName.add(i);
            }
        }
        if (matchingName.isEmpty()) {
            System.out.println("No one matches the search criteria!");
        } else {
            System.out.println(HEADER_TABLE);
            for (Customer customer : matchingName) {
                System.out.println(customer);
                System.out.print(FOOTER_TABLE);
            }

        }
    }
//
//    // đọc file 

    public void readFromFile() {
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
                Customer c = (Customer) ois.readObject();
                this.add(c);
            }
            ois.close();
            this.save = true;

        } catch (FileNotFoundException ex) {
//            Logger.getLogger(CustomersManager.class
//                    .getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();

        } catch (IOException ex) {
//            Logger.getLogger(CustomersManager.class
//                    .getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(CustomersManager.class
//                    .getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }

            } catch (IOException ex) {
                Logger.getLogger(CustomersManager.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
//
//    // lưu file
//    public void saveToFile() {
//        FileOutputStream fos = null;
//        try {
//            // 1. Thu thập dữ liệu đăng ký hiện tại
//            File f = new File(this.pathFile);
//
//            // 2. Mở luồng file output
//            fos = new FileOutputStream(f);
//
//            // 3. Tạo object output stream để tuần tự hóa dữ liệu
//            ObjectOutputStream oos = new ObjectOutputStream(fos);
//
//            // 4. Ghi dữ liệu tuần tự hóa vào file
//            for (Customer i : this) {
//                oos.writeObject(i);
//            }
//            oos.close();
//            this.save = true;
//
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(CustomersManager.class
//                    .getName()).log(Level.SEVERE, null, ex);
//
//        } catch (IOException ex) {
//            Logger.getLogger(CustomersManager.class
//                    .getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            try {
//                // Đóng tất cả luồng để giải phóng tài nguyên
//                if (fos != null) {
//                    fos.close();
//
//                }
//            } catch (IOException ex) {
//                Logger.getLogger(CustomersManager.class
//                        .getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//    }
//
//    // tìm kiếm khách hàng bằng code

    public Customer searchByCode(String Code) {
        for (Customer i : this) {
            if (i.getCustomerCode().toLowerCase().trim().equalsIgnoreCase(Code.toLowerCase().trim())) {
                return i;
            }
        }
        return null;
    }

    //kiểm tra xem khách hàng đã tồn tại trong list hay chưa 
    //BẰNG CÁCH CHECK CUSCODE, NAME, PHONE
    private boolean checkExist(Customer x) {
        for (Customer i : this) {
            if (i.getCustomerCode().toLowerCase().equalsIgnoreCase(x.getCustomerCode().toLowerCase())
                    && i.getName().toLowerCase().contains(x.getName().toLowerCase())
                    && i.getPhoneNumber().equals(x.getPhoneNumber())) {
                return true;
            }
        }
        return false;

    }

    public void saveToFile() {
        FileOutputStream fos = null;
        try {
            // 1. Thu thập dữ liệu đăng ký hiện tại
            File f = new File(this.pathFile);

            // 2. Mở luồng file output
            fos = new FileOutputStream(f);

            // 3. Tạo object output stream để tuần tự hóa dữ liệu
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            // 4. Ghi dữ liệu tuần tự hóa vào file
            for (Customer i : this) {
                oos.writeObject(i);
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
}
