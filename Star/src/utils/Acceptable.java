/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author Wang
 */
public interface Acceptable {

    public final String NAME_VALID = "^.{2,20}$";
    public final String DOUBLE_VALID = "^\\d+.?\\d*$";
    public final String INTEGER_VALID = "^[1-9]\\d*$";
    public final String PHONE_VALID = "^(0[3|5|7|8|9])+([0-9]{8})$";
    public final String EMAIL_VALID = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    public final String MENU_ID_VALID = "^[Pp][Ww]00[1-6]$";
    public final String DATE_VALID = "^(0[1-9]|[1|2][0-9]|3[01])/(0[1-9]|1[0-2])/(\\d{4})$";

}