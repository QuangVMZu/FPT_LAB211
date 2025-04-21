/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import models.CarInformation;
import models.InsuranceStatement;

/**
 *
 * @author Wang
 */
public class Utils {

    // Tạo HashMap chứa các biển số và quận tương ứng
    public static HashMap<String, String> licensePlatesMap = new HashMap<>();

    static {
        // Thêm dữ liệu mẫu vào licensePlatesMap
        licensePlatesMap.put("X", "Thu Duc");
        licensePlatesMap.put("T", "District 1");
        licensePlatesMap.put("F", "District 3");
        licensePlatesMap.put("C", "District 4");
        licensePlatesMap.put("H", "District 5");
        licensePlatesMap.put("K", "District 6");
        licensePlatesMap.put("L", "District 8");
        licensePlatesMap.put("U", "District 10");
        licensePlatesMap.put("M", "District 11");
        licensePlatesMap.put("G", "District 12");
        licensePlatesMap.put("D", "District Tan Phu");
        licensePlatesMap.put("E", "District Phu Nhuan");
        licensePlatesMap.put("N", "District Binh Tan");
        licensePlatesMap.put("P", "District Tan Binh");
        licensePlatesMap.put("S", "District Binh Thanh");
        licensePlatesMap.put("V", "District Go Vap");
        licensePlatesMap.put("Y", "District Hoc Mon");
        licensePlatesMap.put("Z", "Nha Be");
    }

    // Hàm xác định quận dựa trên ký tự thứ 3 của biển số
    public static String getDistrictByPlate(String plate) {
        if (plate == null || plate.length() < 3) {
            return "Invalid plate number"; // Nếu biển số không hợp lệ
        }

        // Lấy ký tự thứ 3 của biển số
        char thirdCharacter = plate.charAt(2);

        // Kiểm tra và trả về tên quận tương ứng
        String district = licensePlatesMap.get(String.valueOf(thirdCharacter)).toLowerCase();
        if (district != null) {
            return district;
        } else {
            return "District not found for the given plate"; // Nếu không có quận tương ứng
        }
    }

    public static boolean isValidPlate(String plate) {
        Pattern pattern = Pattern.compile(Acceptable.PLATE_VALID);
        Matcher matcher = pattern.matcher(plate);
        return matcher.matches();
    }
    
    public static boolean isValidYear(String year) {
        Pattern pattern = Pattern.compile(Acceptable.YEAR_VALID);
        Matcher matcher = pattern.matcher(year);
        return matcher.matches();
    }

    public static boolean isValidName(String name) {
        Pattern pattern = Pattern.compile(Acceptable.NAME_VALID);
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }

    public static boolean isValidPhone(String phone) {
        Pattern pattern = Pattern.compile(Acceptable.PHONE_VALID);
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }

    public static boolean isValidBrand(String brand) {
        Pattern pattern = Pattern.compile(Acceptable.BRAND_VALID);
        Matcher matcher = pattern.matcher(brand);
        return matcher.matches();
    }

    public static boolean isValidID(String id) {
        Pattern pattern = Pattern.compile(Acceptable.ID_VALID);
        Matcher matcher = pattern.matcher(id);
        return matcher.matches();
    }

    // ✅ Nhập ngày tháng (dd/MM/yyyy) và kiểm tra hợp lệ
    public static Date inputDate(String message) {
        Scanner sc = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);

