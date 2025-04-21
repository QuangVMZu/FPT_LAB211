/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import models.Customer;
import models.FeastMenu;
import models.OrderFeast;

/**
 *
 * @author ADMIN
 */
public class Utils {

    public static boolean isValidCode(String code) {
        Pattern pattern = Pattern.compile(Acceptable.CUSTOMER_ID_VALID);
        Matcher matcher = pattern.matcher(code);
        return matcher.matches();
    }

    public static boolean isValidPhone(String phone) {
        Pattern pattern = Pattern.compile(Acceptable.PHONE_VALID);
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }

    public static boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile(Acceptable.EMAIL_VALID);
        Matcher matcher = pattern.matcher(email);
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
                if (!isFutureDate(date)) {
                    System.out.println("The event date must be in the future. Try again!");
                    continue;
                }
                return date;
            } catch (ParseException e) {
                System.out.println("Invalid date format! Please enter in format dd/MM/yyyy.");
            }
        }
    }

    // ✅ Kiểm tra ngày có phải trong tương lai không
    public static boolean isFutureDate(Date date) {
        Date today = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String todayStr = sdf.format(today);
        String dateStr = sdf.format(date);
        return dateStr.compareTo(todayStr) > 0;
    }

    // ✅ Chuẩn hóa ngày tháng khi hiển thị
    public static String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(date);
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

    public static boolean writeListObjectToFile(String path, List<Customer> list) {
        boolean result = false;
        try (FileOutputStream file = new FileOutputStream(path);
                ObjectOutputStream oos = new ObjectOutputStream(file)) {

            oos.writeObject(list); // Ghi toàn bộ danh sách vào file
            oos.flush(); // Đảm bảo dữ liệu được ghi hết vào file
            result = true;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static ArrayList<Object> readObjectFromFile(String path) throws IOException {
        ArrayList<Object> list = new ArrayList();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            boolean firstLine = true; // Đánh dấu dòng đầu tiên (tiêu đề)
            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    if (line.startsWith("\uFEFF")) {
//                        line = line.substring(1); // Loại bỏ BOM nếu có
                    }
                    continue; // Bỏ qua dòng tiêu đề
                }
                String[] data = line.split(","); // Tách dữ liệu bằng dấu phẩy
                if (data.length == 4) {
                    String codeOfSetMenu = data[0].trim();
                    String name = data[1].trim();
                    double price = Double.parseDouble(data[2].trim());
                    String ingrediends = data[3].trim();
                    list.add(new FeastMenu(codeOfSetMenu, name, price, ingrediends)); // Thêm Feast vào danh sách
                }
            }
        }
        return list;
    }

    public Customer inputCustomerInfo(boolean isUpdate) {
        Customer customer = new Customer();

        
        // Nếu là thêm mới (không phải update), yêu cầu nhập mã khách hàng
        if (!isUpdate) {
            do {
                String code = getString("Customer Code (C, G, K followed by 4 digits): ").toUpperCase();
                if (isValidCode(code)) {
                    customer.setCustomerCode(code);
                    break;
                } else {
                    System.out.println("Invalid customer code! Please enter again.");
                }
            } while (true);
        }

        // Nhập và kiểm tra tên khách hàng
        do {
            String name = getString("Customer Name: ");
            if (name.matches(Acceptable.NAME_VALID)) { // Kiểm tra regex nếu có
                customer.setName(name);
                break;
            } else {
                System.out.println("Invalid name! Please enter again.");
            }
        } while (true);

        // Nhập và kiểm tra số điện thoại
        do {
            String phone = getString("Phone (10 digits): ");
            if (isValidPhone(phone)) {
                customer.setPhoneNumber(phone);
                break;
            } else {
                System.out.println("Invalid phone number! Please enter again.");
            }
        } while (true);

        // Nhập và kiểm tra email
        do {
            String email = getString("Email: ");
            if (isValidEmail(email)) {
                customer.setEmail(email);
                break;
            } else {
                System.out.println("Invalid email format! Please enter again.");
            }
        } while (true);

        return customer;
    }

    public OrderFeast inputOrderFeast(boolean isUpdate) {
        OrderFeast orderFeast = new OrderFeast();

        // Nếu là thêm mới (không phải update), yêu cầu nhập mã khách hàng
        if (!isUpdate) {
            do {
                String customerCode = getString("Enter customer code (C, G, K followed by 4 digits): ").toUpperCase();
                if (isValidCode(customerCode)) {
                    orderFeast.setCustomeCode(customerCode);
                    break;
                } else {
                    System.out.println("Invalid customer code! Please enter again.");
                }
            } while (true);
        }

        // Nhập mã SetMenu
        do {
            String menuCode = getString("Enter SetMenu code (PW00 + 1 digit): ").toUpperCase();
            if (menuCode.matches("^PW00\\d$")) { // Kiểm tra có dạng PW001, PW002, ...
                orderFeast.setMenuCode(menuCode);
                break;
            } else {
                System.out.println("Invalid menu code! Format should be PW00X (X is a digit).");
            }
        } while (true);

        // Nhập số bàn
        do {
            String numTable = getString("Enter number of tables (Min: 1, Max: 100): ");
            int numTable2 = Integer.parseInt(numTable);
            if (numTable2 > 0) {
                orderFeast.setNumTable(numTable);
                break;
            } else {
                System.out.println("Invalid number of tables! Must be at least 1.");
            }
        } while (true);

        // Nhập ngày tổ chức
        Date eventDate = inputDate("Enter event date (dd/MM/yyyy): ");
        orderFeast.setEventDate(eventDate);

        return orderFeast;
    }
    
    
}
