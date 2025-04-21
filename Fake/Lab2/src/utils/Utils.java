/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import controllers.CustomerManager;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
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
import models.Feast;
import models.FeastCustomer;

/**
 *
 * @author ADMIN
 */
public class Utils {

    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
    public static final String CUSTOMER_ID_VALID = "^[CcGgKk]\\d{4}$";

//    public static boolean isValidCode(String code) {
//        boolean result = false;
//        try {
//            if (code.length() == 5) {
//                String firstCharacters = code.substring(0, 1);
//                String last4Characters = code.substring(1);
//                if (firstCharacters.equalsIgnoreCase("C")
//                        || firstCharacters.equalsIgnoreCase("G")
//                        || firstCharacters.equalsIgnoreCase("K")) {
//                    Integer.parseInt(last4Characters);
//                    result = true;
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return result;
//    }
//     
    public static boolean isValidCode(String code) {
        Pattern pattern = Pattern.compile(CUSTOMER_ID_VALID);
        Matcher matcher = pattern.matcher(code);
        return matcher.matches();
    }

    public static boolean isValidPhone(String phone) {
        boolean result = false;
        try {
            if (phone.length() == 10) {
                Integer.parseInt(phone);
                result = true;
            }
        } catch (Exception e) {
        }
        return result;
    }

    public static boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
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

    public static boolean writeListObjectToFile(String path, List<Object> list) {
        boolean result = false;
        try {
            FileOutputStream file = new FileOutputStream(path);
            ObjectOutputStream oos = new ObjectOutputStream(file);
            try {

                for (Object sm : list) {
                    oos.writeObject(sm);
                }
                result = true;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (oos != null) {
                    oos.close();
                }
            }
            if (file != null) {
                file.close();
            }
        } catch (Exception e) {
        }

        return result;
    }

    public static boolean writeListObjectToFileOrder(String path, List<FeastCustomer> list) {
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

//    public static ArrayList<Object> readObjectFromFile(String path) throws IOException {
//        FileInputStream fis = new FileInputStream(path);
//        ObjectInputStream ois = new ObjectInputStream(fis);
//        ArrayList<Object> list = new ArrayList();
//        try {
//            Object obj = null;
//            while (fis.available() > 0) {
//                obj = (Object) ois.readObject();
//                list.add(obj);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (ois != null) {
//                ois.close();
//            }
//            if (fis != null) {
//                fis.close();
//            }
//        }
//        return list;
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
                    String price = data[2].trim();
                    String ingrediends = data[3].trim();
                    list.add(new Feast(codeOfSetMenu, name, price, ingrediends)); // Thêm Feast vào danh sách
                }
            }
        }
        return list;
    }

    
    public static ArrayList<Object> readListOjectFromFileDat(String path) throws IOException {
        FileInputStream fis = new FileInputStream(path);
        ObjectInputStream ois = new ObjectInputStream(fis);
        ArrayList<Object> list = new ArrayList();
        try {
            Object obj = null;
            while (fis.available() > 0) {
                obj = (Object) ois.readObject();
                list.add(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ois != null) {
                ois.close();
            }
            if (fis != null) {
                fis.close();
            }
        }
        return list;
    }
}
