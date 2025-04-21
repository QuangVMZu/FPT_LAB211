/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import models.Fees;

/**
 *
 * @author Wang
 */
public class FeesManager extends ArrayList<Fees> {

    private final String pathFile;
    private final ArrayList<Fees> menuList;
    private final String HEADER_TABLE = ("--------------------------------------------------------------------------\n"
            + "List of Set Menus for ordering party:                                     "),
            FOOTER_TABLE = ("--------------------------------------------------------------------------\n");

    public FeesManager() {
        this.pathFile = "./Fees.csv";
        this.menuList = new ArrayList<>();
        readMenuFromFile();
    }

    /**
     *
     */
    public void readMenuFromFile() {
        FileReader fr = null;

        try {
            File f = new File(pathFile);
            //kiểm tra sự tồn tại của file
            if (!f.exists()) {
                System.out.println("Cannot read data from feastMenu.csv. Please check it.");
                return;
            }
            fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String temp = "";

            // Bỏ qua dòng đầu tiên nếu đó là tiêu đề
            br.readLine(); // Đọc và bỏ qua dòng đầu tiên

            // Đọc dữ liệu từ file
            while ((temp = br.readLine()) != null) {
                // tạo đối tượng i để add vào list Menu
                Fees i = dataToObject(temp);
                if (i != null) {
                    menuList.add(i);// Thêm thực đơn vào danh sách
                }
            }
            //Sắp xếp danh sách menu với giá tăng dần
//            menuList.sort(Comparator.comparingDouble(Fees::getPrice));
//            Collections.sort(menuList, new Comparator<Fees>() {
//                @Override
//                public int compare(Fees o1, Fees o2) {
//                    return Double.compare(o1.getPrice(), o2.getPrice());  // So sánh giá của món ăn
//                }
//            });
            br.close();
        } catch (FileNotFoundException ex) {

        } catch (IOException ex) {
        }
//        } finally {
////            if (fr != null) { // Kiểm tra fr trước khi đóng
////                try {
////                    fr.close();
////                } catch (IOException ex) {
////
////                }
////            }
////        }
//        }
    }

    public void displayMenu() {

        System.out.println(HEADER_TABLE);
        for (Fees f : menuList) {
            System.out.println("--------------------------------------------------------------------------");
            System.out.print(f);
            System.out.println(FOOTER_TABLE);
        }
    }

    //TÌM KIẾM MENU BẰNG CODE menu
    public Fees searchMenuByCode(String menuCode) {
        for (Fees i : menuList) {
            if (i.getClassName().toLowerCase().equalsIgnoreCase(menuCode.toLowerCase())) {
                return i;
            }
        }
        return null;
    }
    

    private Fees dataToObject(String text) {
        if (text == null || text.trim().isEmpty()) {
            return null; // Trả về null nếu chuỗi trống
        }
        //tách chuỗi text thành 1 mảng để xử lí
        String[] parts = text.split(",");
        if (parts.length < 3) {
            // Kiểm tra nếu số lượng thành phần không phải là 4
            System.out.println("Invalid data format: " + text);
            return null;
        }
        // Loại bỏ khoảng trắng dư thừa của từng phần tử
        String className = parts[0].trim();
        int grand = Integer.parseInt(parts[1].trim());
        double fee = Double.parseDouble(parts[2]);

        // trả về một MENU được khởi tạo từ các thành phần trên
        return new Fees(className, grand, fee);
    }
}
