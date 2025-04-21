/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import sample.models.CustomerFeastOrder;

/**
 *
 * @author Wang
 */
public class Utils {

    public static final int MIN = 0;
    public static final int MAX = 3000;
    public static final int BASE_FEE = 6000000;
    public static final int DISCOUNT_RATE = 35;

    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";

    public static boolean isValidCode(String code) {
        boolean result = false;
        try {
            if (code.length() == 5) {
                String firstCharacters = code.substring(0, 1);
                String last4Characters = code.substring(1, 5);

                if (firstCharacters.equalsIgnoreCase("C")
                        || firstCharacters.equalsIgnoreCase("G")
                        || firstCharacters.equalsIgnoreCase("K")) {
                    Integer.parseInt(last4Characters);

                    List<Character> digits = new ArrayList<>();
                    boolean hasDuplicate;
                    int i = 0;

                    do {
                        hasDuplicate = false;
                        for (char c : last4Characters.toCharArray()) {
                            if (digits.contains(c)) {
                                hasDuplicate = true;
                                break;
                            }
                            digits.add(c);
                        }
                        i++;
                    } while (hasDuplicate && i < 1); // Chạy đúng 1 lần để kiểm tra trùng số
                    result = !hasDuplicate;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
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

    public static Date inputDate(String message) {
        Scanner sc = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false); // Không chấp nhận ngày không hợp lệ (VD: 32/01/2024)

        Date date = null;
        do {
            try {
                System.out.print(message);
                String input = sc.nextLine();
                date = sdf.parse(input); // Thử chuyển đổi chuỗi thành ngày
                break; // Nếu không lỗi, thoát vòng lặp
            } catch (ParseException e) {
                System.out.println("Invalid date format! Please enter in format dd/MM/yyyy.");
            }
        } while (true); // Lặp lại nếu nhập sai

        return date;
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

    public static boolean writeListObjectToFile(String path, List<CustomerFeastOrder> list) {
        boolean result = false;
        try {
            FileOutputStream file = new FileOutputStream(path);
            ObjectOutputStream oos = new ObjectOutputStream(file);
            try {

                for (CustomerFeastOrder sm : list) {
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
            e.printStackTrace();
        }

        return result;
    }

    public static ArrayList<Object> readObjectFromFile(String path) throws IOException {
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
