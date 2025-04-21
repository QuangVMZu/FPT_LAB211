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
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import models.CarInformation;
import models.InsuranceStatement;

/**
 *
 * @author ADMIN
 */
public class InsuranceManager extends ArrayList<InsuranceStatement> {

    private final String pathFile;
    private CarInformationManager carInforList;
    private final String HEADER_TABLE = ("\n=====================================================================================================\n"),
            FOOTER_TABLE = ("=====================================================================================================\n");

    public InsuranceManager() {
        super();
        this.pathFile = "./insurance.dat";
        this.carInforList = new CarInformationManager();
        carInforList.readFromFile();
        readInsuranceFromFile();
    }

    public void readInsuranceFromFile() {
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
                InsuranceStatement c = (InsuranceStatement) ois.readObject();
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

    private boolean checkExist(InsuranceStatement x) {
        for (InsuranceStatement i : this) {
            if (i.getInsuranceID().toLowerCase().equalsIgnoreCase(x.getInsuranceID().toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    public void AddInsurance(InsuranceStatement x) {
        carInforList.readFromFile();

        if (!checkExist(x)) {
            String ID = x.getInsuranceID();
            String plate = x.getPlate();
            CarInformation existingPlate = carInforList.searchByPlate(plate);

            if (existingPlate == null) {
                System.out.println("Error: The plate number '" + plate + "' is not found in the system. \n");
                return; // Dừng lại nếu không tìm thấy biển số
            }

            String no = generateOrderId();
            Date establish = x.getEstablishedDate();
            int period = x.getInsurancePeriod();
            double value = x.getValue();

            CarInformation existingValue = carInforList.searchByValue(value);
            if (existingValue == null) {
                System.out.println("Error: The value $" + value + " is invalid. \n");
                return; // Dừng lại nếu giá trị không hợp lệ
            }

            double fees = x.getFees();
            String name = x.getNameInsurance();

            // Chỉ tạo và thêm mới khi tất cả điều kiện đều đúng
            InsuranceStatement newInsurance = new InsuranceStatement(no, ID, plate, establish, period, value, fees, name);
            this.add(newInsurance);

            System.out.println("Insurance added successfully! \n");
            display(this);
            saveInsuranToFile();
        } else {
            System.out.println("Error: The insurance ID '" + x.getInsuranceID() + "' already exists. \n");
        }
    }

    public void saveInsuranToFile() {
        FileOutputStream fos = null;
        try {
            // 1. Thu thập dữ liệu đăng ký hiện tại
            File f = new File(this.pathFile);

            // 2. Mở luồng file output
            fos = new FileOutputStream(f);

            // 3. Tạo object output stream để tuần tự hóa dữ liệu
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            // 4. Ghi dữ liệu tuần tự hóa vào file
            for (InsuranceStatement i : this) {
                oos.writeObject(i);
            }
            oos.close();

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                // Đóng tất cả luồng để giải phóng tài nguyên
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private String generateOrderId() {
        return String.valueOf(this.size() + 1);
    }

    public void display(List<InsuranceStatement> insuranceSta) {
        if (insuranceSta.isEmpty()) {
            System.out.println("No insurance records available. \n");
            return;
        }

        DecimalFormat df = new DecimalFormat("$#,###");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        System.out.print(HEADER_TABLE);
        System.out.printf("| %-10s | %-12s | %-12s | %-10s | %-15s | %-8s | %-12s |\n",
                "No", "Insurance ID", "Established", "Plate", "Name", "Period", "Fee");
        System.out.print(FOOTER_TABLE);
        for (InsuranceStatement is : insuranceSta) {
            System.out.printf("| %-10s | %-12s | %-12s | %-10s | %-15s | %-8d | %-12s |\n",
                    is.getNo(),
                    is.getInsuranceID(),
                    sdf.format(is.getEstablishedDate()),
                    is.getPlate(),
                    is.getNameInsurance(),
                    is.getInsurancePeriod(),
                    df.format(is.getFees()));
        }
        System.out.println(FOOTER_TABLE);
    }

    public void displayInsuranceByYear() {
        int year;
        // Nhập năm hợp lệ
        do {
            String yearS = Utils.getString("Enter YEAR you want to search: ");
            if (Utils.isValidYear(yearS)) {
                year = Integer.parseInt(yearS); // Chuyển String thành int
                break;
            }
            System.out.println("Invalid year! Please enter again. \n");
        } while (true);

        boolean found = false;

        DecimalFormat df = new DecimalFormat("$#,###");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        // Lọc danh sách bảo hiểm theo năm nhập vào
        List<InsuranceStatement> filteredList = new ArrayList<>();
        for (InsuranceStatement is : this) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(is.getEstablishedDate());
            int insuranceYear = cal.get(Calendar.YEAR);

            if (insuranceYear == year) {
                filteredList.add(is);
                found = true;
            }
        }

        // Nếu không tìm thấy bảo hiểm trong năm đó, in thông báo rồi return luôn
        if (!found) {
            System.out.println("No insurance records found for the year " + year);
            System.out.println();
            return;
        }

        // Sắp xếp danh sách theo `fees` từ bé đến lớn bằng Collections.sort()
        Collections.sort(filteredList, new Comparator<InsuranceStatement>() {
            @Override
            public int compare(InsuranceStatement o1, InsuranceStatement o2) {
                return Double.compare(o1.getFees(), o2.getFees());
            }
        });

        int no = 1;
        // Hiển thị khoảng thời gian từ đầu đến cuối năm
        System.out.println("\n-------------------------------------------------");
        System.out.println("Report: INSURANCE STATEMENTS");
        System.out.println("From: 01/01/" + year + "  To: 31/12/" + year);
        System.out.print("-------------------------------------------------");

        // In bảng tiêu đề
        System.out.print(HEADER_TABLE);
        System.out.printf("| %-10s | %-12s | %-12s | %-10s | %-15s | %-8s | %-12s |\n",
                "No.", "Insurance ID", "Established", "Plate", "Name", "Period", "Fee");
        System.out.print(FOOTER_TABLE);
        // In thông tin bảo hiểm đã sắp xếp
        for (InsuranceStatement is : filteredList) {
            System.out.printf("| %-10s | %-12s | %-12s | %-10s | %-15s | %-8d | %-12s |\n",
                    no++,
                    is.getInsuranceID(),
                    sdf.format(is.getEstablishedDate()),
                    is.getPlate(),
                    is.getNameInsurance(),
                    is.getInsurancePeriod(),
                    df.format(is.getFees()));
        }
        System.out.println(FOOTER_TABLE);
    }

    public void displayCarsWithoutInsurance() {
        List<CarInformation> carList = carInforList; // Danh sách tất cả xe
        Set<String> insuredPlates = new HashSet<>(); // Dùng Set để tránh trùng lặp biển số

        // Thu thập biển số của những xe đã đăng ký bảo hiểm
        for (InsuranceStatement is : this) {
            insuredPlates.add(is.getPlate());
        }

        // Dùng Set để lọc biển số xe chưa đăng ký bảo hiểm
        Set<String> uniquePlates = new HashSet<>();
        List<CarInformation> carsWithoutInsurance = new ArrayList<>();

        for (CarInformation car : carList) {
            if (!insuredPlates.contains(car.getPlate()) && uniquePlates.add(car.getPlate())) {
                carsWithoutInsurance.add(car);
            }
        }

        // Kiểm tra nếu không có xe nào chưa đăng ký bảo hiểm
        if (carsWithoutInsurance.isEmpty()) {
            System.out.println("All cars have insurance. \n");
            return;
        }

        // Hiển thị thông tin xe chưa có bảo hiểm
        System.out.print(HEADER_TABLE);
        System.out.printf("| %-10s | %-15s | %-12s | %-20s | %-10s | %-10s |\n",
                "No. ", "Plate", "Registration Date", "Owner", "Brand", "Value");
        System.out.println("-----------------------------------------------------------------------------------------------------");

        DecimalFormat df = new DecimalFormat("$#,###");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        int no = 1;
        for (CarInformation car : carsWithoutInsurance) {
            System.out.printf("| %-10s | %-15s | %-17s | %-20s | %-10s | %-10s |\n",
                    no++,
                    car.getPlate(),
                    sdf.format(car.getDate()),
                    car.getName(),
                    car.getBrand(),
                    df.format(car.getValue()));
        }
        System.out.println(FOOTER_TABLE);
    }
}
