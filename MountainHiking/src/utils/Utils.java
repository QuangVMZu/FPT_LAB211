/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Wang
 */
public class Utils {

    public static final int MIN=0;
    public static final int MAX=3000;
    
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-}+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}]$";
    public static boolean isValiateID(String id) {
        boolean result = false;
        try {
            if (id.length() == 8) {
                String fristTwoCharacters = id.substring(0, 2);
                String fristOrtherCharacters = id.substring(2, 8);
                if (fristTwoCharacters.equals("SE") || fristTwoCharacters.equals("HE")
                        || fristTwoCharacters.equals("DE")
                        || fristTwoCharacters.equals("CE") || fristTwoCharacters.equals("QE")) {
                    Integer.parseInt(fristOrtherCharacters);
                    result = true;
                }
            }
        } catch (Exception e) {
            
        }
        return result;
    }
    
    public static boolean isValidPhone(String phone) {
        boolean result = false;
        try {
            if(phone.length() == 10) {
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

    public static String getString(String welcome, String oldData) {
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

    public static int getInt(String welcome, int min, int max, int oldData) {
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
}
