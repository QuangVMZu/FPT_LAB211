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
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import models.StudentFees;
import models.Students;

/**
 *
 * @author Wang
 */
public class Utils {

//
    public static boolean isValidPhone(String phone) {
        Pattern pattern = Pattern.compile(Acceptable.PHONE_VALID);
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }

//
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
//
    // ✅ Kiểm tra ngày có phải trong tương lai không

    public static boolean isPassedDate(Date date) {
        LocalDate today = LocalDate.now(); // Lấy ngày hôm nay dưới dạng LocalDate
        LocalDate inputDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(); // Chuyển Date sang LocalDate
        return !inputDate.isAfter(today); // Kiểm tra nếu ngày đầu vào là trc ngày hôm nay
    }
//

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
//

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

    public static boolean writeListObjectToFile(String path, List<Students> list) {
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
//
//    public static ArrayList<Object> readObjectFromFile(String path) throws IOException {
//        ArrayList<Object> list = new ArrayList();
//        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
//            String line;
//            boolean firstLine = true; // Đánh dấu dòng đầu tiên (tiêu đề)
//            while ((line = br.readLine()) != null) {
//                if (firstLine) {
//                    firstLine = false;
//                    if (line.startsWith("\uFEFF")) {
////                        line = line.substring(1); // Loại bỏ BOM nếu có
//                    }
//                    continue; // Bỏ qua dòng tiêu đề
//                }
//                String[] data = line.split(","); // Tách dữ liệu bằng dấu phẩy
//                if (data.length == 4) {
//                    String codeOfSetMenu = data[0].trim();
//                    String name = data[1].trim();
//                    double price = Double.parseDouble(data[2].trim());
//                    String ingrediends = data[3].trim().replace("#", "\n");
//                    list.add(new FeastMenu(codeOfSetMenu, name, price, ingrediends)); // Thêm Feast vào danh sách
//                }
////                Collections.sort(menuList, new Comparator<FeastMenu>() {
////                @Override
////                public int compare(FeastMenu o1, FeastMenu o2) {
////                    return Double.compare(o1.getPrice(), o2.getPrice());  // So sánh giá của món ăn
////                }
////            });
////            br.close();
//            }
//        }
//        return list;
//    }
//    
//

    public Students inputCustomerInfo(boolean isUpdate) {
        Students stu = new Students();

        // Nếu là thêm mới (không phải update), yêu cầu nhập mã khách hàng
        if (!isUpdate) {
            do {
                String code = getString("Enter customer code: ").toUpperCase();
                if (code != null) {
                    stu.setCode(code);
                    break;
                } else {
                    System.out.println("Invalid customer code! Please enter again.");
                }
            } while (true);
        }

        // Nhập và kiểm tra tên khách hàng
        do {
            String name = getString("Enter customer NAME: ");
            if (name.matches(Acceptable.NAME_VALID)) { // Kiểm tra regex nếu có
                stu.setName(name);
                break;
            } else {
                System.out.println("Invalid name! Please enter again.");
            }
        } while (true);

        // Nhập và kiểm tra số điện thoại
        do {
            String phone = getString("Enter PHONE number of customer: ");
            if (isValidPhone(phone)) {
                stu.setPhone(phone);
                break;
            } else {
                System.out.println("Invalid phone number! Please enter again.");
            }
        } while (true);

        Date date = inputDate("Enter insurance date (dd/MM/yyyy): ");
        stu.setBirthDay(date);

        int grand = getInt("Enter grand: ", 5, 12);
        stu.setGrand(grand);
        
        String note = getString("Enter note: ");
        stu.setNote(note);
        
        return stu;
    }
//
    public StudentFees inputOrderFeast(boolean isUpdate) {
        StudentFees sf = new StudentFees();

        // Nếu là thêm mới (không phải update), yêu cầu nhập mã khách hàng
        if (!isUpdate) {
            do {
                String code = getString("Enter customer code: ").toUpperCase();
                if (code != null) {
                    sf.setCode(code);
                    break;
                } else {
                    System.out.println("Invalid customer code! Please enter again.");
                }
            } while (true);

            // Nhập mã SetMenu
            do {
                String className = getString("Enter className: ").toUpperCase();
                if (className != null) { // Kiểm tra có dạng PW001, PW002, ...
                    sf.setClassName(className);
                    break;
                } else {
                    System.out.println("Invalid menu code! Format should be PW00X (X is a digit).");
                }
            } while (true);

            do {
                String season = getString("Enter season: ").toUpperCase();
                if (season == "Hè" || season == "HK1" || season == "HK2") {
                    sf.setCode(season);
                    break;
                } else {
                    System.out.println("Invalid customer code! Please enter again.");
                }
            } while (true);
        }
        return sf;
//
    }
}
