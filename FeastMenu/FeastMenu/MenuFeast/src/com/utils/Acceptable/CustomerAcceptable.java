/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utils.Acceptable;

/**
 *
 * @author DELL
 */
public class CustomerAcceptable {

    private static final String CODE = "[CGK]\\d{4}";
    private static final String NAME = "^[A-Za-z ]{2,25}$";
    private static final String PHONE = "^(?:\\+84|0)(3[2-9]|5[2689]|7[0-9]|8[1-9]|9[0-9])\\d{7}$";
    private static final String EMAIL = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

    public static boolean isValid(String arg, String cases) {
        boolean result = false;
        switch (cases) {
            case "code":
                arg = arg.toUpperCase();
                result = arg.matches(CustomerAcceptable.CODE);
                break;
            case "name":
                result = arg.matches(CustomerAcceptable.NAME);
                break;
            case "phone":
                result = arg.matches(CustomerAcceptable.PHONE);
                break;
            case "email":
                result = arg.matches(CustomerAcceptable.EMAIL);
                break;
            default:
                throw new AssertionError();
        }
        return result;
    }
}
