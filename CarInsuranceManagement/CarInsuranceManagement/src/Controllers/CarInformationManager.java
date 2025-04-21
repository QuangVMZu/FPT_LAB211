/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Utils.Utils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import models.CarInformation;
import java.util.ArrayList;
import java.util.List;
import models.I_Menu;

/**
 *
 * @author Admin
 */
public class CarInformationManager extends ArrayList<CarInformation> {

    private final String pathFile;
    private final String HEADER_TABLE = ("----------------------------------------------------------"),
            FOOTER_TABLE = ("----------------------------------------------------------");

    public CarInformationManager() {
        super();
        this.pathFile = "./ownerInfor.dat";
        readFromFile();
    }

    public void addNew(CarInformation x) {
        if (!checkExist(x)) {
            this.add(x);
            saveToFile();
        } else {
            System.out.println("The customer already exists in the Register List. \n");
        }
    }

    public CarInformation searchByPlate(String plate) {
        for (CarInformation i : this) {
            if (i.getPlate().toLowerCase().trim().equalsIgnoreCase(plate.toLowerCase().trim())) {
                return i;
            }
        }
        return null;
    }

    public CarInformation searchByValue(double value) {
        for (CarInformation i : this) {
            if (i.getValue() == value) {
                return i;
            }
        }
        return null;
    }

    private boolean checkExist(CarInformation x) {
        for (CarInformation i : this) {
            if (i.getPlate().toLowerCase().equalsIgnoreCase(x.getPlate().toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    public boolean find(String plate) {
        List<CarInformation> matchingPlate = new ArrayList<>();
        for (CarInformation i : this) {
            if (i.getPlate().toLowerCase().contains(plate.toLowerCase())) {
                matchingPlate.add(i);
            }
        }
        if (matchingPlate.isEmpty()) {
            System.out.println("No one matches the search criteria! \n");
        } else {
            System.out.println("\nVehicle Details: ");
            System.out.println(HEADER_TABLE);
            for (CarInformation customer : matchingPlate) {
                System.out.println(customer);
                System.out.println(FOOTER_TABLE);
                break;
            }
        }
        return false;
    }

    public boolean updateInfoCutomer(String plate) {
        boolean updated = false;

        // Tìm kiếm khách hàng theo mã code
        CarInformation ci = searchByPlate(plate);

        if (ci == null) {
            System.out.println("This customer does not exist. \n");
            return false;
        }
        // Hiển thị thông tin customer muốn update
        System.out.println("Found information of Customer you want to update: ");
        System.out.println(HEADER_TABLE);
        System.out.println(ci);
        System.out.println(FOOTER_TABLE);

        // Nhập và cập nhật tên khách hàng
        do {
            String newName = Utils.updateString("Enter new NAME: ", ci.getName());
            if (Utils.isValidName(newName)) {
                if (!newName.isEmpty()) {
                    ci.setName(newName);
                    updated = true;
                }
                break;
            }
            System.out.println("Name is not valid.\n");
        } while (updated);

        // Nhập và cập nhật số điện thoại
        String newPhone;
        do {
            newPhone = Utils.updateString("Enter new PHONE: ", ci.getPhone());
            if (Utils.isValidPhone(newPhone)) {
                if (!newPhone.isEmpty()) {
                    ci.setPhone(newPhone);
                    updated = true;
                }
                break;
            }
            System.out.println("Phone number is not valid.\n");
        } while (updated);

        // Nhập và cập nhật email
        String newBrand;
        do {
            newBrand = Utils.updateString("Enter new EMAIL: ", ci.getBrand());
            if (Utils.isValidBrand(newBrand)) {
                if (!newBrand.isEmpty()) {
                    ci.setBrand(newBrand);
                    updated = true;
                }
                break;
            }
            System.out.println("EMAIL is not valid.");
        } while (updated);

        // Lưu thay đổi vào file nếu có cập nhật
        if (updated) {
            saveToFile();
            System.out.println("UPDATE customer successful.\n");
        } else {
            System.out.println("No find customer to UPDATE.\n");
        }

        return updated;
    }

    public void displayAll(List<CarInformation> ci) {
        if (ci.isEmpty()) {
            System.out.println("Does not have any customer information.");
        } else {
            System.out.println("");
            System.out.println(HEADER_TABLE);
            for (CarInformation i : ci) {
                System.out.println(i);
            }
            System.out.println(FOOTER_TABLE);
        }
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
            for (CarInformation i : this) {
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
                CarInformation c = (CarInformation) ois.readObject();
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

    public boolean delete(String plate) {
        boolean check = false;
        CarInformation ci = searchByPlate(plate);
        System.out.println("\nVehicle Details: ");
        System.out.println(HEADER_TABLE);
        System.out.println(ci);
        System.out.println(FOOTER_TABLE);

        if (ci != null) {
            I_Menu menu = new Menu();
            boolean confirm = menu.confirmYesNo("Do you want to delete this information of car? (Y/N): ");
            if (confirm) {
                this.remove(ci);
                System.out.println("Delete successfull.\n");
                check = true;
            } else {
                System.out.println("Deletion canceled.\n");
            }
        } else {
            System.out.println("No registration found with the given Plate.\n");
        }
        return check;
    }
}
