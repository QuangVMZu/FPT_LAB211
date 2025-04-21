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
import models.FeastMenu;

/**
 *
 * @author Wang
 */
public class FeastMenuManager extends ArrayList<FeastMenu> {

    private final String pathFile;
    private final ArrayList<FeastMenu> menuList;
    private final String HEADER_TABLE = ("--------------------------------------------------------------------------\n"
            + "List of Set Menus for ordering party:                                     "),
            FOOTER_TABLE = ("--------------------------------------------------------------------------\n");

    public FeastMenuManager() {
        this.pathFile = "./FeastMenu.csv";
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
                FeastMenu i = dataToObject(temp);
                if (i != null) {
                    menuList.add(i);// Thêm thực đơn vào danh sách
                }
            }
            //Sắp xếp danh sách menu với giá tăng dần
//            menuList.sort(Comparator.comparingDouble(FeastMenu::getPrice));
            Collections.sort(menuList, new Comparator<FeastMenu>() {
                @Override
                public int compare(FeastMenu o1, FeastMenu o2) {
                    return Double.compare(o1.getPrice(), o2.getPrice());  // So sánh giá của món ăn
                }
            });
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
        for (FeastMenu feastMenu : menuList) {
            System.out.println("--------------------------------------------------------------------------");
            System.out.print(feastMenu);
            System.out.println(FOOTER_TABLE);
        }
    }

    //TÌM KIẾM MENU BẰNG CODE menu
    public FeastMenu searchMenuByCode(String menuCode) {
        for (FeastMenu i : menuList) {
            if (i.getCodeMenu().toLowerCase().equalsIgnoreCase(menuCode.toLowerCase())) {
                return i;
            }
        }
        return null;
    }

    private FeastMenu dataToObject(String text) {
        if (text == null || text.trim().isEmpty()) {
            return null; // Trả về null nếu chuỗi trống
        }
        //tách chuỗi text thành 1 mảng để xử lí
        String[] parts = text.split(",");
        if (parts.length < 4) {
            // Kiểm tra nếu số lượng thành phần không phải là 4
            System.out.println("Invalid data format: " + text);
            return null;
        }
        // Loại bỏ khoảng trắng dư thừa của từng phần tử
        String menuCode = parts[0].trim();
        String nameParty = parts[1].trim();
        double price = Double.parseDouble(parts[2]);
        //kiểm ra ingredients rồi thay thế các dấu# thành xuống hàng
        String ingre = parts[3].trim().replace("#", "\n");

        // trả về một MENU được khởi tạo từ các thành phần trên
        return new FeastMenu(menuCode, nameParty, price, ingre);
    }
}
