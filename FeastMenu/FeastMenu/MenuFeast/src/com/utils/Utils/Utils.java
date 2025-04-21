/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utils.Utils;

import com.models.Action;
import com.models.FeastMenu;
import com.utils.Acceptable.CustomerAcceptable;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author hd
 */
public class Utils {

    public static String checkValidString(String welcome, String cases) {
        String result = "";
        do {
            result = getString(welcome);
            if (CustomerAcceptable.isValid(result, cases)) {
                break;
            } else {
                System.out.println("Invalid value for " + cases);
            }
        } while (true);
        return result;
    }

    public static String updateValidString(String welcome, String cases, String oldData) {
        String result = oldData;
        String tmp = updateString(welcome, oldData);

        if (!tmp.isEmpty()) {
            while (CustomerAcceptable.isValid(tmp, cases) == false) {
                System.out.println("Invalid value for " + cases);
                tmp = updateString(welcome, oldData);
                if (tmp.equalsIgnoreCase("")) {
                    break;
                }
            }
            if (!tmp.isEmpty()) {
                result = tmp;
            }
        }
        return result;
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

    public static void displayStatus(boolean status, String success, String failed) {
        if (status == true) {
            System.out.println(success);
        } else {
            System.out.println(failed);
        }
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
        boolean result = false;
        String confirm = Utils.getString(welcome);
        if ("Y".equalsIgnoreCase(confirm)) {
            result = true;
        }
        return result;
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

    public static ArrayList<Object> readListOjectFromFile(String path) throws IOException {
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
    
    public static ArrayList<Object> readMenu(String path) throws IOException {
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
                    list.add(new FeastMenu(codeOfSetMenu, name, Double.parseDouble(price), ingrediends)); // Thêm Feast vào danh sách
                }
            }
        }
        return list;
    }
    
    public static LocalDate getLocalDate(String welcome) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy"); // Định dạng ngày
        LocalDate inputDate;
        
        while (true) {
            try {
                String dateStr = getString(welcome);
                inputDate = LocalDate.parse(dateStr, formatter); // Chuyển chuỗi thành LocalDate
                
                if (inputDate.isAfter(LocalDate.now())) { // Kiểm tra ngày có thuộc tương lai không
                    return inputDate;
                } else {
                    System.out.println("Event's date must be in future. Please input again.");
                }
            } catch (DateTimeParseException e) {
                System.out.println("Wrong format. Please input with [dd-MM-yyyy].");
            }
        }
    }
    
    public static void executeWithRetry(Action action, String successMsg, String failureMsg) {
        while (true) {
            boolean result = action.execute();
            displayStatus(result, successMsg, failureMsg);

            boolean choice = confirmYesNo("Do you want to return to menu : ");

            if (choice) {
                break;
            }
        }
    }
}