        Date date = null;
        while (true) {
            try {
                System.out.print(message);
                String input = sc.nextLine().trim();

                if (input.isEmpty()) {
                    System.out.println("Date cannot be empty. Please enter a valid date.");
                    continue;
                }

                date = sdf.parse(input);
                if (!isPassedDate(date)) {
                    System.out.println("The event date must be in the passed. Try again!");
                    continue;
                }
                return date;
            } catch (ParseException e) {
                System.out.println("Invalid date format! Please enter in format dd/MM/yyyy.");
            }
        }
    }

    // ✅ Kiểm tra ngày có phải trong tương lai không
    public static boolean isPassedDate(Date date) {
        LocalDate today = LocalDate.now(); // Lấy ngày hôm nay dưới dạng LocalDate
        LocalDate inputDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(); // Chuyển Date sang LocalDate
        return !inputDate.isAfter(today); // Kiểm tra nếu ngày đầu vào là trc ngày hôm nay
    }

    public static String getString(String welcome) {
        boolean check = true;
        String result = "";
        do {
            Scanner sc = new Scanner(System.in);
            System.out.print(welcome);
            result = sc.nextLine();
            if (result.isEmpty()) {
                System.out.println("Input text!!!");
            } else {
                check = false;
            }
        } while (check);
        return result;
    }

    public static String updateString(String welcome, String oldData) {
        String result = oldData;
        Scanner sc = new Scanner(System.in);
        System.out.print(welcome);
        String tmp = sc.nextLine();
        if (!tmp.isEmpty()) {
            result = tmp;
        }
        return result;
    }

    public static int getInt(String welcome, int min, int max) {
        boolean check = true;
        int number = 0;
        do {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.print(welcome);
                number = Integer.parseInt(sc.nextLine());
                check = false;
            } catch (Exception e) {
                System.out.println("Input number!!!");
            }
        } while (check || number > max || number < min);
        return number;
    }

    public static int updateInt(String welcome, int min, int max, int oldData) {
        boolean check = true;
        int number = oldData;
        do {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.print(welcome);
                String tmp = sc.nextLine();
                if (tmp.isEmpty()) {
                    check = false;
                } else {
                    number = Integer.parseInt(tmp);
                    check = false;
                }
            } catch (Exception e) {
                System.out.println("Input number!!!");
            }
        } while (check || number > max || number < min);
        return number;
    }

    public static boolean confirmYesNo(String welcome) {
        do {
            String input = Utils.getString(welcome).trim().toUpperCase(); // Lấy đầu vào và chuyển thành chữ in hoa
            if (input.equals("Y")) {
                return true; // Người dùng chọn Yes
            } else if (input.equals("N")) {
                return false; // Người dùng chọn No
            }
            System.out.println("Invalid input. Please enter 'Y' or 'N'.");
        } while (true);
    }

    public CarInformation inputOwnerInfo(boolean isUpdate) {
        CarInformation carInf = new CarInformation();
        String plate = "";
        // Nếu là thêm mới (không phải update), yêu cầu nhập mã khách hàng
        if (!isUpdate) {
            do {
                plate = getString("Enter the plate of car: ").toUpperCase();
                if (isValidPlate(plate)) {
                    carInf.setPlate(plate);
                    break;
                } else {
                    System.out.println("Invalid plate ! Please enter again.");
                }
            } while (true);
        }

        // Nhập và kiểm tra tên khách hàng
        do {
            String name = getString("Enter owner NAME: ");
            if (isValidName(name)) { // Kiểm tra regex nếu có
                carInf.setName(name);
                break;
            } else {
                System.out.println("Invalid name! Please enter again.");
            }
        } while (true);

        // Nhập và kiểm tra số điện thoại
        do {
            String phone = getString("Enter PHONE number of owner: ");
            if (isValidPhone(phone)) {
                carInf.setPhone(phone);
                break;
            } else {
                System.out.println("Invalid phone number! Please enter again.");
            }
        } while (true);

        // Nhập và kiểm tra brand
        do {
            String brand = getString("Enter the BRAND of this car: ");
            if (isValidBrand(brand)) {
                carInf.setBrand(brand);
                break;
            } else {
                System.out.println("Invalid brand format! Please enter again.");
            }
        } while (true);

        double value = getInt("Enter value of car: ", 999, 100000000);
        carInf.setValue(value);

        Date date = inputDate("Enter event date (dd/MM/yyyy): ");
        carInf.setDate(date);

        do {
            // Nhập quận/place
            System.out.println("\nThe plate of your car: " + plate);
            System.out.println("------------------------------------------");
            System.out.println("| The place of plate in Ho Chi Minh City |");
            System.out.println("------------------------------------------");
            System.out.println("| X - Thu Duc                            |");
            System.out.println("| T - District 1                         |");
            System.out.println("| F - District 3                         |");
            System.out.println("| C - District 4                         |");
            System.out.println("| H - District 5                         |");
            System.out.println("| K - District 6                         |");
            System.out.println("| L - District 8                         |");
            System.out.println("| U - District 10                        |");
            System.out.println("| M - District 11                        |");
            System.out.println("| G - District 12                        |");
            System.out.println("| D - District Tan Phu                   |");
            System.out.println("| E - District Phu Nhuan                 |");
            System.out.println("| N - District Binh Tan                  |");
            System.out.println("| P - District Tan Binh                  |");
            System.out.println("| S - District Binh Thanh                |");
            System.out.println("| V - District Go Vap                    |");
            System.out.println("| Y - District Hoc Mon                   |");
            System.out.println("| Z - Nha Be                             |");
            System.out.println("------------------------------------------");
            String place = getString("Enter the place for the vehicle: ").toUpperCase().trim();

            // Lấy quận từ biển số xe
            String district = getDistrictByPlate(carInf.getPlate());
            // So sánh quận vừa nhập với quận từ biển số
            if (district != null && district.equalsIgnoreCase(place)) {
                carInf.setPlaceOfRegistration(place);
                break;
            } else {
                // Nếu place trùng với district từ biển số, gán vào thông tin
                System.out.println("The place does not match the district for the given plate.");

            }
        } while (true);

        int seat = getInt("Enter number seat: ", 4, 36);
        carInf.setNumberOfSeat(seat);

        return carInf;
    }

    public InsuranceStatement inputInsurance(boolean isUpdate) {
        InsuranceStatement insurance = new InsuranceStatement();

        if (!isUpdate) {
            do {
                String id = getString("Enter the Insurance ID: ").toUpperCase();
                if (isValidID(id)) {
                    insurance.setInsuranceID(id);
                    break;
                } else {
                    System.out.println("Invalid ID! Please enter again.");
                }
            } while (true);

            do {
                String plate = getString("Enter your plate number: ").toUpperCase();
                if (isValidPlate(plate)) {
                    insurance.setPlate(plate);
                    break;
                } else {
                    System.out.println("Invalid plate! Please enter again.");
                }
            } while (true);

            Date date = inputDate("Enter insurance date (dd/MM/yyyy): ");
            insurance.setEstablishedDate(date);

            int period;
            do {
                period = getInt("Enter the period of insurance (12, 24, 36 months): ", 12, 36);
                if (period == 12 || period == 24 || period == 36) {
                    insurance.setInsurancePeriod(period);
                    break;
                } else {
                    System.out.println("Invalid period! Please enter again (Only 12, 24, or 36).");
                }
            } while (true);

            double value = getInt("Enter the value of the car: ", 999, 100000000);
            insurance.setValue(value);

            double fee = 0.0;
            switch (period) {
                case 12:
                    fee = value * 0.25;
                    break;
                case 24:
                    fee = value * 0.40;
                    break;
                case 36:
                    fee = value * 0.45;
                    break;
            }
            insurance.setFees(fee);

            do {
                String name = getString("Enter the owner of the Insurance: ");
                if (isValidName(name)) {
                    insurance.setNameInsurance(name);
                    break;
                } else {
                    System.out.println("Invalid name! Please enter again.");
                }
            } while (true);
        }
        return insurance;
    }
}
