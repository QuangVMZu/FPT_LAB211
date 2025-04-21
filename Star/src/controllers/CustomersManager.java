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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import models.Students;
import utils.Acceptable;
import utils.Utils;

/**
 *
 * @author Wang
 */
public class CustomersManager extends ArrayList<Students>{

    private final String pathFile;
    private final String HEADER_TABLE = ("----------------------------------------------------------------------------------------\n"
            + " Customer ID      | Customer Name             | Phone        |Email                     \n"
            + "----------------------------------------------------------------------------------------"),
            FOOTER_TABLE = ("----------------------------------------------------------------------------------------\n");

    public CustomersManager() {
        super();
        this.pathFile = "./customers.dat";
        readFromFile();
    }

    public void addNew(Students x) {
        if (!checkExist(x)) {
            this.add(x);
            saveToFile();
        } else {
            System.out.println("The customer already exists in the Register List");
        }
    }

//    @Override
//    public boolean updateInfoCutomer(String code) {
//        boolean updated = false;
//
//        // Tìm kiếm khách hàng theo mã code
//        Customer customer = searchByCode(code);
//
//        if (customer == null) {
//            System.out.println("This customer does not exist .");
//            return false;
//        }
//        // Hiển thị thông tin customer muốn update
//        System.out.print("Found information of Customer you want to update: \n");
//        System.out.println(HEADER_TABLE);
//        System.out.println(customer);
//        System.out.print(FOOTER_TABLE);
//
//        // Nhập và cập nhật tên khách hàng
//        do {
//            String newName = Utils.updateString("Enter new NAME: ", customer.getName());
//            if (newName.matches(Acceptable.NAME_VALID)) {
//                if (!newName.isEmpty()) {
//                    customer.setName(newName);
//                    updated = true;
//                }
//                break;
//            }
//            System.out.println("Name is not valid.");
//        } while (updated);
//
//        // Nhập và cập nhật số điện thoại
//        String newPhone;
//        do {
//            newPhone = Utils.updateString("Enter new PHONE: ", customer.getPhoneNumber());
//            if (Utils.isValidPhone(newPhone)) {
//                if (!newPhone.isEmpty()) {
//                    customer.setPhoneNumber(newPhone);
//                    updated = true;
//                }
//                break;
//            }
//            System.out.println("Phone number is not valid.");
//        } while (updated);
//
//        // Nhập và cập nhật email
//        String newEmail;
//        do {
//            newEmail = Utils.updateString("Enter new EMAIL: ", customer.getEmail());
//            if (Utils.isValidEmail(newEmail)) {
//                if (!newEmail.isEmpty()) {
//                    customer.setEmail(newEmail);
//                    updated = true;
//                }
//                break;
//            }
//            System.out.println("EMAIL is not valid.");
//        } while (updated);
//
//        // Lưu thay đổi vào file nếu có cập nhật
//        if (updated) {
//            saveToFile();
//            System.out.println("UPDATE customer successful.");
//        } else {
//            System.out.println("No find customer to UPDATE.");
//        }
//
//        return updated;
//    }

    //display bảng khách hàng đã đăng kí
//    @Override
//    public void displayAll(List<Customer> cs) {
//        if (cs.isEmpty()) {
//            System.out.println("Does not have any customer information.");
//        } else {
//            System.out.println("");
//            System.out.println(HEADER_TABLE);
//            for (Customer i : cs) {
//                System.out.println(i);
//            }
//            System.out.print(FOOTER_TABLE);
//        }
//    }

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
                Students c = (Students) ois.readObject();
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

//    public Customer searchByCode(String Code) {
//        for (Customer i : this) {
//            if (i.getCustomerCode().toLowerCase().trim().equalsIgnoreCase(Code.toLowerCase().trim())) {
//                return i;
//            }
//        }
//        return null;
//    }

    //kiểm tra xem khách hàng đã tồn tại trong list hay chưa 
    //BẰNG CÁCH CHECK CUSCODE, NAME, PHONE
    private boolean checkExist(Students x) {
        for (Students i : this) {
            if (i.getCode().toLowerCase().equalsIgnoreCase(x.getCode().toLowerCase())
                    && i.getName().toLowerCase().contains(x.getName().toLowerCase())
                    && i.getPhone().equals(x.getPhone())) {
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
            for (Students i : this) {
                oos.writeObject(i);
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

//    @Override
//    public void searchByName(String name) {
//        List<Customer> matchingName = new ArrayList<>();
//        for (Customer i : this) {
//            if (i.getName().toLowerCase().contains(name.toLowerCase())) {
//                matchingName.add(i);
//            }
//        }
//        if (matchingName.isEmpty()) {
//            System.out.println("No one matches the search criteria!");
//        } else {
//            System.out.println(HEADER_TABLE);
//            for (Customer customer : matchingName) {
//                System.out.println(customer);
//                System.out.print(FOOTER_TABLE);
//            }
//        }
//    }
}
